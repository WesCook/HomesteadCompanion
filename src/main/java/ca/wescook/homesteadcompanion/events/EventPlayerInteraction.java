package ca.wescook.homesteadcompanion.events;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Map;
import static net.minecraft.block.BlockTorch.FACING;

public class EventPlayerInteraction {
	@SubscribeEvent
	public void playerInteraction(PlayerInteractEvent.RightClickBlock event) {
		// Get info
		World world = event.getWorld();
		ItemStack stack = event.getItemStack();
		EntityPlayer player = event.getEntityPlayer();
		IBlockState blockState = world.getBlockState(event.getPos());

		// If item is used on campfire
		if (Loader.isModLoaded("ToughAsNails") && blockState.getBlock().equals(Block.getBlockFromName("ToughAsNails:campfire")))
			lightTorchOnCampfire(event, world, player, stack, blockState);
		// If item is used on unlit torch
		else if (Loader.isModLoaded("RealisticTorches") && blockState.getBlock().equals(Block.getBlockFromName("RealisticTorches:TorchUnlit")))
			relightTorchOnGround(event, world, player, stack, blockState);
	}

	private void lightTorchOnCampfire(PlayerInteractEvent.RightClickBlock event, World world, EntityPlayer player, ItemStack stack, IBlockState blockState) {
		// Get item instances (may be null if mod Realistic Torches isn't loaded)
		Item unlitTorch = Item.getByNameOrId("RealisticTorches:TorchUnlit");
		Item litTorch = Item.getByNameOrId("RealisticTorches:TorchLit");

		// Get out if not using stick or unlit torch
		if (stack == null || !(stack.getItem().equals(Items.STICK) || stack.getItem().equals(unlitTorch)))
			return;

		// Check that fire is burning and ignite torch
		Comparable burningStatus = getProperty(blockState, "burning");
		if (burningStatus != null && burningStatus.equals(true)) {
			if (!world.isRemote) { // Only on server
				// Remove one stick/unlit torch
				if (!player.isCreative())
					--stack.stackSize;

				// Ignites torch
				if (litTorch != null) // If Realistic Torches mod is loaded
					player.inventory.addItemStackToInventory(new ItemStack(litTorch)); // Give lit torch
				else
					player.inventory.addItemStackToInventory(new ItemStack(Blocks.TORCH)); // Give vanilla torch
			}

			// Don't place block
			if (event.isCancelable() && stack.getItem().equals(unlitTorch))
				event.setCanceled(true);
		}
	}

	private void relightTorchOnGround(PlayerInteractEvent.RightClickBlock event, World world, EntityPlayer player, ItemStack stack, IBlockState blockState) {
		// Get item instances (may be null if mod Realistic Torches isn't loaded)
		Item litTorch = Item.getByNameOrId("RealisticTorches:TorchLit");
		Item matchbox = Item.getByNameOrId("RealisticTorches:Matchbox");

		// Get out if not using lit torch or matchbox
		if (stack == null || !(stack.getItem().equals(litTorch) || stack.getItem().equals(Item.getItemFromBlock(Blocks.TORCH)) || stack.getItem().equals(matchbox)))
			return;

		// Light torch
		EnumFacing facing = (EnumFacing)getProperty(blockState, "facing"); // Get torch rotation
		if (facing != null)
			world.setBlockState(event.getPos(), Block.getBlockFromItem(litTorch).getDefaultState().withProperty(FACING, facing)); // Replace torch with rotation applied

		// Reduce durability of matchbox
		if (stack.getItem().equals(matchbox))
			stack.damageItem(1, player);

		// Don't place block
		if (event.isCancelable())
			event.setCanceled(true);
	}

	// Iterate through properties looking for string and return value
	private Comparable getProperty(IBlockState blockState, String desiredProperty) {
		ImmutableMap<IProperty<?>, Comparable<?>> properties = blockState.getProperties(); // Get list of properties
		for (Map.Entry<IProperty<?>, Comparable<?>> property : properties.entrySet()) { // Iterate over properties
			if (property.getKey().getName().equals(desiredProperty)) { // If property is found
				return property.getValue();
			}
		}
		return null; // Else return null
	}
}

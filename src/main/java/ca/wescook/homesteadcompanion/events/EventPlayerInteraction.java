package ca.wescook.homesteadcompanion.events;

import ca.wescook.homesteadcompanion.HomesteadCompanion;
import ca.wescook.homesteadcompanion.gui.ModGuiHandler;
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

import java.util.HashMap;
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
			campfireCraft(event, world, player, stack, blockState);
		// If item is used on unlit torch
		else if (Loader.isModLoaded("RealisticTorches") && blockState.getBlock().equals(Block.getBlockFromName("RealisticTorches:TorchUnlit")))
			relightTorchOnGround(event, world, player, stack, blockState);

		// NutritionManager GUI
		// TODO: Implement proper activation method
		if (blockState.getBlock().equals(Blocks.GRASS) && world.isRemote)
			player.openGui(HomesteadCompanion.instance, ModGuiHandler.NUTRITION_GUI, world, (int) player.posX, (int) player.posY, (int) player.posZ);
	}

	private void campfireCraft(PlayerInteractEvent.RightClickBlock event, World world, EntityPlayer player, ItemStack stack, IBlockState blockState) {
		// Define item ingredient/result list
		Map<Item, Item> campfireItems = new HashMap<Item, Item>();

		// Add entries
		campfireItems.put(Item.getItemFromBlock(Blocks.SAND), Items.GLASS_BOTTLE); // Cooking sand into glass
		if (Loader.isModLoaded("RealisticTorches")) { // Light realistic torches
			campfireItems.put(Items.STICK, Item.getByNameOrId("RealisticTorches:TorchLit")); // Turn sticks into lit torches
			campfireItems.put(Item.getByNameOrId("RealisticTorches:TorchUnlit"), Item.getByNameOrId("RealisticTorches:TorchLit")); // Turn unlit torches into lit torches
		}
		else // Create normal torch
			campfireItems.put(Items.STICK, Item.getItemFromBlock(Blocks.TORCH));

		// If no item in hand, get out
		if (stack == null)
			return;

		// If fire is not burning, get out
		Comparable burningStatus = getBlockStateProperty(blockState, "burning");
		if (burningStatus == null || burningStatus.equals(false))
			return;

		// Craft item
		for (Map.Entry<Item, Item> campfireItem : campfireItems.entrySet()) { // Iterate through crafting list
			if (campfireItem.getKey().equals(stack.getItem())) { // If it matches
				// Cancel block place event
				if (event.isCancelable())
					event.setCanceled(true);

				// Only on server
				if (!world.isRemote) {
					// Remove crafting input
					if (!player.isCreative())
						--stack.stackSize;

					// Craft item
					player.inventory.addItemStackToInventory(new ItemStack(campfireItem.getValue()));

					// Item was already found, can exit
					return;
				}
			}
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
		EnumFacing facing = (EnumFacing) getBlockStateProperty(blockState, "facing"); // Get torch rotation
		if (facing != null)
			world.setBlockState(event.getPos(), Block.getBlockFromItem(litTorch).getDefaultState().withProperty(FACING, facing)); // Replace torch with rotation applied

		// Reduce durability of matchbox
		if (stack.getItem().equals(matchbox))
			stack.damageItem(1, player);

		// Cancel block place event
		if (event.isCancelable())
			event.setCanceled(true);
	}

	// Iterate through properties looking for string and return value
	private Comparable getBlockStateProperty(IBlockState blockState, String desiredProperty) {
		ImmutableMap<IProperty<?>, Comparable<?>> properties = blockState.getProperties(); // Get list of properties
		for (Map.Entry<IProperty<?>, Comparable<?>> property : properties.entrySet()) { // Iterate over properties
			if (property.getKey().getName().equals(desiredProperty)) { // If property is found
				return property.getValue();
			}
		}
		return null; // Else return null
	}
}

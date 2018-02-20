package ca.wescook.homesteadcompanion.items;

import ca.wescook.homesteadcompanion.HomesteadCompanion;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.tools.TinkerMaterials;
import slimeknights.tconstruct.tools.melee.TinkerMeleeWeapons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemRandomTinkers extends Item {
	ItemRandomTinkers() {
		setRegistryName("random_tinkers");
		setUnlocalizedName(getRegistryName().toString());
		GameRegistry.register(this);
		setCreativeTab(CreativeTabs.COMBAT);
		setMaxStackSize(1);
	}

	@SideOnly(Side.CLIENT)
	void render() {
		// Maps item to blockstate json of same name
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}

	// When in inventory, try and turn into Tinkers tool
	// This only applies to players.  See EventRandomTinkers for the mob version of this code.
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		// Get out if TConstruct isn't installed
		if (!Loader.isModLoaded("tconstruct"))
			return;

		// If on client, get out
		if (worldIn.isRemote)
			return;

		// Replace item (on server)
		entityIn.replaceItemInInventory(itemSlot, generateRandomTool());
	}

	// Add tooltips
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(I18n.format("tooltip." + HomesteadCompanion.MODID + ":random_tinkers", TextFormatting.DARK_GRAY));
	}

	// Returns random Tinkers tool
	public ItemStack generateRandomTool() {
		// Tool list
		List<ToolCore> toolList = new ArrayList<ToolCore>();
		toolList.add(TinkerMeleeWeapons.cleaver);
		toolList.add(TinkerMeleeWeapons.broadSword);
		toolList.add(TinkerMeleeWeapons.rapier);
		toolList.add(TinkerMeleeWeapons.longSword);

		// Materials list
		List<Material> availableMaterials = new ArrayList<Material>();
		availableMaterials.add(TinkerMaterials.wood);
		availableMaterials.add(TinkerMaterials.stone);
		availableMaterials.add(TinkerMaterials.flint);
		availableMaterials.add(TinkerMaterials.cactus);
		availableMaterials.add(TinkerMaterials.bone);
		availableMaterials.add(TinkerMaterials.obsidian);
		availableMaterials.add(TinkerMaterials.prismarine);
		availableMaterials.add(TinkerMaterials.endstone);
		availableMaterials.add(TinkerMaterials.paper);
		availableMaterials.add(TinkerMaterials.sponge);
		availableMaterials.add(TinkerMaterials.firewood);
		availableMaterials.add(TinkerMaterials.knightslime);
		availableMaterials.add(TinkerMaterials.slime);
		availableMaterials.add(TinkerMaterials.blueslime);
		availableMaterials.add(TinkerMaterials.magmaslime);
		availableMaterials.add(TinkerMaterials.iron);
		availableMaterials.add(TinkerMaterials.pigiron);
		availableMaterials.add(TinkerMaterials.netherrack);
		availableMaterials.add(TinkerMaterials.ardite);
		availableMaterials.add(TinkerMaterials.cobalt);
		availableMaterials.add(TinkerMaterials.manyullyn);

		// Choose a tool at random
		ToolCore chosenTool = toolList.get(new Random().nextInt(toolList.size()));

		// Pick materials at random
		List<Material> chosenMaterials = new ArrayList<Material>();
		for (int i=0; i<chosenTool.getToolBuildComponents().size(); i++) // Get part count
			chosenMaterials.add(availableMaterials.get(new Random().nextInt(availableMaterials.size())));

		// Build and return tool
		return chosenTool.buildItem(chosenMaterials);
	}
}

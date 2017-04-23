package ca.wescook.homesteadcompanion.proxy;

import ca.wescook.homesteadcompanion.HomesteadCompanion;
import ca.wescook.homesteadcompanion.events.EventEntitySpawn;
import ca.wescook.homesteadcompanion.events.EventPlayerInteraction;
import ca.wescook.homesteadcompanion.events.EventUseItem;
import ca.wescook.homesteadcompanion.gui.ModGuiHandler;
import ca.wescook.homesteadcompanion.items.ModItems;
import ca.wescook.homesteadcompanion.nutrition.NutritionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
		ModItems.registerItems(); // Register items
	}

	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(HomesteadCompanion.instance, new ModGuiHandler()); // Register GUI handler
		NutritionManager.register(); // Register nutrients
	}

	public void postInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new EventPlayerInteraction()); // Register interaction event
		MinecraftForge.EVENT_BUS.register(new EventEntitySpawn()); // Register entity spawn event
		MinecraftForge.EVENT_BUS.register(new EventUseItem()); // Register use item event
	}
}

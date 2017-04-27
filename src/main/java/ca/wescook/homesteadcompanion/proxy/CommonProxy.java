package ca.wescook.homesteadcompanion.proxy;

import ca.wescook.homesteadcompanion.HomesteadCompanion;
import ca.wescook.homesteadcompanion.events.*;
import ca.wescook.homesteadcompanion.gui.ModGuiHandler;
import ca.wescook.homesteadcompanion.items.ModItems;
import ca.wescook.homesteadcompanion.network.ModPacketHandler;
import ca.wescook.homesteadcompanion.nutrition.common.NutrientList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
		ModItems.registerItems(); // Register items
		ModPacketHandler.registerMessages(); // Register network messages
	}

	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(HomesteadCompanion.instance, new ModGuiHandler()); // Register GUI handler
		NutrientList.register(); // Register nutrients
		MinecraftForge.EVENT_BUS.register(new EventLightFire()); // Register player interaction event
	}

	public void postInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new EventPlayerAction()); // Player player login, logout, death
		MinecraftForge.EVENT_BUS.register(new EventNutritionButton()); // Register GUI events
		MinecraftForge.EVENT_BUS.register(new EventRandomTinkers()); // Register entity spawn event
		MinecraftForge.EVENT_BUS.register(new EventEatFood()); // Register use item event
	}
}

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
		// Register items
		ModItems.registerItems();

		// Register network messages
		ModPacketHandler.registerMessages();
	}

	public void init(FMLInitializationEvent event) {
		// Register GUI handler
		NetworkRegistry.INSTANCE.registerGuiHandler(HomesteadCompanion.instance, new ModGuiHandler());

		// Register nutrients
		NutrientList.register();
	}

	public void postInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new EventPlayerLogIn()); // Register player login
		MinecraftForge.EVENT_BUS.register(new EventPlayerLogOut()); // Register player logout
		MinecraftForge.EVENT_BUS.register(new EventPlayerInteraction()); // Register player interaction event
		MinecraftForge.EVENT_BUS.register(new EventEntitySpawn()); // Register entity spawn event
		MinecraftForge.EVENT_BUS.register(new EventUseItem()); // Register use item event
	}
}

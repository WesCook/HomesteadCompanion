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
		MinecraftForge.EVENT_BUS.register(new EventPlayerInteraction()); // Register player interaction event
	}

	public void postInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new EventPlayerLogIn()); // Register player login event
		MinecraftForge.EVENT_BUS.register(new EventPlayerLogOut()); // Register player logout event
		MinecraftForge.EVENT_BUS.register(new EventPlayerDeath()); // Register player death event
		MinecraftForge.EVENT_BUS.register(new EventGuiScreen()); // Register GUI events
		MinecraftForge.EVENT_BUS.register(new EventEntitySpawn()); // Register entity spawn event
		MinecraftForge.EVENT_BUS.register(new EventUseItem()); // Register use item event
	}
}

package ca.wescook.homesteadcompanion.proxy;

import ca.wescook.homesteadcompanion.events.EventLightFire;
import ca.wescook.homesteadcompanion.events.EventRandomTinkers;
import ca.wescook.homesteadcompanion.items.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
		ModItems.registerItems(); // Register items
	}

	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new EventLightFire()); // Register player interaction event
	}

	public void postInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new EventRandomTinkers()); // Register entity spawn event
	}
}

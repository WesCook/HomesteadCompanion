package ca.wescook.homesteadcompanion;

import ca.wescook.homesteadcompanion.events.EventPlayerInteraction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import static ca.wescook.homesteadcompanion.HomesteadCompanion.*;

@Mod(modid = MODID, name = MODNAME, version = "@VERSION@", dependencies = DEPENDENCIES)
public class HomesteadCompanion
{
	static final String MODID = "homesteadcompanion";
	static final String MODNAME = "Homestead Companion";
	static final String DEPENDENCIES = "after:RealisticTorches;after:ToughAsNails";
    
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new EventPlayerInteraction()); // Register interaction event
	}
}

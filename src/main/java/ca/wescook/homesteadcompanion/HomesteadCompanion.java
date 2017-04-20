package ca.wescook.homesteadcompanion;

import ca.wescook.homesteadcompanion.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import static ca.wescook.homesteadcompanion.HomesteadCompanion.MODID;
import static ca.wescook.homesteadcompanion.HomesteadCompanion.MODNAME;

@Mod(modid = MODID, name = MODNAME, version = "@VERSION@")
public class HomesteadCompanion
{
	public static final String MODID = "homesteadcompanion";
	public static final String MODNAME = "Homestead Companion";

	// Create instance of mod
	@Mod.Instance
	public static HomesteadCompanion instance;

	// Create instance of proxy
	// This will vary depending on if the client or server is running
	@SidedProxy(clientSide="ca.wescook.homesteadcompanion.proxy.ClientProxy", serverSide="ca.wescook.homesteadcompanion.proxy.ServerProxy")
	static private CommonProxy proxy;

	// Events
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		HomesteadCompanion.proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		HomesteadCompanion.proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		HomesteadCompanion.proxy.postInit(event);
	}

}

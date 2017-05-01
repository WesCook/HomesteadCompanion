package ca.wescook.homesteadcompanion.proxy;

import ca.wescook.homesteadcompanion.items.ModItems;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		ModItems.renderItems(); // Client-side rendering
	}
}

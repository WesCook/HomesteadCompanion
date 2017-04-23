package ca.wescook.homesteadcompanion.proxy;

import ca.wescook.homesteadcompanion.events.EventKeyInput;
import ca.wescook.homesteadcompanion.items.ModItems;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	public static KeyBinding keyNutritionGui;

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

		ModItems.renderItems();
	}

	public void init(FMLInitializationEvent event) {
		super.init(event);

		ClientRegistry.registerKeyBinding(keyNutritionGui = new KeyBinding("key.nutrition", 49, "Homestead")); // Register key, default to "N"
		MinecraftForge.EVENT_BUS.register(new EventKeyInput()); // Register key input event
	}
}

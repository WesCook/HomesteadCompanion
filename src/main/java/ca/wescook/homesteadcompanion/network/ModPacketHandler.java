package ca.wescook.homesteadcompanion.network;

import ca.wescook.homesteadcompanion.HomesteadCompanion;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModPacketHandler {
	public static final SimpleNetworkWrapper NETWORK_CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(HomesteadCompanion.MODID);

	// Message IDs
	private static int MESSAGE_NUTRITION_REQUEST = 0;
	private static int MESSAGE_NUTRITION_RESPONSE = 1;

	// Register messages on run
	public static void registerMessages() {
		NETWORK_CHANNEL.registerMessage(PacketNutritionRequest.Handler.class, PacketNutritionRequest.Message.class, MESSAGE_NUTRITION_REQUEST, Side.SERVER);
		NETWORK_CHANNEL.registerMessage(PacketNutritionResponse.Handler.class, PacketNutritionResponse.Message.class, MESSAGE_NUTRITION_RESPONSE, Side.CLIENT);
	}
}

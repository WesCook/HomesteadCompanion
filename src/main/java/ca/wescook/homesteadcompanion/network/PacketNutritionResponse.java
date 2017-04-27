package ca.wescook.homesteadcompanion.network;

import ca.wescook.homesteadcompanion.gui.ModGuiHandler;
import ca.wescook.homesteadcompanion.nutrition.common.Nutrient;
import ca.wescook.homesteadcompanion.nutrition.common.NutrientList;
import ca.wescook.homesteadcompanion.nutrition.server.PlayerNutritionList;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.HashMap;
import java.util.Map;

public class PacketNutritionResponse {
	// Message Subclass
	public static class Message implements IMessage {
		// Server vars only
		EntityPlayer serverPlayer;

		// Client vars only
		Map<Nutrient, Integer> clientNutrients;

		public Message() {}

		// Message data is passed along from server
		public Message(EntityPlayer player) {
			serverPlayer = player; // Get server player
		}

		// Then serialized into bytes (on server)
		@Override
		public void toBytes(ByteBuf buf) {
			// Loop through nutrients and add to buffer
			for (Nutrient nutrient : NutrientList.getAll())
				buf.writeInt(PlayerNutritionList.getPlayerNutrition(serverPlayer).get(nutrient));
		}

		// Then deserialized (on the client)
		@Override
		public void fromBytes(ByteBuf buf) {
			// Loop through nutrients to build accurate list from data
			clientNutrients = new HashMap<Nutrient, Integer>();
			for (Nutrient nutrient : NutrientList.getAll())
				clientNutrients.put(nutrient, buf.readInt());
		}
	}

	// Message Handler Subclass
	// This is the client response to the information
	public static class Handler implements IMessageHandler<Message, IMessage> {
		@Override
		public IMessage onMessage(final Message message, final MessageContext context) {
			FMLCommonHandler.instance().getWorldThread(context.netHandler).addScheduledTask(new Runnable() {
				@Override
				public void run() {
					// Get info
					GuiScreen currentScreen = Minecraft.getMinecraft().currentScreen;

					// Ensure GUI is still open
					if (currentScreen == null || !currentScreen.equals(ModGuiHandler.nutritionGui))
						return;

					// Update GUI information
					ModGuiHandler.nutritionGui.updateInformation(message.clientNutrients);
				}
			});
			return null;
		}
	}
}

package ca.wescook.homesteadcompanion.network;

import ca.wescook.homesteadcompanion.gui.ModGuiHandler;
import ca.wescook.homesteadcompanion.gui.NutritionGui;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Random;

public class PacketNutritionResponse {
	// Message Subclass
	public static class Message implements IMessage {
		int val;

		public Message() {}

		public Message(int val) {
			// Message data is passed along from server
			this.val = val;
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			val = buf.readInt();
		}

		@Override
		public void toBytes(ByteBuf buf) {
			buf.writeInt(val);
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
					ModGuiHandler.nutritionGui.updateInformation(message.val);
				}
			});
			return null;
		}
	}
}

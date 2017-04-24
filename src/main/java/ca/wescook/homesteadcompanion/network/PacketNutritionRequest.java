package ca.wescook.homesteadcompanion.network;

import com.google.common.util.concurrent.ListenableFuture;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Random;

public class PacketNutritionRequest {
	// Message Subclass
	// Empty message just to request information
	public static class Message implements IMessage {
		public Message() {}

		@Override
		public void fromBytes(ByteBuf buf) {}

		@Override
		public void toBytes(ByteBuf buf) {}
	}

	// Message Handler Subclass
	// Handled on server
	public static class Handler implements IMessageHandler<Message, IMessage> {
		@Override
		public IMessage onMessage(final Message message, final MessageContext context) {
			FMLCommonHandler.instance().getWorldThread(context.netHandler).addScheduledTask(new Runnable() {
				@Override
				public void run() {
					// Return message
					int val = new Random().nextInt(100) + 1; // Create data
					// EntityPlayerMP playerEntity = context.getServerHandler().playerEntity; // Get Player on server
					ModPacketHandler.NETWORK_CHANNEL.sendTo(new PacketNutritionResponse.Message(val), context.getServerHandler().playerEntity);
				}
			});
			return null;
		}
	}
}

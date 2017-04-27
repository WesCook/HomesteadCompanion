package ca.wescook.homesteadcompanion.events;

import ca.wescook.homesteadcompanion.HomesteadCompanion;
import ca.wescook.homesteadcompanion.gui.ModGuiHandler;
import ca.wescook.homesteadcompanion.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

public class EventNutritionKey {
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void keyInput(InputEvent.KeyInputEvent event) {
		// Exit on key de-press
		if (!Keyboard.getEventKeyState()) {
			return;
		}

		if (Keyboard.getEventKey() == ClientProxy.keyNutritionGui.getKeyCode()) {
			openNutritionGui();
		}
	}

	// Opens GUI to show nutrition menu
	@SideOnly(Side.CLIENT)
	private void openNutritionGui() {
		// Get data
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		World world = Minecraft.getMinecraft().theWorld;

		// Open GUI
		player.openGui(HomesteadCompanion.instance, ModGuiHandler.NUTRITION_GUI_ID, world, (int) player.posX, (int) player.posY, (int) player.posZ);
	}
}

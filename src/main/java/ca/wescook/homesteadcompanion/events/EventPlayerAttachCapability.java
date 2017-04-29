package ca.wescook.homesteadcompanion.events;

import ca.wescook.homesteadcompanion.HomesteadCompanion;
import ca.wescook.homesteadcompanion.capabilities.ManaProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventPlayerAttachCapability {
	@SubscribeEvent
	public void AttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event) {
		// Attach capability to player
		Entity entity = event.getObject();
		if (entity instanceof EntityPlayer)
			event.addCapability(new ResourceLocation(HomesteadCompanion.MODID, "nutrition"), new ManaProvider());
	}
}

package ca.wescook.homesteadcompanion.events;

import ca.wescook.homesteadcompanion.nutrition.server.PlayerNutritionList;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class EventPlayerLogOut {
	@SubscribeEvent
	public void playerLogOut(PlayerEvent.PlayerLoggedOutEvent event) {
		// Release player from nutrition list
		if (!event.player.worldObj.isRemote)
			PlayerNutritionList.releasePlayer(event.player);
	}
}

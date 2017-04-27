package ca.wescook.homesteadcompanion.events;

import ca.wescook.homesteadcompanion.nutrition.server.PlayerNutritionList;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class EventPlayerDeath {
	@SubscribeEvent
	public void playerDeath(PlayerEvent.PlayerRespawnEvent event) {
		// Reduce player nutrition on death
		if (!event.player.worldObj.isRemote)
			PlayerNutritionList.playerDeath(event.player);
	}
}

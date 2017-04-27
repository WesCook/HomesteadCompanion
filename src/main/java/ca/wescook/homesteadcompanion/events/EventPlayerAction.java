package ca.wescook.homesteadcompanion.events;

import ca.wescook.homesteadcompanion.nutrition.server.PlayerNutritionList;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class EventPlayerAction {
	@SubscribeEvent
	public void playerLogIn(PlayerEvent.PlayerLoggedInEvent event) {
		// Start tracking new player's nutrition (on server)
		if (!event.player.worldObj.isRemote)
			PlayerNutritionList.newPlayer(event.player);
	}

	@SubscribeEvent
	public void playerLogOut(PlayerEvent.PlayerLoggedOutEvent event) {
		// Release player from nutrition list (on server)
		if (!event.player.worldObj.isRemote)
			PlayerNutritionList.releasePlayer(event.player);
	}

	@SubscribeEvent
	public void playerDeath(PlayerEvent.PlayerRespawnEvent event) {
		// Reduce player nutrition on death
		if (!event.player.worldObj.isRemote)
			PlayerNutritionList.playerDeath(event.player);
	}
}

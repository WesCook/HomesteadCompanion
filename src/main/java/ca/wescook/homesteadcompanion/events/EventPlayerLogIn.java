package ca.wescook.homesteadcompanion.events;

import ca.wescook.homesteadcompanion.nutrition.server.PlayerNutritionList;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class EventPlayerLogIn {
	@SubscribeEvent
	public void playerLogIn(PlayerEvent.PlayerLoggedInEvent event) {
		// Start tracking new player's nutrition
		if (!event.player.worldObj.isRemote)
			PlayerNutritionList.newPlayer(event.player);
	}
}

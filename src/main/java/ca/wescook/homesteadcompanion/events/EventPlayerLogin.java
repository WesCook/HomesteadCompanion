package ca.wescook.homesteadcompanion.events;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class EventPlayerLogin {
	@SubscribeEvent
	public void playerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		checkNutrition(event);
	}

	// Gets current nutrition from player file, or resets if none found
	void checkNutrition(PlayerEvent.PlayerLoggedInEvent event) {
		System.out.println(event.player);
	}
}

package ca.wescook.homesteadcompanion.events;

import ca.wescook.homesteadcompanion.nutrition.INutrition;
import ca.wescook.homesteadcompanion.nutrition.NutritionProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventPlayerClone {
	// Copy player nutrition when "cloned" (death, teleport from End)
	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event) {
		// Duplicate player nutrition on teleport/death
		EntityPlayer player = event.getEntityPlayer(); // Get player
		INutrition nutritionOld = event.getOriginal().getCapability(NutritionProvider.NUTRITION_CAPABILITY, null); // Get old nutrition
		INutrition nutritionNew = player.getCapability(NutritionProvider.NUTRITION_CAPABILITY, null); // Get new nutrition
		nutritionNew.set(nutritionOld.get()); // Overwrite nutrition

		// On death, apply penalty
		if (!player.worldObj.isRemote && event.isWasDeath())
			player.getCapability(NutritionProvider.NUTRITION_CAPABILITY, null).deathPenalty();
	}
}

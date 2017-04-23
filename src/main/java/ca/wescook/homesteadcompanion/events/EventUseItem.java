package ca.wescook.homesteadcompanion.events;

import ca.wescook.homesteadcompanion.nutrition.common.Nutrient;
import ca.wescook.homesteadcompanion.nutrition.common.NutrientList;
import ca.wescook.homesteadcompanion.nutrition.server.PlayerNutrition;
import ca.wescook.homesteadcompanion.nutrition.server.PlayerNutritionList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventUseItem {
	@SubscribeEvent
	public void finishUsingItem(LivingEntityUseItemEvent.Finish event) {
		checkFoodEaten(event);
	}

	// Checks which food has been eaten, and updates nutrition
	private void checkFoodEaten(LivingEntityUseItemEvent.Finish event) {
		// Only run on server
		if (event.getEntity().getEntityWorld().isRemote)
			return;

		// Add nutrition value
		// TODO: Add other foods
		if (event.getItem().getItem().equals(Items.BEETROOT)) {
			PlayerNutrition playerNutrition = PlayerNutritionList.getPlayerNutrition((EntityPlayer) event.getEntity());
			Nutrient vegetable = NutrientList.getNutrientByName("vegetable");
			playerNutrition.add(vegetable, 10);
		}
	}
}

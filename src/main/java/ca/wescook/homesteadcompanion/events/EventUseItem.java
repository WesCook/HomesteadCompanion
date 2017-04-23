package ca.wescook.homesteadcompanion.events;

import ca.wescook.homesteadcompanion.nutrition.NutritionManager;
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

		// TODO: Add other foods
		if (event.getItem().getItem().equals(Items.BEETROOT))
			NutritionManager.NUTRIENT_VEGETABLE.add(15);
	}
}

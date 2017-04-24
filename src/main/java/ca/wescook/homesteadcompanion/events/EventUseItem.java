package ca.wescook.homesteadcompanion.events;

import ca.wescook.homesteadcompanion.nutrition.common.Nutrient;
import ca.wescook.homesteadcompanion.nutrition.common.NutrientList;
import ca.wescook.homesteadcompanion.nutrition.server.PlayerNutrition;
import ca.wescook.homesteadcompanion.nutrition.server.PlayerNutritionList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
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

		// Get out if not food item
		Item item = event.getItem().getItem();
		if (!(item instanceof ItemFood))
			return;

		// Add nutrition value
		ItemFood food = (ItemFood) event.getItem().getItem();
		if (food.equals(Items.BEETROOT)) {
			PlayerNutrition playerNutrition = PlayerNutritionList.getPlayerNutrition((EntityPlayer) event.getEntity()); // Get player's nutrition
			Nutrient vegetable = NutrientList.getNutrientByName("vegetable"); // Get relevant nutrient
			playerNutrition.add(vegetable, food.getHealAmount(null)); // Update player nutrition
		}
	}
}

package ca.wescook.homesteadcompanion.nutrition;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class NutritionManager {
	private static List<Nutrient> nutrients = new ArrayList<Nutrient>();

	public static void register() {
		// Register nutrients
		nutrients.add(new Nutrient("grain", 0xfff4d92e, new ItemStack(Items.WHEAT), 100));
		nutrients.add(new Nutrient("fruit", 0xffcd73f4, new ItemStack(Items.APPLE), 60));
		nutrients.add(new Nutrient("vegetable", 0xff72dd5a, new ItemStack(Items.CARROT), 100));
		nutrients.add(new Nutrient("protein", 0xffdb6c23, new ItemStack(Items.COOKED_BEEF), 80));
		nutrients.add(new Nutrient("dairy", 0xffa0d4f7, new ItemStack(Items.MILK_BUCKET), 100));
	}

	// Give list of all nutrients
	static List<Nutrient> returnSet() {
		return nutrients;
	}
}

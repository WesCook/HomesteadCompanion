package ca.wescook.homesteadcompanion.nutrition;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class NutritionManager {
	static List<Nutrient> nutrients = new ArrayList<Nutrient>();

	public static Nutrient NUTRIENT_GRAIN = new Nutrient("grain", 0xfff4d92e, new ItemStack(Items.WHEAT), 20);
	public static Nutrient NUTRIENT_FRUIT = new Nutrient("fruit", 0xffcd73f4, new ItemStack(Items.APPLE), 20);
	public static Nutrient NUTRIENT_VEGETABLE = new Nutrient("vegetable", 0xff72dd5a, new ItemStack(Items.CARROT), 20);
	public static Nutrient NUTRIENT_PROTEIN = new Nutrient("protein", 0xffdb6c23, new ItemStack(Items.COOKED_BEEF), 20);
	public static Nutrient NUTRIENT_DAIRY = new Nutrient("dairy", 0xffa0d4f7, new ItemStack(Items.MILK_BUCKET), 20);

	public static void register() {
		// Register nutrients
		nutrients.add(NUTRIENT_GRAIN);
		nutrients.add(NUTRIENT_FRUIT);
		nutrients.add(NUTRIENT_VEGETABLE);
		nutrients.add(NUTRIENT_PROTEIN);
		nutrients.add(NUTRIENT_DAIRY);
	}

	// Give list of all nutrients
	static List<Nutrient> returnSet() {
		return nutrients;
	}
}

package ca.wescook.homesteadcompanion.nutrition;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class NutritionManager {
	static List<Nutrient> nutrients = new ArrayList<Nutrient>();

	static final int STARTING_NUTRITION = 50;
	static final int DEATH_LOSS = 25;

	public static Nutrient NUTRIENT_GRAIN = new Nutrient("grain", 0xfff4d92e, new ItemStack(Items.WHEAT));
	public static Nutrient NUTRIENT_FRUIT = new Nutrient("fruit", 0xffcd73f4, new ItemStack(Items.APPLE));
	public static Nutrient NUTRIENT_VEGETABLE = new Nutrient("vegetable", 0xff72dd5a, new ItemStack(Items.CARROT));
	public static Nutrient NUTRIENT_PROTEIN = new Nutrient("protein", 0xffdb6c23, new ItemStack(Items.COOKED_BEEF));
	public static Nutrient NUTRIENT_DAIRY = new Nutrient("dairy", 0xffa0d4f7, new ItemStack(Items.MILK_BUCKET));

	public static void register() {
		// Register nutrients
		nutrients.add(NUTRIENT_GRAIN);
		nutrients.add(NUTRIENT_FRUIT);
		nutrients.add(NUTRIENT_VEGETABLE);
		nutrients.add(NUTRIENT_PROTEIN);
		nutrients.add(NUTRIENT_DAIRY);
	}

	// Return list of all nutrients
	static List<Nutrient> returnSet() {
		return nutrients;
	}

	// Reset all nutrients to default
	public void resetNutrition() {
		for (Nutrient nutrient : nutrients)
			nutrient.reset();
	}

	// Call to lower all nutrition by death penalty
	// TODO: Add death detection event
	void deathPenalty() {
		for (Nutrient nutrient : nutrients)
			nutrient.subtract(DEATH_LOSS);
	}
}

package ca.wescook.homesteadcompanion.nutrition;

import net.minecraft.item.ItemStack;

public class Nutrient {
	// Nutrient properties
	public String name;
	public int color;
	public ItemStack icon;
	private int value;

	Nutrient(String name, int color, ItemStack icon) {
		// Update object
		this.name = name;
		this.color = color;
		this.icon = icon;
	}

	// Get nutrition value
	public int getValue() {
		return value;
	}

	// Increase nutrition
	public void add(int amount) {
		value += Math.min(amount, 100);
	}

	// Decrease nutrition
	public void subtract(int amount) {
		value -= Math.max(amount, 0);
	}

	// Reset nutrient to default value
	void reset() {
		value = NutritionManager.STARTING_NUTRITION;
	}
}

package ca.wescook.homesteadcompanion.nutrition;

import net.minecraft.item.ItemStack;

public class Nutrient {
	// Nutrient properties
	public String name;
	public int color;
	public ItemStack icon;
	private int value;

	Nutrient(String name, int color, ItemStack icon, int initialValue) {
		// Update object
		this.name = name;
		this.color = color;
		this.icon = icon;
		this.value = initialValue;
	}

	// Get nutrition value
	public int getValue() {
		return value;
	}

	// Increase nutrition
	public void add(int amount) {
		this.value += Math.min(amount, 100);
	}

	// Decrease nutrition
	public void decrease(int amount) {
		this.value -= Math.max(amount, 0);
	}
}

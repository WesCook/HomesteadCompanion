package ca.wescook.homesteadcompanion.nutrition;

import net.minecraft.item.ItemStack;

public class Nutrient {
	// Nutrient properties
	private String name;
	private int color;
	private int value;
	private ItemStack icon;

	Nutrient(String name, int color, ItemStack icon, int initialValue) {
		// Update object
		this.name = name;
		this.color = color;
		this.icon = icon;
		this.value = initialValue;
	}

	// Get name
	String getName() {
		return name;
	}

	// Get background color
	int getColor() {
		return color;
	}

	// Get icon
	ItemStack getIcon() {
		return icon;
	}

	// Get nutrition value
	int getValue() {
		return value;
	}

	// Set nutrition value
	public void setValue(int value) {
		this.value = value;
	}
}

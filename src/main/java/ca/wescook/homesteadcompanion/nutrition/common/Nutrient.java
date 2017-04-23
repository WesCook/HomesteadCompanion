package ca.wescook.homesteadcompanion.nutrition.common;

import net.minecraft.item.ItemStack;

public class Nutrient {
	// Nutrient properties
	public String name;
	public int color;
	public ItemStack icon;

	Nutrient(String name, int color, ItemStack icon) {
		// Update object
		this.name = name;
		this.color = color;
		this.icon = icon;
	}
}

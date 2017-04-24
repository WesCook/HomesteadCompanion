package ca.wescook.homesteadcompanion.nutrition.common;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

// Maintains list of information about nutrients (name, color, icon)
// Stored client and server-side
public class NutrientList {
	private static List<Nutrient> nutrients = new ArrayList<Nutrient>();

	public static final int STARTING_NUTRITION = 50;
	public static final int DEATH_LOSS = 25;

	public static void register() {
		// Register nutrients
		nutrients.add(new Nutrient("grain", 0xfff4d92e, new ItemStack(Items.WHEAT)));
		nutrients.add(new Nutrient("fruit", 0xffcd73f4, new ItemStack(Items.APPLE)));
		nutrients.add(new Nutrient("vegetable", 0xff72dd5a, new ItemStack(Items.CARROT)));
		nutrients.add(new Nutrient("protein", 0xffdb6c23, new ItemStack(Items.COOKED_BEEF)));
		nutrients.add(new Nutrient("dairy", 0xffa0d4f7, new ItemStack(Items.MILK_BUCKET)));
	}

	// Return list of all nutrients
	public static List<Nutrient> returnSet() {
		return nutrients;
	}

	// Return nutrient by name
	// Returns null if not found
	public static Nutrient getNutrientByName(String name) {
		for (Nutrient nutrient : nutrients) {
			if (nutrient.name.equals(name))
				return nutrient;
		}

		return null;
	}
}

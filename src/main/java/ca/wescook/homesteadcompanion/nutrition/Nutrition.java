package ca.wescook.homesteadcompanion.nutrition;

import java.util.HashMap;
import java.util.Map;

// Default implementation of Capability.  Contains logic for each method defined in the Interface.
public class Nutrition implements INutrition {
	// Map Nutrient type to value for that nutrient
	private Map<Nutrient, Integer> playerNutrition = new HashMap<Nutrient, Integer>();

	// Constants
	private final int STARTING_NUTRITION = 50;
	private final int DEATH_LOSS = 15;

	public Nutrition() {
		// Populate nutrient data with starting nutrition
		for (Nutrient nutrient : NutrientList.get())
			playerNutrition.put(nutrient, STARTING_NUTRITION);
	}

	public Map<Nutrient, Integer> get() {
		return playerNutrition;
	}

	public Integer get(Nutrient nutrient) {
		return playerNutrition.get(nutrient);
	}

	public void set(Map<Nutrient, Integer> nutrientData) {
		this.playerNutrition = nutrientData;
	}

	public void add(Nutrient nutrient, int amount) {
		int currentAmount = playerNutrition.get(nutrient);
		playerNutrition.put(nutrient, Math.min(currentAmount + amount, 100));
	}

	public void subtract(Nutrient nutrient, int amount) {
		int currentAmount = playerNutrition.get(nutrient);
		playerNutrition.put(nutrient, Math.max(currentAmount - amount, 0));
	}

	public void deathPenalty() {
		for (Nutrient nutrient : playerNutrition.keySet()) // Loop through player's nutrients
			subtract(nutrient, DEATH_LOSS); // Subtract death penalty to each
	}
}

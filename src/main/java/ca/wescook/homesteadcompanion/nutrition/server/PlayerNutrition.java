package ca.wescook.homesteadcompanion.nutrition.server;

import ca.wescook.homesteadcompanion.nutrition.common.Nutrient;
import ca.wescook.homesteadcompanion.nutrition.common.NutrientList;

import java.util.HashMap;
import java.util.Map;

// Actual nutrition levels for one player
// Server-side only - data is synced to the client when needed
public class PlayerNutrition {
	// Maps Nutrient type to value for that nutrient
	private Map<Nutrient, Integer> nutrientData = new HashMap<Nutrient, Integer>();

	// Return all nutrients
	public Map<Nutrient, Integer> get() {
		return nutrientData;
	}

	// Return specific nutrient
	public Integer get(Nutrient nutrient) {
		return nutrientData.get(nutrient);
	}


	// Increase nutrition
	public void add(Nutrient nutrient, int amount) {
		int currentAmount = nutrientData.get(nutrient);
		nutrientData.put(nutrient, Math.min(currentAmount + amount, 100));
	}

	// Decrease nutrition
	void subtract(Nutrient nutrient, int amount) {
		int currentAmount = nutrientData.get(nutrient);
		nutrientData.put(nutrient, Math.max(currentAmount - amount, 0));
	}

	// Reset nutrition to default value
	// Probably a new player
	void reset() {
		for (Nutrient nutrient : NutrientList.getAll()) // Loop through known nutrient list
			nutrientData.put(nutrient, NutrientList.STARTING_NUTRITION); // Reset values (if they exist, overwritten - if not, created)
	}

	// Penalize all skills for death
	void deathPenalty() {
		for (Nutrient nutrient : nutrientData.keySet()) // Loop through player's nutrients
			subtract(nutrient, NutrientList.DEATH_LOSS); // Subtract death penalty to each
	}
}

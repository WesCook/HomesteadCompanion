package ca.wescook.homesteadcompanion.nutrition;

import java.util.Map;

// Capability Interface that describes what methods the Implementations should understand.
public interface INutrition {
	// Return all nutrients
	Map<Nutrient, Integer> get();

	// Return specific nutrient
	Integer get(Nutrient nutrient);

	// Overwrite all nutrients
	void set(Map<Nutrient, Integer> nutrientData);

	// Increase nutrition
	void add(Nutrient nutrient, int amount);

	// Decrease nutrition
	void subtract(Nutrient nutrient, int amount);

	// Penalize all skills on death
	void deathPenalty();
}

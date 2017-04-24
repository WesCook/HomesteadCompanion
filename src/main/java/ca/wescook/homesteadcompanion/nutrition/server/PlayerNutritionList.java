package ca.wescook.homesteadcompanion.nutrition.server;

import net.minecraft.entity.player.EntityPlayer;

import java.util.HashMap;
import java.util.Map;

// Maintains list of current player's nutrients being tracked (as instances of PlayerNutrition)
// Saves/loads as players join/leave, so only logged in users are tracked
// Stored server-side
public class PlayerNutritionList {
	private static Map<EntityPlayer, PlayerNutrition> playerNutritionData = new HashMap<EntityPlayer, PlayerNutrition>();

	// TODO: Finish implementation
	public static void newPlayer(EntityPlayer player) {
		//Check player's file for nutrition data
		//If data exists, read data into PlayerNutrition object.
		//If data doesn't exist, generate new PlayerNutrition data.
		//Add player to list with their PlayerNutrition data.

		PlayerNutrition newPlayerNutrition = new PlayerNutrition(); // New entry for player nutrition
		newPlayerNutrition.reset(); // Reset values
		playerNutritionData.put(player, newPlayerNutrition); // Add to list
	}

	// Release player from list
	public static void releasePlayer(EntityPlayer player) {
		savePlayer(player);
		playerNutritionData.remove(player);
	}

	// Save player's nutrition data to their file
	// TODO: Implement in PlayerEvent#PlayerLoggedOutEvent and WorldEvent#Save
	public static void savePlayer(EntityPlayer player) {
		//Save data to file
	}

	// Reset all nutrients to default
	public static void resetNutrition(EntityPlayer player) {
		playerNutritionData.get(player).reset();
	}

	// Call to lower all nutrition by deathPenalty penalty
	// TODO: Implement in PlayerEvent#PlayerRespawnEvent
	public static void playerDeath(EntityPlayer player) {
		playerNutritionData.get(player).deathPenalty();
	}

	public static PlayerNutrition getPlayerNutrition(EntityPlayer player) {
		return playerNutritionData.get(player);
	}
}

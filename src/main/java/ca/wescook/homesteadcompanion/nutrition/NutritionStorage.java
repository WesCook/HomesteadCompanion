package ca.wescook.homesteadcompanion.nutrition;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import java.util.HashMap;

// Saves and loads serialized data from disk
public class NutritionStorage implements Capability.IStorage<INutrition> {
	// Save serialized data to disk
	@Override
	public NBTBase writeNBT(Capability<INutrition> capability, INutrition instance, EnumFacing side) {
		NBTTagCompound playerData = new NBTTagCompound();
		for (Nutrient nutrient : NutrientList.get())
			playerData.setInteger(nutrient.name, instance.get(nutrient));
		return playerData;
	}

	// Load serialized data from disk
	@Override
	public void readNBT(Capability<INutrition> capability, INutrition instance, EnumFacing side, NBTBase nbt) {
		HashMap<Nutrient, Integer> clientNutrients = new HashMap<Nutrient, Integer>(); // Create new map
		for (Nutrient nutrient : NutrientList.get()) {
			Integer value = ((NBTTagCompound) nbt).getInteger(nutrient.name); // Read ints from packet
			clientNutrients.put(nutrient, value); // Add to map
		}
		instance.set(clientNutrients); // Replace nutrient data wih map
	}
}

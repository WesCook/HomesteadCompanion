package ca.wescook.homesteadcompanion.nutrition;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import java.util.HashMap;

// Saves and loads serialized data from disk
public class NutritionStorage implements Capability.IStorage<INutrition> {
	@Override
	public NBTBase writeNBT(Capability<INutrition> capability, INutrition instance, EnumFacing side) {
		NBTTagCompound playerData = new NBTTagCompound();
		for (Nutrient nutrient : NutrientList.get())
			playerData.setInteger(nutrient.name, instance.get(nutrient));
		return playerData;
	}

	@Override
	public void readNBT(Capability<INutrition> capability, INutrition instance, EnumFacing side, NBTBase nbt) {
		// Loop through nutrients to build accurate list from data
		HashMap<Nutrient, Integer> clientNutrients = new HashMap<Nutrient, Integer>();
		for (Nutrient nutrient : NutrientList.get()) {
			Integer value = ((NBTTagCompound) nbt).getInteger(nutrient.name);
			clientNutrients.put(nutrient, value);
		}

		// Replace nutrient data
		instance.set(clientNutrients);
	}
}

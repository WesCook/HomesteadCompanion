package ca.wescook.homesteadcompanion.nutrition;

import jline.internal.Nullable;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

// Creates and holds default implementation.  Offers Capability access, and optionally handles serialization.
public class NutritionProvider implements ICapabilitySerializable<NBTBase> {

	// Inject capability into field
	@CapabilityInject(INutrition.class)
	public static final Capability<INutrition> NUTRITION_CAPABILITY = null;

	// Create default instance
	private INutrition instance = NUTRITION_CAPABILITY.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == NUTRITION_CAPABILITY)
			return true;
		return false;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if (capability == NUTRITION_CAPABILITY)
			return  NUTRITION_CAPABILITY.cast(instance);
		return null;
	}

	@Override
	public NBTBase serializeNBT() {
		return NUTRITION_CAPABILITY.getStorage().writeNBT(NUTRITION_CAPABILITY, instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		NUTRITION_CAPABILITY.getStorage().readNBT(NUTRITION_CAPABILITY, instance, null, nbt);
	}
}

package ca.wescook.homesteadcompanion.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

// Saves and loads serialized data from disk
public class ManaStorage implements Capability.IStorage<IMana> {
	@Override
	public NBTBase writeNBT(Capability<IMana> capability, IMana instance, EnumFacing side) {
		return new NBTTagFloat(instance.getMana());
	}

	@Override
	public void readNBT(Capability<IMana> capability, IMana instance, EnumFacing side, NBTBase nbt) {
		instance.set(((NBTPrimitive) nbt).getFloat());
	}
}

package ca.wescook.homesteadcompanion.events;

import ca.wescook.homesteadcompanion.items.ModItems;
import net.minecraft.entity.EntityLiving;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

public class EventEntitySpawn {
	@SubscribeEvent
	public void entitySpawn(EntityJoinWorldEvent event) {
		checkHoldingRandomTinkers(event);
	}

	// This code attempts to replace mobs holding a RandomTinkers item with a real tool.  For the player version, see ItemRandomTinkers#onUpdate.
	// To test in game:  /summon Zombie ~3 ~ ~ {HandItems:[{Count:1,id:homesteadcompanion:random_tinkers},{}]}
	private void checkHoldingRandomTinkers(EntityJoinWorldEvent event) {
		// Only run on server
		if (event.getEntity().getEntityWorld().isRemote)
			return;

		// If entity isn't living (mob), get out
		if (!(event.getEntity() instanceof EntityLiving))
			return;

		// Get out if TConstruct isn't installed
		if (!Loader.isModLoaded("tconstruct"))
			return;

		// Cast to living entity so we get some useful methods
		EntityLiving entity = (EntityLiving) event.getEntity();

		// Get contents of each hand
		Map<EntityEquipmentSlot, ItemStack> equipment = new HashMap<EntityEquipmentSlot, ItemStack>();
		equipment.put(EntityEquipmentSlot.MAINHAND, entity.getHeldItem(EnumHand.MAIN_HAND));
		equipment.put(EntityEquipmentSlot.OFFHAND, entity.getHeldItem(EnumHand.OFF_HAND));

		// Check and replace
		for (Map.Entry<EntityEquipmentSlot, ItemStack> entry : equipment.entrySet()) {
			if (entry.getValue() != null && entry.getValue().getItem().equals(ModItems.itemRandomTinkers)) // If RandomTinkers item is found
				entity.setItemStackToSlot(entry.getKey(), ModItems.itemRandomTinkers.generateRandomTool()); // Replace it in that inventory slot
		}
	}
}

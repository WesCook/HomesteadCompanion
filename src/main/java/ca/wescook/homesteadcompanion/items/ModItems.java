package ca.wescook.homesteadcompanion.items;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
	public static ItemRandomTinkers itemRandomTinkers;

	public static void registerItems() {
		itemRandomTinkers = new ItemRandomTinkers();
	}

	@SideOnly(Side.CLIENT)
	public static void renderItems() {
		itemRandomTinkers.render();
	}
}

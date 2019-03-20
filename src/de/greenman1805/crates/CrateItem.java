package de.greenman1805.crates;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

public class CrateItem {
	private int slot;
	private ItemStack itemstack;

	public CrateItem(ItemStack itemstack, int slot) {
		this.itemstack = itemstack;
		this.slot = slot;
	}

	public CrateItem(String data) {
		YamlConfiguration config = new YamlConfiguration();
		try {
			config.loadFromString(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		itemstack = config.getItemStack("i", null);
		slot = config.getInt("slot");
	}

	@Override
	public String toString() {
		YamlConfiguration config = new YamlConfiguration();
		config.set("i", itemstack);
		config.set("slot", slot);
		return config.saveToString();
	}

	public ItemStack getItem() {
		return itemstack;
	}

	public int getPlace() {
		return slot;
	}
}

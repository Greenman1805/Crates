package de.greenman1805.crates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;

public class CrateCategory {
	String name;
	ItemStack item;
	public int price;
	List<CrateChest> chests = new ArrayList<CrateChest>();
	public static List<CrateCategory> categories = new ArrayList<CrateCategory>();

	public CrateCategory(String name, ItemStack item, int price) {
		this.name = name;
		this.item = item;
		this.price = price;
		Main.Debug("Lade Kategorie " + name);
		if (Main.plugin.getConfig().isConfigurationSection("Crates." + name + ".crates")) {
			for (String id : Main.plugin.getConfig().getConfigurationSection("Crates." + name + ".crates").getKeys(false)) {
				CrateChest cc = CrateChest.loadChest(name, Integer.parseInt(id));
				chests.add(cc);
			}
		}
		categories.add(this);
	}

	public void addChest(Chest chest) {
		CrateChest cc = new CrateChest(name, chest);
		chests.add(cc);
	}

	public static CrateCategory get(String name) {
		for (CrateCategory category : categories) {
			if (category.name.equalsIgnoreCase(name)) {
				return category;
			}
		}
		return null;
	}

	public static CrateCategory get(ItemStack itemStack) {
		for (CrateCategory category : categories) {
			if (category.item.getItemMeta().getDisplayName().equalsIgnoreCase(itemStack.getItemMeta().getDisplayName())) {
				return category;
			}
		}
		return null;
	}

	public CrateChest getRandomChest() {
		Random rand = new Random();
		if (chests.isEmpty()) {
			Main.Debug("Keine RandomChest gefunden");
			return null;
		}
		CrateChest randomChest = chests.get(rand.nextInt(chests.size()));
		Main.Debug("RandomChest mit ID: " + randomChest.id);
		return randomChest;
	}

}

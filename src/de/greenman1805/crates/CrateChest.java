package de.greenman1805.crates;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.ShulkerBox;
import org.bukkit.inventory.ItemStack;

public class CrateChest {
	String category;
	public static int globalid = 0;
	int id;
	private List<CrateItem> crateitems = new ArrayList<CrateItem>();

	public CrateChest(String category, Chest c) {
		this.category = category;
		this.id = globalid;
		globalid++;
		int slot = 0;
		for (ItemStack i : c.getBlockInventory().getContents()) {
			if (i != null && i.getType() != Material.AIR) {
				CrateItem ci = new CrateItem(i, slot);
				crateitems.add(ci);
			}
			slot++;
		}
		Main.Debug("Neue Chest: " + category + " " + id);
		save();
	}

	private CrateChest(String category, int id) {
		this.id = id;
		this.category = category;
		Main.Debug("Lade Chest " + id + ":");
		List<String> itemStrings = Main.plugin.getConfig().getStringList(("Crates." + category + ".crates." + id + ".items"));
		for (String itemString : itemStrings) {
			Main.Debug("Lade Item " + itemString);
			crateitems.add(new CrateItem(itemString));
		}

	}

	public void setChestItems(ShulkerBox chest) {
		chest.getInventory().clear();
		for (CrateItem ci : crateitems) {
			Main.Debug("Setze item in Truhe " + ci.getItem().getType() + " " + ci.getPlace());
			chest.getInventory().setItem(ci.getPlace(), ci.getItem());
		}
	}
	
	public void setChestItems(Chest chest) {
		chest.getBlockInventory().clear();
		for (CrateItem ci : crateitems) {
			Main.Debug("Setze item in Truhe " + ci.getItem().getType() + " " + ci.getPlace());
			chest.getBlockInventory().setItem(ci.getPlace(), ci.getItem());
		}
	}

	public static CrateChest loadChest(String category, int id) {
		return new CrateChest(category, id);
	}

	public void save() {
		List<String> items = new ArrayList<String>();
		for (CrateItem ci : crateitems) {
			items.add(ci.toString());
		}
		Main.plugin.getConfig().set("Crates." + category + ".crates." + id + ".items", items);
		Main.plugin.getConfig().set("globalid", globalid);
		Main.plugin.saveConfig();
		Main.Debug("Chest gespeichert: " + this.id);
	}

}

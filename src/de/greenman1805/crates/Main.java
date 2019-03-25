package de.greenman1805.crates;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static String prefix = "§8[§6Crates§8] §f";
	public static Main plugin;
	public static boolean debug = false;
	CrateCategory iron;
	CrateCategory gold;
	CrateCategory diamond;
	

	@Override
	public void onEnable() {
		plugin = this;
		CrateChest.globalid = getConfig().getInt("globalid");
		getCommand("crates").setExecutor(new CrateCommand());

		ItemStack iron_item = new ItemStack(Material.LIGHT_GRAY_SHULKER_BOX, 1);
		ItemStack gold_item = new ItemStack(Material.YELLOW_SHULKER_BOX, 1);
		ItemStack diamond_item = new ItemStack(Material.LIGHT_BLUE_SHULKER_BOX, 1);

		 setItemName(iron_item, "§9* §r§7Iron §fLootbox §9*", null);
		 setItemName(gold_item, "§9* §r§eGold §fLootbox §9*", null);
		 setItemName(diamond_item, "§9* §r§bDiamond §fLootbox §9*", null);

		iron = new CrateCategory("iron", iron_item, 5000);
		gold = new CrateCategory("gold", gold_item, 20000);
		diamond = new CrateCategory("diamond", diamond_item, 50000);
		
		
		
		
		new CratePlacement();
	}
	
	
	@Override
	public void onDisable() {
	}

	public static void Debug(String debugmessage) {
		if (debug) {
			System.out.println("[Crates] " + debugmessage);
		}
	}

	public static void setItemName(ItemStack item, String name, ArrayList<String> lore_list) {
		ItemMeta meta;
		meta = item.getItemMeta();
		meta.setLore(lore_list);
		meta.setDisplayName(name);
		item.setItemMeta(meta);
	}

}

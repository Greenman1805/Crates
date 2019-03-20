package de.greenman1805.crates;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

public class CrateGui implements Listener{
	Inventory inv;
	
	
	public CrateGui(Player p) {
		Bukkit.getServer().getPluginManager().registerEvents(this, Main.plugin);
		inv = Bukkit.createInventory(null, 27, "§6Lootbox §fShop");
		
		ItemStack gap = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
		Main.setItemName(gap, "§f", null);
		inv.setItem(0, gap);
		inv.setItem(1, gap);
		inv.setItem(7, gap);
		inv.setItem(8, gap);
		inv.setItem(9, gap);
		inv.setItem(0, gap);
		inv.setItem(0, gap);
		inv.setItem(25, gap);
		inv.setItem(26, gap);
		
		
		ItemStack iron = Main.plugin.iron.item.clone();
		ItemStack gold = Main.plugin.gold.item.clone();
		ItemStack diamond = Main.plugin.diamond.item.clone();
		
		
		
		
		
		inv.setItem(11, iron);
		inv.setItem(13, gold);
		inv.setItem(15, diamond);
		
		
		p.openInventory(inv);
		
		
		
		
	}

}

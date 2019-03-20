package de.greenman1805.crates;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
public class CratePlacement implements Listener {

	public CratePlacement() {
		Bukkit.getServer().getPluginManager().registerEvents(this, Main.plugin);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		ItemStack current = p.getInventory().getItemInMainHand();
		if (current != null && current.getType() != Material.AIR && current.getItemMeta().getDisplayName() != null) {
			CrateCategory cc = CrateCategory.get(current);
			if (cc != null) {
				Main.Debug("Kategorie gefunden: " + cc.name);
				if (e.canBuild() && !e.isCancelled()) {
					current.setType(Material.AIR);
					e.setCancelled(true);
					Location startingLocation = e.getBlock().getLocation().add(0.5, -1.3, 0.5);
					ArmorStand as = (ArmorStand) e.getBlock().getLocation().getWorld().spawn(startingLocation.clone(), ArmorStand.class);

					as.setGravity(false);
					as.setCanPickupItems(false);
					as.setCustomName(cc.item.getItemMeta().getDisplayName());
					as.setCustomNameVisible(true);
					as.setVisible(false);
					as.setHelmet(cc.item);
					
					
					
					CrateAnimation ca = new CrateAnimation(p, as, cc);
					int taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, ca, 0, 1);
					ca.setId(taskid);


				} else {
					p.sendMessage(Main.prefix + "§4Du kannst diese Lootbox hier nicht platzieren!");
				}

			}
		}

	}
	
	




}

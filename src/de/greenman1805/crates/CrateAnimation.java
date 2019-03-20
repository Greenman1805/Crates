package de.greenman1805.crates;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.util.EulerAngle;


public class CrateAnimation implements Runnable {
	int id;
	Player p;
	ArmorStand as;
	Location startingLocation;
	EulerAngle headPose;
	boolean up = false;
	CrateCategory cc;

	public CrateAnimation(Player p, ArmorStand as, CrateCategory cc) {
		this.cc = cc;
		this.startingLocation = as.getLocation().clone();
		this.headPose = as.getHeadPose();
		this.p = p;
		this.as = as;
		p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
		p.playSound(p.getLocation(), Sound.ITEM_ELYTRA_FLYING, 1, 1);
	}

	public void setId(int id) {
		this.id = id;
	}

	public void stop() {
		Bukkit.getScheduler().cancelTask(id);
	}

	@Override
	public void run() {
		if (as != null) {
			Location l = as.getLocation();
			as.setHeadPose(as.getHeadPose().add(0, 0.1, 0));
			as.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, l.getX(), l.getY() + 1.5, l.getZ(), 5, 0.3, 0, 0.3);
			if (!up) {
				if (l.getBlockY() - startingLocation.getBlockY() <= 4) {
					as.teleport(l.add(0, 0.1, 0));
				} else {
					up = true;
					p.stopSound(Sound.ITEM_ELYTRA_FLYING);
					as.setGravity(true);
				}
			} else if (as.isOnGround()) {
				Block b = as.getLocation().getBlock();
				b.setType(Material.CHEST);
				Chest chest = (Chest) b.getState();
				cc.getRandomChest().setChestItems(chest);
				p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
				as.getWorld().spawnParticle(Particle.REDSTONE, l.getX(), l.getY() + 1, l.getZ(), 300, 0.5, 0.5, 0.5);
				Bukkit.getServer().broadcastMessage(Main.prefix + p.getDisplayName() + "§f hat eine " + cc.item.getItemMeta().getDisplayName() + "§f geöffnet!");
				as.remove();
				as = null;
			}
		} else {
			stop();
		}
	}

}

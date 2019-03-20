package de.greenman1805.crates;

import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CrateCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;

			if (cmd.getName().equalsIgnoreCase("crates")) {

				
				if (args.length == 1)  {
					if (args[0].equalsIgnoreCase("shop")) {
						new CrateGui(p);
					}
				}
				
			else if (args.length == 2) {
					if (args[0].equalsIgnoreCase("createchest")) {
						if (p.hasPermission("crates.admin")) {
							String category = args[1];
							CrateCategory cc = CrateCategory.get(category);
							if (cc != null) {
								Block b = p.getTargetBlock(null, 5);
								if (b.getState() instanceof Chest) {
									Chest chest = (Chest) b.getState();
									cc.addChest(chest);
									p.sendMessage(Main.prefix + "§aKiste hinzugefügt!");
								} else {
									p.sendMessage(Main.prefix + "§4Du musst eine Kiste ansehen!");
								}
							} else {
								p.sendMessage(Main.prefix + "§4Kategorie nicht gefunden!");
							}
						} else {
							p.sendMessage(Main.prefix + "§4Keine Rechte!");
						}

					}

					if (args[0].equalsIgnoreCase("get")) {
						if (p.hasPermission("crates.admin")) {
							String category = args[1];
							CrateCategory cc = CrateCategory.get(category);
							if (cc != null) {
								p.getInventory().addItem(cc.item);
							} else {
								p.sendMessage(Main.prefix + "§4Kategorie nicht gefunden!");
							}
						} else {
							p.sendMessage(Main.prefix + "§4Keine Rechte!");
						}

					}
				}
			}
		}
		return false;
	}
}

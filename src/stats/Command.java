package stats;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Command implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Only players can execute this command!");
		}
		
		Player p = (Player) sender;
		
		Player target;
		
		if(args.length == 0) {
			target = p;
		} else {
			target = Bukkit.getPlayer(args[0]);
			
			if(target == null) {
				p.sendMessage("§cPlayer not found.");
				return true;
			}
		}
		Inventory inv = Bukkit.createInventory(null, 27, "§8Stats");
		ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) playerSkull.getItemMeta();
		meta.setOwner(p.getName());
		meta.setDisplayName("§7" + target.getName() + "'s Stats");
		playerSkull.setItemMeta(meta);
		
		
		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		ItemMeta swordMeta = sword.getItemMeta();

		int kills = target.getStatistic(Statistic.PLAYER_KILLS);
		int deaths = target.getStatistic(Statistic.DEATHS);
		
		double kd;
		
		if(deaths == 0) {
			kd = kills;
		} else {
			kd = (double) kills / deaths;
		}
		
		String FinalKD = String.format("%.2f", kd);
		
		
		swordMeta.setDisplayName("§cKills");
		swordMeta.setLore(Arrays.asList("§7Total Kills: §c" + target.getStatistic(Statistic.PLAYER_KILLS)));
		
		ItemStack bone = new ItemStack(Material.BONE);
		ItemMeta boneMeta = bone.getItemMeta();
		
		boneMeta.setDisplayName("§cDeaths");
		boneMeta.setLore(Arrays.asList("§7Total deaths: §c" + target.getStatistic(Statistic.DEATHS)));
		
		ItemStack netherstar = new ItemStack(Material.NETHER_STAR);
		ItemMeta netherstarMeta = netherstar.getItemMeta();
		
		netherstarMeta.setDisplayName("§cK/D Ratio");
		netherstarMeta.setLore(Arrays.asList("§7K/D Ratio: §c" + FinalKD));
		
		sword.setItemMeta(swordMeta);
		bone.setItemMeta(boneMeta);
		netherstar.setItemMeta(netherstarMeta);
		
		inv.setItem(4, playerSkull);
		inv.setItem(12, sword);
		inv.setItem(14, bone);
		inv.setItem(22, netherstar);
		
		
		target.openInventory(inv);

		target.playSound(p.getLocation(), Sound.CHEST_OPEN, 1.0f, 1.0f);
		
		return true;
	}

}

package me.chaseoes.restrictedtnt;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class RestrictedTNT extends JavaPlugin implements Listener {
	
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable() {
		reloadConfig();
		saveConfig();
	}
	
	@EventHandler(ignoreCancelled=true, priority=EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent event) {
		if (event.getBlock().getType() == Material.TNT && !event.getPlayer().hasPermission("restrictedtnt.place")) {
			event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("message")));
			event.setCancelled(true);
		}
	}

}

package org.hnplugin.hnplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class HNListener implements Listener {
    FileConfiguration lang = HNPlugin.main.loadLang();
    FileConfiguration modules = HNPlugin.main.loadModules();
    
    @EventHandler
    public void playerJoin(PlayerJoinEvent Join) {
        boolean EnableJoinMessage = modules.getBoolean("JoinMessage.Enable");
        if (EnableJoinMessage) {
            String player = Join.getPlayer().getName();
            Join.setJoinMessage(null);
            Bukkit.broadcastMessage(lang.getString("JoinMessage").replace("%player%", player).replace("&", "§").replace("%prefix%", (lang.getString("Prefix"))));
        }
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent Quit) {

        boolean EnableQuitMessage = modules.getBoolean("QuitMessage.Enable");
        if (EnableQuitMessage) {
            String player2 = Quit.getPlayer().getName();
            Quit.setQuitMessage(null);
            Bukkit.broadcastMessage(lang.getString("QuitMessage").replace("%player%", player2).replace("&", "§").replace("%prefix%", lang.getString("Prefix")));
        }
    }

    @EventHandler
    public void playerDeath(PlayerDeathEvent Death) {
        boolean EnableDeathBroadcast = modules.getBoolean("DeathBroadcast.Enable");
        boolean EnableDeathMsg = modules.getBoolean("DeathMsg.Enable");
        Location death_loc = Death.getEntity().getLocation();
        String playerName = Death.getEntity().getName();
        String prefix = ChatColor.translateAlternateColorCodes('&', lang.getString("Prefix"));
        if (EnableDeathBroadcast) {
            String WorldDisplay = death_loc.getWorld().getName();
            for(String display : lang.getConfigurationSection("WorldDisplayList").getKeys(false)) {
                if(death_loc.getWorld().getName() == display) {
                    WorldDisplay = lang.getString("WorldDisplayList." + display, death_loc.getWorld().getName());
                    break;
                }
            }
            String broadcastMsg = ChatColor.translateAlternateColorCodes('&', lang.getString("DeathBroadcast")
                    .replace("%player%", playerName)
                    .replace("%prefix%", prefix)
                    .replace("%death_x%", String.valueOf(death_loc.getBlockX()))
                    .replace("%death_y%", String.valueOf(death_loc.getBlockY()))
                    .replace("%death_z%", String.valueOf(death_loc.getBlockZ()))
                    .replace("%death_world%", WorldDisplay));
            Death.setDeathMessage(broadcastMsg);
        }

        if (EnableDeathMsg) {
            String WorldDisplay = death_loc.getWorld().getName();
            for(String display : lang.getConfigurationSection("WorldDisplayList").getKeys(false)) {
                if(death_loc.getWorld().getName() == display) {
                    WorldDisplay = lang.getString("WorldDisplayList." + display, death_loc.getWorld().getName());
                    break;
                }
            }
            String privateMsg = ChatColor.translateAlternateColorCodes('&', lang.getString("DeathMsg")
                    .replace("%death_y%", String.valueOf(death_loc.getBlockY()))
                    .replace("%player%", playerName)
                    .replace("%prefix%", prefix)
                    .replace("%death_x%", String.valueOf(death_loc.getBlockX()))
                    .replace("%death_z%", String.valueOf(death_loc.getBlockZ()))
                    .replace("%death_world%", WorldDisplay));
            Death.getEntity().sendMessage(privateMsg);
        }
    }
}
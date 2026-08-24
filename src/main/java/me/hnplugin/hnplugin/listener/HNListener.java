package me.hnplugin.hnplugin.listener;

import me.hnplugin.hnplugin.manager.config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import me.hnplugin.hnplugin.util;

public class HNListener implements Listener {
    FileConfiguration lang = config.loadLang();
    FileConfiguration modules = config.loadModules();

    @EventHandler
    public void playerJoin(PlayerJoinEvent Join) {
        boolean EnableJoinMessage = modules.getBoolean("JoinMessage.Enable");
        if (EnableJoinMessage) {
            String player = Join.getPlayer().getName();
            Join.setJoinMessage(null);
            String joinMsg = lang.getString("JoinMessage")
                    .replace("%player%", player)
                    .replace("%prefix%", lang.getStringList("Prefix").get(0));
            Bukkit.broadcastMessage(util.format(Join.getPlayer(), joinMsg));
        }
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent Quit) {

        boolean EnableQuitMessage = modules.getBoolean("QuitMessage.Enable");
        if (EnableQuitMessage) {
            String player2 = Quit.getPlayer().getName();
            Quit.setQuitMessage(null);
            String quitMsg = lang.getString("QuitMessage")
                    .replace("%player%", player2)
                    .replace("%prefix%", lang.getStringList("Prefix").get(0));
            Bukkit.broadcastMessage(util.format(Quit.getPlayer(), quitMsg));
        }
    }

    @EventHandler
    public void playerDeath(PlayerDeathEvent Death) {
        boolean EnableDeathBroadcast = modules.getBoolean("DeathBroadcast.Enable");
        boolean EnableDeathMsg = modules.getBoolean("DeathMsg.Enable");
        Location death_loc = Death.getEntity().getLocation();
        String playerName = Death.getEntity().getName();
        String prefix = ChatColor.translateAlternateColorCodes('&', lang.getStringList("Prefix").get(0));
        if (EnableDeathBroadcast) {
            String WorldDisplay = death_loc.getWorld().getName();
            for(String s : lang.getConfigurationSection("WorldDisplayList").getKeys(false)) {
                if(death_loc.getWorld().getName().equals(s)) {
                    WorldDisplay = lang.getStringList("WorldDisplayList." + s).get(0);
                    break;
                }
            }
            String broadcastMsg = lang.getString("DeathBroadcast")
                    .replace("%player%", playerName)
                    .replace("%prefix%", prefix)
                    .replace("%death_x%", String.valueOf(death_loc.getBlockX()))
                    .replace("%death_y%", String.valueOf(death_loc.getBlockY()))
                    .replace("%death_z%", String.valueOf(death_loc.getBlockZ()))
                    .replace("%death_world%", WorldDisplay);
            Death.setDeathMessage(util.format(Death.getEntity(), broadcastMsg));
        }

        if (EnableDeathMsg) {
            String WorldDisplay = death_loc.getWorld().getName();
            for(String s : lang.getConfigurationSection("WorldDisplayList").getKeys(false)) {
                if(death_loc.getWorld().getName().equals(s)) {
                    WorldDisplay = lang.getStringList("WorldDisplayList." + s).get(0);
                    break;
                }
            }
            String privateMsg = lang.getString("DeathMsg")
                    .replace("%player%", playerName)
                    .replace("%prefix%", prefix)
                    .replace("%death_x%", String.valueOf(death_loc.getBlockX()))
                    .replace("%death_y%", String.valueOf(death_loc.getBlockY()))
                    .replace("%death_z%", String.valueOf(death_loc.getBlockZ()))
                    .replace("%death_world%", WorldDisplay);
            util.sendMessage(Death.getEntity(), privateMsg);
        }
    }
}

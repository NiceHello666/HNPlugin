package org.hnplugin.hnplugin;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;

public class HNListener implements Listener {
    @EventHandler
    public void playerJoin(PlayerJoinEvent Join) {
        File modules = new File(org.hnplugin.hnplugin.HNPlugin.getPlugin(org.hnplugin.hnplugin.HNPlugin.class).getDataFolder(), "modules.yml");
        FileConfiguration modules2 = YamlConfiguration.loadConfiguration(modules);
        File lang = new File(org.hnplugin.hnplugin.HNPlugin.getPlugin(org.hnplugin.hnplugin.HNPlugin.class).getDataFolder(), "lang.yml");
        FileConfiguration lang2 = YamlConfiguration.loadConfiguration(lang);
        boolean EnableJoinMessage = modules2.getBoolean("JoinMessage.Enable");
        if (EnableJoinMessage) {
            String player = Join.getPlayer().getName();
            System.out.println(lang2.getString("JoinMessage").replace("%player%", player).replace("&", "§").replace("%prefix%", (lang2.getString("Prefix"))));
            Join.setJoinMessage(lang2.getString("JoinMessage").replace("%player%", player).replace("&", "§").replace("%prefix%", (lang2.getString("Prefix"))));
        }
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent Quit) {
        File modules = new File(org.hnplugin.hnplugin.HNPlugin.getPlugin(org.hnplugin.hnplugin.HNPlugin.class).getDataFolder(), "modules.yml");
        FileConfiguration modules2 = YamlConfiguration.loadConfiguration(modules);
        File lang = new File(org.hnplugin.hnplugin.HNPlugin.getPlugin(org.hnplugin.hnplugin.HNPlugin.class).getDataFolder(), "lang.yml");
        FileConfiguration lang2 = YamlConfiguration.loadConfiguration(lang);
        boolean EnableQuitMessage = modules2.getBoolean("QuitMessage.Enable");
        if (EnableQuitMessage) {
            String player2 = Quit.getPlayer().getName();
            Quit.setQuitMessage(lang2.getString("QuitMessage").replace("%player%", player2).replace("&", "§").replace("%prefix%", lang2.getString("Prefix")));
        }
    }

    @EventHandler
    public void playerDeath(PlayerDeathEvent Death) {
        File modules = new File(HNPlugin.getPlugin(HNPlugin.class).getDataFolder(), "modules.yml");
        FileConfiguration modules2 = YamlConfiguration.loadConfiguration(modules);
        File lang = new File(HNPlugin.getPlugin(HNPlugin.class).getDataFolder(), "lang.yml");
        FileConfiguration lang2 = YamlConfiguration.loadConfiguration(lang);

        boolean EnableDeathBroadcast = modules2.getBoolean("DeathBroadcast.Enable");
        boolean EnableDeathMsg = modules2.getBoolean("DeathMsg.Enable");
        Location death_loc = Death.getEntity().getLocation();
        String playerName = Death.getEntity().getName();
        String prefix = lang2.getString("Prefix", "").replace("&", "§");
        if (EnableDeathBroadcast) {
            String broadcastMsg = lang2.getString("DeathBroadcast", "")
                    .replace("%player%", playerName)
                    .replace("%prefix%", prefix)
                    .replace("%death_x%", String.valueOf(death_loc.getBlockX()))
                    .replace("%death_y%", String.valueOf(death_loc.getBlockY()))
                    .replace("%death_z%", String.valueOf(death_loc.getBlockZ()))
                    .replace("&", "§");
            Death.setDeathMessage(broadcastMsg);
        }

        if (EnableDeathMsg) {
            String privateMsg = lang2.getString("DeathMsg", "")
                    .replace("%player%", playerName)
                    .replace("%prefix%", prefix)
                    .replace("%death_x%", String.valueOf(death_loc.getBlockX()))
                    .replace("%death_y%", String.valueOf(death_loc.getBlockY()))
                    .replace("%death_z%", String.valueOf(death_loc.getBlockZ()))
                    .replace("&", "§");
            Death.getEntity().sendMessage(privateMsg);
        }
    }
}
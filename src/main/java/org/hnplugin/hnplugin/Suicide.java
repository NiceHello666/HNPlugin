package org.hnplugin.hnplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Suicide implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        File lang = new File(org.hnplugin.hnplugin.HNPlugin.getPlugin(org.hnplugin.hnplugin.HNPlugin.class).getDataFolder(), "lang.yml");
        FileConfiguration lang2 = YamlConfiguration.loadConfiguration(lang);
        File modules = new File(org.hnplugin.hnplugin.HNPlugin.getPlugin(org.hnplugin.hnplugin.HNPlugin.class).getDataFolder(), "modules.yml");
        FileConfiguration modules2 = YamlConfiguration.loadConfiguration(modules);
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(lang2.getString("CantUseInConsole")
                    .replace("%prefix%", lang2.getString("Prefix"))
                    .replace("&", "§"));
            return true;
        } else {
            if (commandSender.hasPermission("hnplugin.command.suicide")) {
                if (modules2.getBoolean("Suicide.Enable")) {
                    Player player = (Player) commandSender;
                    player.setHealth(0);
                    Bukkit.broadcastMessage(lang2
                            .getString("SuicideBroadcast")
                            .replace("%prefix%", lang2.getString("Prefix"))
                            .replace("%player%", commandSender.getName())
                            .replace("&", "§"));
                    return true;
                }
            }
            commandSender.sendMessage(lang2.getString("NoPermission")
                    .replace("%prefix%", lang2.getString("Prefix"))
                    .replace("&", "§"));
            return true;
        }
    }
}

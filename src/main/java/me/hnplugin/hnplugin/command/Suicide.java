package org.hnplugin.hnplugin.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.hnplugin.hnplugin.HNPlugin;

public class Suicide implements CommandExecutor {

    FileConfiguration lang = HNPlugin.main.loadLang();
    FileConfiguration modules = HNPlugin.main.loadModules();
    
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', lang.getString("CantUseInConsole")
                    .replace("%prefix%", lang.getString("Prefix"))));
            return true;
        } else {
            if (commandSender.hasPermission("hnplugin.command.suicide")) {
                if (modules.getBoolean("Suicide.Enable")) {
                    Player player = (Player) commandSender;
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', lang
                            .getString("SuicideBroadcast")
                            .replace("%prefix%", lang.getString("Prefix"))
                            .replace("%player%", commandSender.getName())));
                    player.setHealth(0);
                    return true;
                }
            }
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', lang.getString("NoPermission")
                    .replace("%prefix%", lang.getString("Prefix"))));
            return true;
        }
    }
}

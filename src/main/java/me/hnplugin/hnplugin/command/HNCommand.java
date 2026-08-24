package org.hnplugin.hnplugin.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.hnplugin.hnplugin.HNPlugin;

public class HNCommand implements CommandExecutor {

    FileConfiguration lang = HNPlugin.loadLang();


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission("hnplugin.command.main")) {
            if (strings.length >= 2 && strings[0].equals("bc")) {
                StringBuilder message = new StringBuilder();
                for (int i = 1; i < strings.length; i++) {
                    message.append(strings[i]);
                    if (i < strings.length - 1) {
                        message.append(" ");
                    }
                }
                Bukkit.broadcastMessage(
                        ChatColor.translateAlternateColorCodes('&',
                        lang.getString("BCPrefix").replace("%prefix%", lang.getString("Prefix")) + message.toString())
                );

                return true;
            }
            if (strings.length == 1 && strings[0].equals("help")) {
                HNPlugin.helpmsg(commandSender);
                return true;
            }
            if (strings.length == 1 && strings[0].equals("reload")) {
                java.util.logging.Logger logger = java.util.logging.Logger.getLogger("");
                java.util.logging.Level oldLevel = logger.getLevel();
                logger.setLevel(java.util.logging.Level.OFF);
                HNPlugin.saveResource("lang.yml", false);
                HNPlugin.saveResource("modules.yml", false);
                logger.setLevel(oldLevel);
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', lang.getString("ReloadMessage")
                        .replace("%prefix%", lang.getString("Prefix"))));
                return true;
            }
            HNPlugin.helpmsg(commandSender);
            return true;
        }
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', lang.getString("NoPermission")
                .replace("%prefix%", lang.getString("Prefix"))));
        return true;
    }
}
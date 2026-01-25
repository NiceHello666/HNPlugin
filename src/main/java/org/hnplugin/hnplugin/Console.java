package org.hnplugin.hnplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class Console implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        FileConfiguration modules = HNPlugin.main.loadModules();
        FileConfiguration lang = HNPlugin.main.loadLang();

        if(modules.getBoolean("Console.Enable")) {
            if (commandSender.hasPermission("hnplugin.command.console")) {
                if (strings.length > 0) {
                    StringBuilder cmd = new StringBuilder();
                    for (int i = 0; i < strings.length; i++) {
                        cmd.append(strings[i]);
                        if (i < strings.length - 1) {
                            cmd.append(" ");
                        }
                    }
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), cmd.toString());
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', lang.getString("ConsoleCommandFeedBack")
                            .replace("%prefix%", lang.getString("Prefix"))
                            .replace("%consolecmd%", cmd.toString())));
                } else {
                    HNPlugin.main.helpmsg(commandSender);
                }
            }
        }
        return false;
    }
}

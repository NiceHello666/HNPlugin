package org.hnplugin.hnplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Sudo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        FileConfiguration modules = HNPlugin.main.loadModules();
        FileConfiguration lang = HNPlugin.main.loadLang();

        if (modules.getBoolean("Sudo.Enable")) {
            if (commandSender.hasPermission("hnplugin.command.sudo")) {

                if (strings.length > 1) {
                    Player pl = Bukkit.getPlayer(strings[0]);
                    if (pl != null) {
                        StringBuilder cmd = new StringBuilder();
                        for (int i = 1; i < strings.length; i++) {
                            cmd.append(strings[i]);
                            if (i < strings.length - 1) {
                                cmd.append(" ");
                            }
                        }
                        Bukkit.dispatchCommand(pl, cmd.toString());
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', lang.getString("SudoFeedBack")
                                .replace("%prefix%", lang.getString("Prefix"))
                                .replace("%sudoplayer%", strings[0])
                                .replace("%sudocmd%", cmd.toString())));
                    } else {
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', lang.getString("WrongPlayer")
                                .replace("%prefix%", lang.getString("Prefix"))));
                    }
                } else {
                    HNPlugin.main.helpmsg(commandSender);
                }
            } else {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', lang.getString("NoPermission")
                        .replace("%prefix%", lang.getString("Prefix"))));
            }
        }
        return false;
    }
}


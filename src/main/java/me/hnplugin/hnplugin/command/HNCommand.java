package me.hnplugin.hnplugin.command;

import me.hnplugin.hnplugin.manager.config;
import me.hnplugin.hnplugin.util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HNCommand implements CommandExecutor {

    FileConfiguration lang = config.loadLang();


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
                Player player = commandSender instanceof Player ? (Player) commandSender : null;
                util.broadcastMessage(
                        String.join("", lang.getStringList("BCPrefix")).replace("%prefix%", lang.getStringList("Prefix").get(0)) + message.toString(),
                        player
                );

                return true;
            }
            if (strings.length == 1 && strings[0].equals("help")) {
                config.helpmsg(commandSender);
                return true;
            }
            if (strings.length == 1 && strings[0].equals("reload")) {
                java.util.logging.Logger logger = java.util.logging.Logger.getLogger("");
                java.util.logging.Level oldLevel = logger.getLevel();
                logger.setLevel(java.util.logging.Level.OFF);
                config.saveResource("lang.yml", false);
                config.saveResource("modules.yml", false);
                logger.setLevel(oldLevel);
                List<String> reloadMsg = new ArrayList<>(lang.getStringList("ReloadMessage"));
                reloadMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0)));
                util.sendMessage(commandSender, reloadMsg);
                return true;
            }
            config.helpmsg(commandSender);
            return true;
        }
        List<String> noPermMsg = new ArrayList<>(lang.getStringList("NoPermission"));
        noPermMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0)));
        util.sendMessage(commandSender, noPermMsg);
        return true;
    }
}
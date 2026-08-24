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

public class Suicide implements CommandExecutor {

    FileConfiguration lang = config.loadLang();
    FileConfiguration modules = config.loadModules();
    
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            List<String> cantUseMsg = new ArrayList<>(lang.getStringList("CantUseInConsole"));
            cantUseMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0)));
            util.sendMessage(commandSender, cantUseMsg);
            return true;
        } else {
            if (commandSender.hasPermission("hnplugin.command.suicide")) {
                if (modules.getBoolean("Suicide.Enable")) {
                    Player player = (Player) commandSender;
                    List<String> suicideMsg = new ArrayList<>(lang.getStringList("SuicideBroadcast"));
                    suicideMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0))
                            .replace("%player%", commandSender.getName()));
                    util.broadcastMessage(suicideMsg, player);
                    player.setHealth(0);
                    return true;
                }
            }
            List<String> noPermMsg = new ArrayList<>(lang.getStringList("NoPermission"));
            noPermMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0)));
            util.sendMessage(commandSender, noPermMsg);
            return true;
        }
    }
}
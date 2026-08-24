package me.hnplugin.hnplugin.command;

import me.hnplugin.hnplugin.manager.config;
import me.hnplugin.hnplugin.util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Console implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        FileConfiguration modules = config.loadModules();
        FileConfiguration lang = config.loadLang();

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
                    List<String> consoleMsg = new ArrayList<>(lang.getStringList("ConsoleCommandFeedBack"));
                    consoleMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0))
                            .replace("%consolecmd%", cmd.toString()));
                    util.sendMessage(commandSender, consoleMsg);
                } else {
                    config.helpmsg(commandSender);
                }
            }
        }
        return false;
    }
}
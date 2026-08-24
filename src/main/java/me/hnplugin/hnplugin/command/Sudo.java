package me.hnplugin.hnplugin.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import me.hnplugin.hnplugin.util;

import java.util.ArrayList;
import java.util.List;

import me.hnplugin.hnplugin.manager.config;
public class Sudo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        FileConfiguration modules = config.loadModules();
        FileConfiguration lang = config.loadLang();

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
                        List<String> sudoMsg = new ArrayList<>(lang.getStringList("SudoFeedBack"));
                        sudoMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0))
                                .replace("%sudoplayer%", strings[0])
                                .replace("%sudocmd%", cmd.toString()));
                        util.sendMessage(commandSender, sudoMsg);
                    } else {
                        List<String> wrongPlayerMsg = new ArrayList<>(lang.getStringList("WrongPlayer"));
                        wrongPlayerMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0)));
                        util.sendMessage(commandSender, wrongPlayerMsg);
                    }
                } else {
                    config.helpmsg(commandSender);
                }
            } else {
                List<String> noPermMsg = new ArrayList<>(lang.getStringList("NoPermission"));
                noPermMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0)));
                util.sendMessage(commandSender, noPermMsg);
            }
        }
        return false;
    }
}
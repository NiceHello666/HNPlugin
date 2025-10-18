package org.hnplugin.hnplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class HNCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        File lang = new File(org.hnplugin.hnplugin.HNPlugin.getPlugin(org.hnplugin.hnplugin.HNPlugin.class).getDataFolder(),"lang.yml");
        FileConfiguration lang2 = YamlConfiguration.loadConfiguration(lang);
        if (strings.length >= 2 && strings[0].equals("bc")) {
            StringBuilder message = new StringBuilder();
            for (int i = 1; i < strings.length; i++) {
                message.append(strings[i]);
                if (i < strings.length - 1) {
                    message.append(" ");
                }
            }
            String formattedMessage = message.toString()
                    .replace("&", "§");
            String bcprefix = lang2.getString(("BCPrefix"))
                    .replace("%prefix%", lang2.getString("Prefix"))
                    .replace("&", "§");
            Bukkit.broadcastMessage(bcprefix + formattedMessage);

            return true;
        }
        if (strings.length == 1 && strings[0].equals("help")) {
            commandSender.sendMessage((lang2.getString("Prefix") + " §6指令帮助").replace("&", "§"));
            commandSender.sendMessage("§e/hnp help - 显示这条消息");
            commandSender.sendMessage("§e/hnp reload - 重载插件");
            commandSender.sendMessage("§e/hnp bc <内容> - 发送公告");
            return true;
        }
        if (strings.length == 1 && strings[0].equals("reload")) {
            java.util.logging.Logger logger = java.util.logging.Logger.getLogger("");
            java.util.logging.Level oldLevel = logger.getLevel();
            logger.setLevel(java.util.logging.Level.OFF);
            HNPlugin.main.saveResource("lang.yml", false);
            HNPlugin.main.saveResource("modules.yml", false);
            logger.setLevel(oldLevel);
            commandSender.sendMessage(lang2.getString("ReloadMessage").replace("%prefix%", lang2.getString("Prefix")).replace("&", "§"));
            return true;
        }
        commandSender.sendMessage(lang2.getString("Prefix").replace("&", "§") + " §c未知指令，输入 /hnp help 查看帮助");
        return true;
    }
}
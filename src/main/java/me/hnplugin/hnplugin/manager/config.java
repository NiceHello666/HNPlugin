package me.hnplugin.hnplugin.manager;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import me.hnplugin.hnplugin.HNPlugin;
import me.hnplugin.hnplugin.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class config {
    public static FileConfiguration loadModules() {
        return loadConfig("modules.yml");
    }

    public static FileConfiguration loadLang() {
        return loadConfig("lang.yml");
    }

    private static FileConfiguration loadConfig(String name) {
        File file = new File(HNPlugin.main.getDataFolder(), name);
        if (!file.exists()) {
            HNPlugin.main.saveResource(name, false);
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        InputStream defaultStream = HNPlugin.main.getResource(name);
        if (defaultStream != null) {
            FileConfiguration defaults = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream, StandardCharsets.UTF_8));
            if (mergeMissingKeys(defaults, config)) {
                try {
                    config.save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return config;
    }

    private static boolean mergeMissingKeys(FileConfiguration defaults, FileConfiguration config) {
        boolean modified = false;
        Set<String> keys = defaults.getKeys(true);
        for (String key : keys) {
            if (!config.contains(key)) {
                config.set(key, defaults.get(key));
                modified = true;
            }
        }
        return modified;
    }

    public static void saveResource(String filename, boolean replace) {
        HNPlugin.main.saveResource(filename, replace);
    }

    public static void helpmsg(CommandSender commandSender) {
        FileConfiguration lang = loadLang();
        List<String> messages = new ArrayList<>();
        messages.add(lang.getStringList("Prefix").get(0) + " &6指令帮助");
        messages.add("&e/hnp help - 显示这条消息");
        messages.add("&e/hnp reload - 重载插件");
        messages.add("&e/hnp bc <内容> - 发送公告");
        messages.add("&e/suicide - 原地自杀");
        messages.add("&e/sudo <玩家> <指令,不需要斜杠> - 强制玩家执行指令");
        messages.add("&e/console <指令,不需要斜杠> - 以控制台身份执行指令");
        util.sendMessage(commandSender, messages);
    }
}

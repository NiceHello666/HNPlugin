package org.hnplugin.hnplugin;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class HNPlugin extends JavaPlugin {

    static HNPlugin main;

    public FileConfiguration loadModules() {
        File modulesfile = new File(org.hnplugin.hnplugin.HNPlugin.getPlugin(org.hnplugin.hnplugin.HNPlugin.class).getDataFolder(), "modules.yml");
        FileConfiguration modules = YamlConfiguration.loadConfiguration(modulesfile);
        return modules;
    }
    public FileConfiguration loadLang() {
        File langfile = new File(org.hnplugin.hnplugin.HNPlugin.getPlugin(org.hnplugin.hnplugin.HNPlugin.class).getDataFolder(), "lang.yml");
        FileConfiguration lang = YamlConfiguration.loadConfiguration(langfile);
        return lang;
    }
    public void helpmsg(CommandSender commandSender) {
        FileConfiguration lang = loadLang();
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', lang.getString("Prefix") + " &6指令帮助"));
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/hnp help - 显示这条消息"));
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/hnp reload - 重载插件"));
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/hnp bc <内容> - 发送公告"));
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/suicide - 原地自杀"));
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/sudo <玩家> <指令,不需要斜杠> - 强制玩家执行指令"));
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e/console <指令,不需要斜杠> - 以控制台身份执行指令"));
    }

    @Override
    public void onEnable() {
        main = this;
        int pluginId = 27811;
        Metrics metrics = new Metrics(this, pluginId);
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger("");
        java.util.logging.Level oldLevel = logger.getLevel();
        logger.setLevel(java.util.logging.Level.OFF);
        this.saveResource("lang.yml", false);
        this.saveResource("modules.yml", false);
        logger.setLevel(oldLevel);
        logger.info(ChatColor.translateAlternateColorCodes('&', "&b&lHNPlugin &f&f> &a插件已启动 &7| &e作者: NiceHello"));
        Bukkit.getPluginCommand("hnplugin").setExecutor(new HNCommand());
        Bukkit.getPluginCommand("suicide").setExecutor(new Suicide());
        Bukkit.getPluginCommand("sudo").setExecutor(new Sudo());
        Bukkit.getPluginCommand("console").setExecutor(new Console());
        Bukkit.getPluginManager().registerEvents(new HNListener(), this);
    }
    @Override
    public void onDisable() {
        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&b&lHNPlugin &f&7> &c插件已关闭，欢迎下次使用!"));
    }
}

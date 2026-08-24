package me.hnplugin.hnplugin;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import me.hnplugin.hnplugin.command.Console;
import me.hnplugin.hnplugin.command.HNCommand;
import me.hnplugin.hnplugin.command.Sudo;
import me.hnplugin.hnplugin.command.Suicide;
import me.hnplugin.hnplugin.command.Tpa;
import me.hnplugin.hnplugin.command.TpaAccept;
import me.hnplugin.hnplugin.command.TpaDeny;
import me.hnplugin.hnplugin.command.TpaHere;
import me.hnplugin.hnplugin.listener.HNListener;
import me.hnplugin.hnplugin.manager.config;

public final class HNPlugin extends JavaPlugin {

    public static HNPlugin main;

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
        config.loadLang();
        util.sendMessage(Bukkit.getConsoleSender(), "&b&lHNPlugin &f&f> &a插件已启动 &7| &e作者: NiceHello");
        Bukkit.getPluginCommand("hnplugin").setExecutor(new HNCommand());
        Bukkit.getPluginCommand("suicide").setExecutor(new Suicide());
        Bukkit.getPluginCommand("sudo").setExecutor(new Sudo());
        Bukkit.getPluginCommand("console").setExecutor(new Console());
        Bukkit.getPluginCommand("tpa").setExecutor(new Tpa());
        Bukkit.getPluginCommand("tpahere").setExecutor(new TpaHere());
        Bukkit.getPluginCommand("tpaccept").setExecutor(new TpaAccept());
        Bukkit.getPluginCommand("tpdeny").setExecutor(new TpaDeny());
        Bukkit.getPluginManager().registerEvents(new HNListener(), this);
    }
    @Override
    public void onDisable() {
        util.sendMessage(Bukkit.getConsoleSender(), "&b&lHNPlugin &f&7> &c插件已关闭，欢迎下次使用!");
    }
}
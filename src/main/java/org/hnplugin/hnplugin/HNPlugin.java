package org.hnplugin.hnplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class HNPlugin extends JavaPlugin {

    static HNPlugin main;
    @Override
    public void onEnable() {
        main = this;
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger("");
        java.util.logging.Level oldLevel = logger.getLevel();
        logger.setLevel(java.util.logging.Level.OFF);
        this.saveResource("lang.yml", false);
        this.saveResource("modules.yml", false);
        logger.setLevel(oldLevel);
        System.out.println("§b§lHNPlugin §f§f> §a插件已启动 §7| §e作者: NiceHello");
        Bukkit.getPluginCommand("hnplugin").setExecutor(new HNCommand());
        Bukkit.getPluginManager().registerEvents(new HNListener(), this);
    }
    @Override
    public void onDisable() {
        System.out.println("§b§lHNPlugin §f§7> §c插件已关闭，欢迎下次使用!");
    }
}

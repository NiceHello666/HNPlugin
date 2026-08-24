package me.hnplugin.hnplugin.command;

import me.hnplugin.hnplugin.manager.TPAManager;
import me.hnplugin.hnplugin.manager.config;
import me.hnplugin.hnplugin.util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TpaHere implements CommandExecutor {
    FileConfiguration modules = config.loadModules();
    FileConfiguration lang = config.loadLang();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            List<String> cantUseMsg = new ArrayList<>(lang.getStringList("TpaCantUseInConsole"));
            cantUseMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0)));
            util.sendMessage(commandSender, cantUseMsg);
            return true;
        }
        if (!modules.getBoolean("Tpa.Enable")) {
            return true;
        }
        Player requester = (Player) commandSender;
        if (!requester.hasPermission("hnplugin.command.tpahere")) {
            List<String> noPermMsg = new ArrayList<>(lang.getStringList("NoPermission"));
            noPermMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0)));
            util.sendMessage(commandSender, noPermMsg);
            return true;
        }
        if (strings.length != 1) {
            List<String> usageMsg = new ArrayList<>(lang.getStringList("TpaUsage"));
            usageMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0)).replace("%cmd%", "tpahere"));
            util.sendMessage(commandSender, usageMsg);
            return true;
        }
        Player target = Bukkit.getPlayer(strings[0]);
        if (target == null) {
            List<String> wrongPlayerMsg = new ArrayList<>(lang.getStringList("WrongPlayer"));
            wrongPlayerMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0)));
            util.sendMessage(commandSender, wrongPlayerMsg);
            return true;
        }
        if (target.getUniqueId().equals(requester.getUniqueId())) {
            List<String> selfMsg = new ArrayList<>(lang.getStringList("TpaCantSelf"));
            selfMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0)));
            util.sendMessage(commandSender, selfMsg);
            return true;
        }
        long expireTime = modules.getLong("Tpa.ExpireTime", 60);
        TPAManager.sendRequest(requester, target, TPAManager.TPARequestType.TPAHERE, expireTime);

        List<String> sentMsg = new ArrayList<>(lang.getStringList("TpaHereRequestSent"));
        sentMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0)).replace("%target%", target.getName()));
        util.sendMessage(requester, sentMsg);

        util.sendClickableTPARequest(target, lang.getStringList("Prefix").get(0), requester.getName(), lang.getString("TpaHereRequestReceived"), lang.getString("TpaAcceptButton"), lang.getString("TpaDenyButton"), "/tpaccept " + requester.getName(), "/tpdeny " + requester.getName());
        return true;
    }
}

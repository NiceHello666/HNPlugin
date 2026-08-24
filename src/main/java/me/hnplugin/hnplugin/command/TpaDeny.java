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

public class TpaDeny implements CommandExecutor {
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
        Player target = (Player) commandSender;
        if (!target.hasPermission("hnplugin.command.tpdeny")) {
            List<String> noPermMsg = new ArrayList<>(lang.getStringList("NoPermission"));
            noPermMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0)));
            util.sendMessage(commandSender, noPermMsg);
            return true;
        }
        TPAManager.TPARequest req = strings.length == 0 ? TPAManager.getRequest(target) : TPAManager.getRequest(target, strings[0]);
        if (req == null) {
            List<String> noReqMsg = new ArrayList<>(lang.getStringList("TpaNoRequest"));
            noReqMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0)));
            util.sendMessage(commandSender, noReqMsg);
            return true;
        }
        Player requester = Bukkit.getPlayer(req.requester);
        if (requester == null) {
            List<String> offlineMsg = new ArrayList<>(lang.getStringList("TpaRequesterOffline"));
            offlineMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0)).replace("%requester%", req.requesterName));
            util.sendMessage(commandSender, offlineMsg);
            TPAManager.removeRequest(target, req.requester);
            return true;
        }
        TPAManager.removeRequest(target, req.requester);

        List<String> deniedTargetMsg = new ArrayList<>(lang.getStringList("TpaDeniedTarget"));
        deniedTargetMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0)).replace("%requester%", requester.getName()));
        util.sendMessage(target, deniedTargetMsg);

        List<String> deniedRequesterMsg = new ArrayList<>(lang.getStringList("TpaDeniedRequester"));
        deniedRequesterMsg.replaceAll(line -> line.replace("%prefix%", lang.getStringList("Prefix").get(0)).replace("%target%", target.getName()));
        util.sendMessage(requester, deniedRequesterMsg);
        return true;
    }
}

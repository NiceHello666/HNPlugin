package me.hnplugin.hnplugin;

import java.util.List;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class util {
    public static String colorize(String message) {
        if (message == null) return null;
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String parsePlaceholders(Player player, String message) {
        if (message == null) return null;
        if (player != null && Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            return PlaceholderAPI.setPlaceholders(player, message);
        }
        return message;
    }

    public static String format(Player player, String message) {
        return colorize(parsePlaceholders(player, message));
    }

    public static void sendMessage(CommandSender commandSender, String message) {
        Player player = commandSender instanceof Player ? (Player) commandSender : null;
        commandSender.sendMessage(format(player, message));
    }

    public static void sendMessage(Player player, String message) {
        player.sendMessage(format(player, message));
    }

    public static void sendMessage(CommandSender commandSender, List<String> message) {
        Player player = commandSender instanceof Player ? (Player) commandSender : null;
        for (String msg : message) {
            commandSender.sendMessage(format(player, msg));
        }
    }

    public static void sendMessage(Player player, List<String> message) {
        for (String msg : message) {
            player.sendMessage(format(player, msg));
        }
    }

    public static void broadcastMessage(String message) {
        Bukkit.broadcastMessage(format(null, message));
    }

    public static void broadcastMessage(String message, Player player) {
        Bukkit.broadcastMessage(format(player, message));
    }

    public static void broadcastMessage(List<String> message) {
        for (String msg : message) {
            Bukkit.broadcastMessage(format(null, msg));
        }
    }

    public static void broadcastMessage(List<String> message, Player player) {
        for (String msg : message) {
            Bukkit.broadcastMessage(format(player, msg));
        }
    }

    public static void sendClickableTPARequest(Player target, String prefix, String requesterName, String message, String acceptButton, String denyButton, String acceptCmd, String denyCmd) {
        String rawMessage = message.replace("%prefix%", prefix).replace("%requester%", requesterName);
        TextComponent text = new TextComponent("");
        String acceptPlaceholder = "%accept%";
        String denyPlaceholder = "%deny%";
        int index = 0;
        while (index < rawMessage.length()) {
            int acceptIndex = rawMessage.indexOf(acceptPlaceholder, index);
            int denyIndex = rawMessage.indexOf(denyPlaceholder, index);
            boolean isAccept;
            int nextIndex;
            if (acceptIndex != -1 && (denyIndex == -1 || acceptIndex < denyIndex)) {
                nextIndex = acceptIndex;
                isAccept = true;
            } else if (denyIndex != -1) {
                nextIndex = denyIndex;
                isAccept = false;
            } else {
                String remaining = rawMessage.substring(index);
                if (!remaining.isEmpty()) {
                    for (BaseComponent comp : TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', remaining))) {
                        text.addExtra(comp);
                    }
                }
                break;
            }
            String before = rawMessage.substring(index, nextIndex);
            if (!before.isEmpty()) {
                for (BaseComponent comp : TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', before))) {
                    text.addExtra(comp);
                }
            }
            String buttonText = isAccept ? acceptButton : denyButton;
            TextComponent button = new TextComponent("");
            for (BaseComponent comp : TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', buttonText))) {
                button.addExtra(comp);
            }
            button.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, isAccept ? acceptCmd : denyCmd));
            text.addExtra(button);
            index = nextIndex + (isAccept ? acceptPlaceholder.length() : denyPlaceholder.length());
        }
        target.spigot().sendMessage(text);
    }
}

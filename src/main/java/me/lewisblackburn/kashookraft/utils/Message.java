package me.lewisblackburn.kashookraft.utils;

import net.kyori.adventure.text.Component;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message {
    private static final String PLUGIN_PREFIX = "§8[§9KashooKraft§8] §8→ §r";
    private static final Component INGAME_PREFIX = Component.text(PLUGIN_PREFIX);

    public static void sendMessage(CommandSender sender, String message) {
        Component textComponent = INGAME_PREFIX.append(Component.text(message));
        sender.sendMessage(textComponent);
    }

    public static void broadcastMessage(String message) {
        Component textComponent = INGAME_PREFIX.append(Component.text(message));
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(textComponent));
    }

    public static void sendMessageToPlayer(Player player, String message) {
        Component textComponent = INGAME_PREFIX.append(Component.text(message));
        player.sendMessage(textComponent);
    }

    public static void sendConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(PLUGIN_PREFIX + message);
        // Bukkit.getLogger().info(message);
    }
}

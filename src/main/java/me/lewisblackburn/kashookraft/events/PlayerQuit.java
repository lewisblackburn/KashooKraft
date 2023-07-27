package me.lewisblackburn.kashookraft.events;

import me.lewisblackburn.kashookraft.utils.Message;
import me.lewisblackburn.kashookraft.utils.PlayerData;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Get the player location message
        Location location = event.getPlayer().getLocation();

        // Send the player location to the server console
        Message.sendConsoleMessage(String.format("§7@ §f%s §7x:§f%s §7y:§f%s §7z:§f%s", event.getPlayer().getName(), Math.round(location.x()), Math.round(location.y()), Math.round(location.z())));

        // Save player data
        PlayerData.savePlayerData(event.getPlayer());
    }
}

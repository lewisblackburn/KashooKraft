package me.lewisblackburn.kashookraft.events;

import me.lewisblackburn.kashookraft.utils.Message;
import me.lewisblackburn.kashookraft.utils.PlayerData;
import me.lewisblackburn.kashookraft.utils.TabList;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(null); // Cancel the default join message

        // Prevent quote from being null
        if (PlayerData.getPlayerQuote(player.getUniqueId()) == null) PlayerData.setPlayerQuote(player.getUniqueId(), "");

        String customMessage = String.format("§f%s §7joined. §8%s", player.getName(),
                PlayerData.getPlayerQuote(player.getUniqueId()));
        Message.broadcastMessage(customMessage);

        TabList.updateLocation(event.getPlayer());

        // Get the player location message
        Location location = event.getPlayer().getLocation();

        // Send the player location to the server console
        Message.sendConsoleMessage(String.format("§7@ §f%s §7x:§f%s §7y:§f%s §7z:§f%s", event.getPlayer().getName(),
                Math.round(location.x()), Math.round(location.y()), Math.round(location.z())));
    }

}

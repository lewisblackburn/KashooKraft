package me.lewisblackburn.kashookraft.commands;

import me.lewisblackburn.kashookraft.utils.CommandWrapper;
import me.lewisblackburn.kashookraft.utils.Message;

import me.lewisblackburn.kashookraft.utils.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FindPlayer extends CommandWrapper {
    public FindPlayer() {
        super("kashookraft.findplayer", true);
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length > 0) {
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

            if (target.hasPlayedBefore()) {
                Location location;
                if (target.isOnline()) {
                    Player onlinePlayer = target.getPlayer();
                    location = onlinePlayer != null ? onlinePlayer.getLocation() : new Location(Bukkit.getWorld("world"), 0, 0, 0);
                    ;
                } else {
                    location = PlayerData.getPlayerLocation(target.getUniqueId());
                }

                Message.sendMessage(sender, String.format("§7@ §f%s §7x:§f%s §7y:§f%s §7z:§f%s", target.getName(), Math.round(location.x()), Math.round(location.y()), Math.round(location.z())));
            } else {
                Message.sendMessage(sender, "§cPlayer not found or never played before.");
            }
        } else {
            Message.sendMessage(sender, "§cPlease specify a player");
        }
    }
}
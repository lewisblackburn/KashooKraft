package me.lewisblackburn.kashookraft.commands;

import me.lewisblackburn.kashookraft.utils.CommandWrapper;
import me.lewisblackburn.kashookraft.utils.Message;
import me.lewisblackburn.kashookraft.utils.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FindHealth extends CommandWrapper {
    public FindHealth() {
        super("kashookraft.findhealth", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length > 0) {
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

            if (target.hasPlayedBefore()) {
                double health;
                if (target.isOnline()) {
                    Player onlinePlayer = target.getPlayer();
                    health = onlinePlayer != null ? onlinePlayer.getHealth() : 0.0;
                } else {
                    health = PlayerData.getPlayerHealth(target.getUniqueId());
                }

                Message.sendMessage(sender, String.format("§f%s §7has health: §f%s ❤", target.getName(), Math.round(health / 2)));
            } else {
                Message.sendMessage(sender, "§cPlayer not found or never played before.");
            }
        } else {
            Message.sendMessage(sender, "§cPlease specify a player");
        }
    }
}


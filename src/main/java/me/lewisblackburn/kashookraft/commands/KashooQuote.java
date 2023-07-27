package me.lewisblackburn.kashookraft.commands;

import me.lewisblackburn.kashookraft.utils.CommandWrapper;
import me.lewisblackburn.kashookraft.utils.Message;
import me.lewisblackburn.kashookraft.utils.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

public class KashooQuote extends CommandWrapper {
    public KashooQuote() {
        super("kashookraft.kashooquote", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length > 0) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
            String quote = String.join(" ", args).replaceFirst(args[0], "").trim();
            

            if (player.hasPlayedBefore()) {
                PlayerData.setPlayerQuote(player.getUniqueId(), quote);

                Message.sendMessage(sender, "§7Quote set successfully.");
            } else {
                Message.sendMessage(sender, "§cPlayer not found or never played before.");
            }
        } else {
            Message.sendMessage(sender, "§cPlease specify a player");
        }
    }
}


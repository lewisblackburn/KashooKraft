package me.lewisblackburn.kashookraft.commands;

import me.lewisblackburn.kashookraft.KashooKraft;
import me.lewisblackburn.kashookraft.utils.CommandWrapper;
import me.lewisblackburn.kashookraft.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ToggleProtection extends CommandWrapper {
    private final KashooKraft plugin;

    public ToggleProtection(KashooKraft plugin) {
        super("kashookraft.toggleprotection", false);
        this.plugin = plugin;
        registerSubCommand("status", new Status());
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        boolean toggle = !plugin.isProtectionEnabled();
        plugin.setProtectionEnabled(toggle);
        sendProtectionStatus((Player) sender);
    }

    private void sendProtectionStatus(Player player) {
        boolean protectionEnabled = plugin.isProtectionEnabled();
        String toggleStatus = protectionEnabled ? "DISABLED" : "ENABLED";
        Message.sendMessage(player, String.format("§7TNT & Fire : §f%s", toggleStatus));
    }

    private class Status implements CommandExecutor {
        @Override
        public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
            Player player = (Player) sender;
            ToggleProtection parentCommand = ToggleProtection.this;
            parentCommand.sendProtectionStatus(player);
            return true;
        }
    }
}

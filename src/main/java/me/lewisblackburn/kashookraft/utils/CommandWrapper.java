package me.lewisblackburn.kashookraft.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class CommandWrapper implements CommandExecutor, TabCompleter {
    private final String permission;
    private final boolean requiresPlayer;
    private final Map<String, CommandExecutor> subCommands;

    public CommandWrapper(String permission, boolean requiresPlayer) {
        this.permission = permission;
        this.requiresPlayer = requiresPlayer;
        this.subCommands = new HashMap<>();
    }

    public abstract void execute(CommandSender sender, String[] args);

    public void registerSubCommand(String label, CommandExecutor executor) {
        subCommands.put(label.toLowerCase(), executor);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (requiresPlayer && !(sender instanceof Player)) {
        Message.sendMessage(sender, "§cYou must be a player to use this command");
          return false;
       }

        if (permission != null && sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.isOp() && !player.hasPermission(permission)) {
                Message.sendMessage(sender, "§cYou do not have permission to use this command");
                return false;
            }
        }

        if (args.length > 0) {
            String subCommandLabel = args[0].toLowerCase();
            CommandExecutor subCommand = subCommands.get(subCommandLabel);
            if (subCommand != null) {
                String[] subArgs = new String[args.length - 1];
                System.arraycopy(args, 1, subArgs, 0, subArgs.length);
                return subCommand.onCommand(sender, command, label, subArgs);
            }
        }

        execute(sender, args);
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1 && subCommands.isEmpty()) {
            List<String> playerNames = new ArrayList<>();
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                playerNames.add(onlinePlayer.getName());
            }
            return filterStartingWith(playerNames, args[0]);
        } else if (args.length == 1) {
            List<String> subCommandLabels = new ArrayList<>(subCommands.keySet());
            return filterStartingWith(subCommandLabels, args[0]);
        } else if (args.length > 1) {
            String rootSubCommandLabel = args[0].toLowerCase();
            CommandExecutor rootSubCommand = subCommands.get(rootSubCommandLabel);
            if (rootSubCommand instanceof TabCompleter) {
                String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
                return ((TabCompleter) rootSubCommand).onTabComplete(sender, command, alias, subArgs);
            }
        }
        return Collections.emptyList();
    }

    private List<String> filterStartingWith(List<String> list, String prefix) {
        List<String> matches = new ArrayList<>();
        for (String entry : list) {
            if (entry.toLowerCase().startsWith(prefix.toLowerCase())) {
                matches.add(entry);
            }
        }
        return matches;
    }
}

package me.lewisblackburn.kashookraft.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class TabList {
    static String withTeamName(Player player) {
        String name = player.getDisplayName();
        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();

        for (Team team : sb.getTeams()) {
            if (team.hasEntry(player.getName())) {
                return team.getColor() + team.getPrefix() + name + team.getSuffix();
            }
        }

        return name;
    }

    public static void updateLocation(Player player) {
        player.setPlayerListName(withTeamName(player) + getLocation(player));
    }


    protected static String getLocation(Player player) {
        String world = "";
        String location = "";

        World.Environment environment = player.getWorld().getEnvironment();

        switch (environment) {
            case NETHER:
                world = "§c";
                break;
            case THE_END:
                world = "§d";
                break;
            default:
                world = "§a";
                break;
        }



        Location loc = player.getLocation();
        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();
        location = x + ", " + y + ", " + z;

        return String.format(" %s[§r%s%s]§r", world, location, world);
    }
}

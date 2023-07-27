package me.lewisblackburn.kashookraft;

import me.lewisblackburn.kashookraft.commands.FindHealth;
import me.lewisblackburn.kashookraft.commands.FindPlayer;
import me.lewisblackburn.kashookraft.commands.KashooQuote;
import me.lewisblackburn.kashookraft.commands.ToggleProtection;
import me.lewisblackburn.kashookraft.events.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.util.Objects;

public final class KashooKraft extends JavaPlugin {
    private boolean protectionEnabled;

    @Override
    public void onEnable() {
        // Plugin startup logic

        getLogger().info("Initialised");
        registerCommands();
        registerListeners();

        // Set the initial protection status
        protectionEnabled = false;

        // Create playerdata folder if not exists
        File playerDataFolder = new File(getDataFolder(), "playerdata");
        if (!playerDataFolder.exists()) {
            playerDataFolder.mkdirs();
        }
    }

    public void setProtectionEnabled(boolean enabled) {
        protectionEnabled = enabled;
    }

    public boolean isProtectionEnabled() {
        return protectionEnabled;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("FindHealth")).setExecutor(new FindHealth());
        Objects.requireNonNull(getCommand("FindPlayer")).setExecutor(new FindPlayer());
        Objects.requireNonNull(getCommand("ToggleProtection")).setExecutor(new ToggleProtection(this));
        Objects.requireNonNull(getCommand("KashooQuote")).setExecutor(new KashooQuote());
        getLogger().info("Registered Commands");
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new EntityExplode(this), this);
        getServer().getPluginManager().registerEvents(new FireSpread(this), this);
        getServer().getPluginManager().registerEvents(new PlayerHit(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        getLogger().info("Registered Listeners");
    }
}
package me.lewisblackburn.kashookraft.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerData {
    private static final File dataFolder = new File("plugins/KashooKraft/playerdata");

    public static void savePlayerData(Player player) {
        UUID uuid = player.getUniqueId();
        File playerDataFile = getPlayerDataFile(uuid);

        if (playerDataFile != null) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(playerDataFile);
            savePlayerFields(player, config); // Save player data fields

            try {
                config.save(playerDataFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void savePlayerFields(Player player, ConfigurationSection config) {
        config.set("health", player.getHealth());
        config.set("location", player.getLocation());
        // Add more fields as needed
        // config.set("fieldName", value);
    }

    public static double getPlayerHealth(UUID uuid) {
        File playerDataFile = getPlayerDataFile(uuid);

        if (playerDataFile != null) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(playerDataFile);
            return config.getDouble("health");
        }

        return 0.0; // Default health value if data couldn't be loaded
    }

    public static Location getPlayerLocation(UUID uuid) {
        File playerDataFile = getPlayerDataFile(uuid);

        if (playerDataFile != null) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(playerDataFile);
            return config.getLocation("location");
        }

        // return default location
        return new Location(Bukkit.getWorld("world"), 0, 0, 0);
    }

    public static String getPlayerQuote(UUID uuid) {
        File playerDataFile = getPlayerDataFile(uuid);

        if (playerDataFile != null) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(playerDataFile);
            return config.getString("quote");
        }

        return "";
    }

    public static void setPlayerQuote(UUID uuid, String quote) {
        File playerDataFile = getPlayerDataFile(uuid);

        if (playerDataFile != null) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(playerDataFile);
            config.set("quote", quote.trim());

            try {
                config.save(playerDataFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static File getPlayerDataFile(UUID uuid) {
        File playerDataFile = new File(dataFolder, uuid.toString() + ".yml");

        if (!playerDataFile.exists()) {
            try {
                playerDataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        return playerDataFile;
    }
}

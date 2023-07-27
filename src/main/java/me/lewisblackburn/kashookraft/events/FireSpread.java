package me.lewisblackburn.kashookraft.events;

import me.lewisblackburn.kashookraft.KashooKraft;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.EntityCombustEvent;

public class FireSpread implements Listener {
    private final KashooKraft plugin;

    public FireSpread(KashooKraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityCombust(EntityCombustEvent event) {
        if (plugin.isProtectionEnabled()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockSpread(BlockSpreadEvent event) {
        if (plugin.isProtectionEnabled() && event.getSource().getType().name().equals("FIRE")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        if (plugin.isProtectionEnabled()) {
            Block block = event.getBlock();
            block.setType(block.getType()); // Reset the block type to prevent it from burning
            event.setCancelled(true);
        }
    }
}

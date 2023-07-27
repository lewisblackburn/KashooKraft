package me.lewisblackburn.kashookraft.events;

import me.lewisblackburn.kashookraft.utils.Message;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerHit implements Listener {
    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player player = (Player) event.getEntity();
            Player attacker = (Player) event.getDamager();
            double health = player.getHealth();
            double damage = event.getDamage();
            double damageTaken = player.getMaxHealth() - (health - damage);
            Message.sendMessage(attacker, String.format("§f%s §7has health: §f%s ❤ (§f%s ❤ §7from §f%s)", player.getName(), Math.round(health / 2), Math.round(damageTaken / 2), attacker.getName()));        }
    }
}
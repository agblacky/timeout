package me.agblacky.timeout;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DamageListener implements Listener {
    /*@EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        //EntityEvent entity=event.getEntity();
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            //System.out.println(event.getCause());

        }
    }*/

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        System.out.println(event.getDamager());
        System.out.println(event.getEntity().getName());
        //TODO: Set Timer which resets every time damage is taken
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = event.getEntity().getPlayer();
        //Ban Player?
        p.setGameMode(GameMode.SPECTATOR);
    }
}

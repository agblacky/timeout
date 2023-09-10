package me.agblacky.timeout;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
//import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import static me.agblacky.timeout.RunSchedular.future;
import static me.agblacky.timeout.RunSchedular.runSchedular;
import static me.agblacky.timeout.Timeout.playerdata;

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
        //TODO: Crashes on non player instance
        if (event.getDamager() instanceof Player) {
            future.cancel(true);
            System.out.println(event.getDamager());
            System.out.println(event.getEntity().getName());
            //TODO: Set Timer which resets every time damage is taken
            Player p = (Player) event.getEntity();
            runSchedular(playerdata, p, 10);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = event.getEntity().getPlayer();
        //Ban Player?
        p.setGameMode(GameMode.SPECTATOR);
    }
}

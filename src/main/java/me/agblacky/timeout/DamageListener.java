package me.agblacky.timeout;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import static me.agblacky.timeout.Timeout.playerData;


public class DamageListener implements Listener {

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player p) {
            //Get Timer and restart it with another time
            SchedulerTimer fightTimer = playerData.get(p.getName());
            fightTimer.future.cancel(true);
            //TODO only when the time has run out
            fightTimer.scheduleKick(5);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = event.getEntity().getPlayer();
        //event.setDeathMessage("Player" + p.getName() + "died");
        SchedulerTimer fightTimer = playerData.get(p.getName());
        fightTimer.future.cancel(true);
        p.setGameMode(GameMode.SPECTATOR);
    }
}

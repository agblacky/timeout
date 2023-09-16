package me.agblacky.timeout;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static me.agblacky.timeout.Timeout.playerData;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //event.setJoinMessage("Player " + event.getPlayer().getName() + " has joined!");
        //Get Gamemode
        Player p = event.getPlayer();
        if (p.getGameMode() == GameMode.SPECTATOR) {
            return;
        }
        //Only start new timer if there is no existing one
        if (playerData.get(p.getName()) == null) {
            SchedulerTimer joinTimer = new SchedulerTimer(p, 30);
            //Get Player and current time and start timer
            playerData.put(p.getName(),joinTimer);
            joinTimer.runScheduler();
        }
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event) {
        //Player p = event.getPlayer();
        event.setQuitMessage("");
    }
}


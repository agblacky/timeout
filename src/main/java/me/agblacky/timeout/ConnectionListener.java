package me.agblacky.timeout;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.time.LocalDateTime;

import static me.agblacky.timeout.RunSchedular.runSchedular;
import static me.agblacky.timeout.Timeout.playerdata;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        //event.setJoinMessage("Player " + event.getPlayer().getName() + " has joined!");
        //Get Player and current time and start timer
        Player p = event.getPlayer();
        if (playerdata.get(p.getName()) == null) {
            LocalDateTime now = LocalDateTime.now();
            runSchedular(playerdata, now, p);
        }
    }
    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event){
        Player p = event.getPlayer();
        //event.setQuitMessage(p.getName()+"'s time run out.");
        event.setQuitMessage("");
    }
}


package me.agblacky.timeout;

import org.bukkit.GameMode;
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
        //Get Gamemode
        Player p = event.getPlayer();
        if (p.getGameMode() == GameMode.SPECTATOR) {
            return;
        }
        if (playerdata.get(p.getName()) == null) {
            //Get Player and current time and start timer
            LocalDateTime now = LocalDateTime.now();
            playerdata.put(p.getName(), now);
            //TODO Write to disk in case of failure
            runSchedular(playerdata, p, 10);
        }
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        //event.setQuitMessage(p.getName()+"'s time run out.");
        event.setQuitMessage("");
    }
}


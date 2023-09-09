package me.agblacky.timeout;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.LocalDateTime;

import static me.agblacky.timeout.RunSchedular.runSchedular;
import static me.agblacky.timeout.Timeout.playerdata;


public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage("Player " + event.getPlayer().getName() + " has joined!");
        LocalDateTime now = LocalDateTime.now();
        Player p = event.getPlayer();
        runSchedular(now, p);
        System.out.println(playerdata);
    }
}


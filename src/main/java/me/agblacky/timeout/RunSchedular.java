package me.agblacky.timeout;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.*;
import static me.agblacky.timeout.Timeout.playerdata;

public class RunSchedular {
    private static ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);

    public static void runSchedular(LocalDateTime joinTime, Player p) {
        playerdata.put(p.getName(), joinTime);
        //Start Timer
        timer.schedule(() -> kickPlayer(p), 10, SECONDS); //Make it adjustable via command

    }

    private static Runnable kickPlayer(Player p) {
        Bukkit.broadcastMessage("Time's over");
        //p.ban("Zeit ausgelaufen", Duration.ofDays(1), "String 2");
        return null;
    }
}

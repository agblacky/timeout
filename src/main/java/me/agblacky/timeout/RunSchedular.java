package me.agblacky.timeout;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Dictionary;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.*;

public class RunSchedular {
    private static ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);

    public static void runSchedular(Dictionary<String, LocalDateTime> playerDataPointer, LocalDateTime joinTime, Player p) {
        //Pointer
        playerDataPointer.put(p.getName(), joinTime);
        //Start Timer
        //TODO Write to disk in case of failure
        timer.schedule(() -> informPlayers(playerDataPointer, p), 10, SECONDS);
    }

    private static Runnable informPlayers(Dictionary<String, LocalDateTime> playerDataPointer, Player p) {
        Bukkit.broadcastMessage("Player " + p.getName() + "disconnects in 5 minutes");
        timer.schedule(() -> kickPlayer(playerDataPointer, p), 5, SECONDS);
        return null;
    }

    private static Runnable kickPlayer(Dictionary<String, LocalDateTime> playerDataPointer, Player p) {
        //Bukkit.broadcastMessage("Time's over");
        //p.sendMessage("Das solltest nur du sehen können");
        //Ban Player and remove them from dictionary so that the timer resets the next time the player joins
        //p.ban("Zeit ausgelaufen", Duration.ofDays(1), "String 2");
        playerDataPointer.remove(p.getName());
        //Broadcast news
        Bukkit.broadcastMessage(p.getName() + "'s time ran out");
        return null;
    }

}

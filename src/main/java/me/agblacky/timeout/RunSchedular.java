package me.agblacky.timeout;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.*;

public class RunSchedular {
    private static ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
    public static ScheduledFuture<?> future;

    public static void runSchedular(Dictionary<String, LocalDateTime> playerDataPointer, Player p, int time) {
        //Start Timer
        future = timer.schedule(() -> informPlayers(playerDataPointer, p), time, SECONDS);
    }

    private static Runnable informPlayers(Dictionary<String, LocalDateTime> playerDataPointer, Player p) {
        Bukkit.broadcastMessage("Player " + p.getName() + " disconnects in 5 minutes");
        timer.schedule(() -> kickPlayer(playerDataPointer, p), 5, SECONDS);
        return null;
    }

    private static Runnable kickPlayer(Dictionary<String, LocalDateTime> playerDataPointer, Player p) {
        //Define Weekend
        ArrayList<String> weekendDays = new ArrayList<String>(2);
        weekendDays.addAll(Arrays.asList("SATURDAY","SUNDAY"));
        System.out.println(weekendDays);
        //Calculate next unban date
        LocalDate now = LocalDate.now();
        LocalDate unbanDate = now.plusDays(1);
        //TODO: If unbanDate intersects with weekend

        LocalDateTime unbanDateTime = unbanDate.atTime(14, 0);
        Bukkit.broadcastMessage("You will be unbanned at " + unbanDateTime);
        //Ban Player and remove them from dictionary so that the timer resets the next time the player joins
        //p.ban("Zeit ausgelaufen", Duration.ofDays(1), "System");
        playerDataPointer.remove(p.getName());
        return null;
    }

}

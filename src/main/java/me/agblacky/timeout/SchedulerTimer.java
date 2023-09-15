package me.agblacky.timeout;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.*;
import static me.agblacky.timeout.Timeout.playerData;
import static me.agblacky.timeout.Timeout.timer;

public class SchedulerTimer {
    public ScheduledFuture<?> future;
    public Player p;
    //Timer time
    public int time;

    public SchedulerTimer(Player p, int time) {
        this.p = p;
        this.time = time;
    }

    public void runSchedular() {
        //Start Timer
        try{
            //TODO Will always go to inform function, even on damage
            timer.schedule(this::informPlayers, time, SECONDS);
        }
        catch(Exception e){
            System.out.println(e);
        }
        finally{
            //Shut down when not used anymore
            timer.shutdown();
        }

    }

    private Runnable informPlayers() {
        Bukkit.broadcastMessage("Player " + p.getName() + "'s time limit reached in 5 minutes");
        //TODO Bug:Function will not be called anymore?
        this.future = timer.schedule(this::kickPlayer, 5, SECONDS);
        return null;
    }
    private Runnable kickPlayer() {
        //Define Weekend
        String[] weekendDays = {"SATURDAY", "SUNDAY"};
        //Calculate next unban date
        LocalDate now = LocalDate.now();
        LocalDateTime unbanDateTime = now.plusDays(1).atStartOfDay();
        //Set unban date according to intersecting weekend
        if (Arrays.stream(weekendDays).anyMatch(unbanDateTime.getDayOfWeek().toString()::contains)) {
            unbanDateTime = unbanDateTime.withHour(12);
        } else {
            unbanDateTime = unbanDateTime.withHour(14);
        }
        Bukkit.broadcastMessage("You will be unbanned at " + unbanDateTime);
        //Ban Player and remove them from dictionary so that the timer resets the next time the player joins
        //p.ban("Zeit ausgelaufen", Duration.ofDays(1), "System");
        playerData.remove(this.p.getName());
        return null;
    }

}

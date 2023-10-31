package me.agblacky.timeout;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.*;
import static me.agblacky.timeout.MetaData.getMetadata;
import static me.agblacky.timeout.Timeout.playerData;
import static me.agblacky.timeout.Timeout.timer;

public class SchedulerTimer {
    public ScheduledFuture<?> future;
    public Player p;
    //Timer time
    public int time;
    public LocalTime joinedAt;
    //Define Weekend
    //TODO COMPLETE CODE REFACTOR AND CLEANUP
    protected String[] weekendDays = {"SATURDAY", "SUNDAY"};

    public SchedulerTimer(Player p, int time) {
        this.p = p;
        this.time = time;
        this.joinedAt = LocalTime.now();
    }

    public void runScheduler() {
        //Start Timer
        try {
            this.future = timer.schedule(this::informPlayers, time, SECONDS);
        } catch (Exception e) {

        }

    }

    private Runnable informPlayers() {
        Bukkit.broadcastMessage(p.getName() + "'s time limit reached in 30 Seconds");
        scheduleKick(30);
        return null;
    }

    public void scheduleKick(int time) {
        this.future = timer.schedule(this::kickPlayer, time, SECONDS);
    }

    private Runnable kickPlayer() {
        //TODO Compare if directly calling this function from the Damage Event instead of checking for the last damage is cleaner and better performance wise
        LocalTime lasthit = LocalTime.parse(getMetadata(p, "lasthit"));
        //Calculate plus 30 minutes, minus 5 minutes after initial join time to check if the player has received damage in the last 5 minutes
        //TODO Replace all magic numbers
        if (joinedAt.plusSeconds(30).isBefore(lasthit)) {
            //offset joinedAt time so that it will take the current time into account
            joinedAt=joinedAt.plusSeconds(5);
            scheduleKick(5);
            return null;
        }
        //Calculate next unban date
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime unbanDateTime = now.plusDays(1);
        //Set unban date according to intersecting weekend
        if (Arrays.stream(weekendDays).anyMatch(unbanDateTime.getDayOfWeek().toString()::contains)) {
            unbanDateTime = unbanDateTime.withHour(12);
        } else {
            unbanDateTime = unbanDateTime.withHour(14);
        }
        Bukkit.broadcastMessage(p.getName()+" will be unbanned at " + unbanDateTime);
        //Ban Player and remove them from dictionary so that the timer resets the next time the player joins
        //p.ban("Zeit ausgelaufen", Duration.ofDays(1), "System");
        playerData.remove(this.p.getName());
        return null;
    }

}

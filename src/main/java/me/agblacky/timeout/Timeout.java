package me.agblacky.timeout;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Timeout extends JavaPlugin {
    public static Plugin plugin;
    public static final ConcurrentHashMap<String, SchedulerTimer> playerData = new ConcurrentHashMap<>();
    public static final ScheduledExecutorService timer = Executors.newScheduledThreadPool(2);
    public static Boolean timerActive;

    @Override
    public void onEnable() {
        //TODO Write to disk in case of server failure
        //TODO Error handling
        //TODO Logging where useful
        //TODO follow Java naming conventions
        //Register all Events
        plugin = this;
        getServer().getPluginManager().registerEvents(new ConnectionListener(), this);
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        getCommand("setTimer").setExecutor(new CommandSetTimer());
        getCommand("setTimerLength").setExecutor(new CommandSetTimerLength());
        getCommand("setFightLength").setExecutor(new CommandSetFightLength());
        getLogger().info("Timeout Plugin loaded");
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable");
    }

}


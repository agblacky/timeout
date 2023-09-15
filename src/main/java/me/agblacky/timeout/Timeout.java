package me.agblacky.timeout;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Timeout extends JavaPlugin {
    //TODO lock access to playerData when it's currently being changed (ConcurrentHashMap?)
    public static final HashMap<String, SchedulerTimer> playerData = new HashMap<>();
    public static final ScheduledExecutorService timer = Executors.newScheduledThreadPool(2);
    @Override
    public void onEnable() {
        //TODO Write to disk in case of failure when changed
        //TODO Errorhandling
        //TODO Logging where useful
        //TODO follow Java naming conventions
        //TODO add commands to set timers
        //Register all Events
        getServer().getPluginManager().registerEvents(new ConnectionListener(), this);
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        getLogger().info("Timeout Plugin loaded");
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable");
    }

}


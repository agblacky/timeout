package me.agblacky.timeout;

import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;
import java.util.Dictionary;
import java.util.Hashtable;

public class Timeout extends JavaPlugin {
    public static Dictionary<String, LocalDateTime> playerdata;
    public static String[] excludedPlayers; //Only if the player won't get banned

    @Override
    public void onEnable() {
        //TODO Write to disk in case of failure
        playerdata = new Hashtable<>();
        excludedPlayers = new String[]{};
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


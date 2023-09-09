package me.agblacky.timeout;

import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;
import java.util.Dictionary;
import java.util.Hashtable;

public class Timeout extends JavaPlugin {
    public static Dictionary<String, LocalDateTime> playerdata;
    @Override
    public void onEnable() {
        playerdata = new Hashtable<>();
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getLogger().info("Timeout Plugin loaded");
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable");
    }

}


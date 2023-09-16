package me.agblacky.timeout;

import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;

import static me.agblacky.timeout.Timeout.plugin;

public class MetaData {

    public static void setMetadata(Entity player, String key, Object value) {
        player.setMetadata(key, new FixedMetadataValue(plugin, value));
    }

    public static String getMetadata(Entity player, String key) {
        return player.getMetadata(key).get(0).asString();
    }
}

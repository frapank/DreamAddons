package com.fiscalo.dreamaddons.airdrops.airdropsbase;

import com.fiscalo.dreamaddons.DreamAddons;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private static FileConfiguration config;

    /* Setup config spawners */
    public ConfigManager(){

        config = DreamAddons.getInstance().getConfig();
        ConfigurationSection locationsSection = config.getConfigurationSection("locations");

        for(String key : locationsSection.getKeys(false)){

            ConfigurationSection newSection = config.getConfigurationSection("locations." + key);
            int x = newSection.getInt("x");
            int y = newSection.getInt("y");
            int z = newSection.getInt("z");

            Location location = new Location(Bukkit.getWorld(config.getString("world")), x, y, z);
            DreamAddons.getInstance().getLogger().warning("Aggiunta una nuova location: " + key);
            DreamAddons.getSpawns().put(key, location);
        }

    }




}

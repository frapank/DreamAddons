package com.dream.dreamaddons.airdrops.airdropsbase;

import com.dream.dreamaddons.DreamAddons;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class AirDropSpawnerLoader {

    /* Setup config spawners */
    public AirDropSpawnerLoader(DreamAddons dreamAddons){

        ConfigurationSection locationsSection =  dreamAddons.getConfig().getConfigurationSection("locations");

        for(String key : locationsSection.getKeys(false)){

            ConfigurationSection newSection =  dreamAddons.getConfig().getConfigurationSection("locations." + key);
            int x = newSection.getInt("x");
            int y = newSection.getInt("y");
            int z = newSection.getInt("z");

            Location location = new Location(Bukkit.getWorld( dreamAddons.getConfig().getString("world")), x, y, z);
            dreamAddons.getLogger().warning("Aggiunta una nuova location: " + key);
            DreamAddons.getSpawns().put(key, location);
        }

    }




}

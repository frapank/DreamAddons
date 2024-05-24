package com.dream.dreamaddons.airdrops.airdropsbase;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.Functions;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpawnTask extends BukkitRunnable {

    private static FileConfiguration config = DreamAddons.getInstance().getConfig();
    private static List<Location> locations = new ArrayList<>(DreamAddons.getSpawns().values());
    /* Spawn the airdrops */
    @Override
    public void run() {

        Functions.removeAllArmorStand();
        Collections.shuffle(locations);
        try{
            int spawnCount = Math.min(config.getInt("spawn-count"), locations.size());
            for (int i = 0; i < spawnCount; i++) {
                Location location = locations.get(i);
                AirDropManager.spawnDrop(location);
            }

            DreamAddons.getInstance().getLogger().info("AirDrop spawned");
        }catch(Exception e){
            DreamAddons.getInstance().getLogger().warning("spawn-count config string error :" + e);
        }


    }

}


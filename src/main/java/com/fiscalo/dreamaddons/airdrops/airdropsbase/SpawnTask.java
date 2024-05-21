package com.fiscalo.dreamaddons.airdrops.airdropsbase;

import com.fiscalo.dreamaddons.DreamAddons;
import com.fiscalo.dreamaddons.airdrops.commands.Functions;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

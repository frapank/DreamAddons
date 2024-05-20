package com.fiscalo.dreamaddons.airdrops.airdropsbase;

import com.fiscalo.dreamaddons.DreamAddons;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;


import java.util.Random;

public class SpawnTask extends BukkitRunnable {

    private static FileConfiguration config = DreamAddons.getInstance().getConfig();

    /* Spawn the airdrops */
    @Override
    public void run() {
        /* ⚠️Questo è il codice che sto rifacendo, per ora metto quello vecchio ⚠️
        //Rimuovi tutti gli armorstand
        Functions.removeAllArmorStand();
        List<Integer> numeri = new ArrayList<>();
        Random random = new Random();
        int numero;
        for(int i = 0; i < config.getInt("spawn-count"); i++){
            numero = random.nextInt(ProjectAirFall.getSpawns().size());
            numeri.add(numero);
        }
        for (Location location : ProjectAirFall.getSpawns().values()) {
            if (i == index) {
                AirDropManager.spawnDrop(location);
            }
            i++;
        }
        */

        //Crea un numero casuale

        Random random = new Random();
        int index = random.nextInt(DreamAddons.getSpawns().size());
        int i = 0;

        for (Location location : DreamAddons.getSpawns().values()) {
            if (i == index) {
                AirDropManager.spawnDrop(location);
            }
            i++;
        }



    }


}

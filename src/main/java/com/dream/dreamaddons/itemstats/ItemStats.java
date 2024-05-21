package com.dream.dreamaddons.itemstats;

import com.dream.dreamaddons.DreamAddons;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemStats {

    public static void setLore(ItemMeta meta){

        if(!meta.hasLore()){

            ArrayList<String> lore = new ArrayList<>();
            lore.add(DreamAddons.color(" "));
            lore.add(DreamAddons.color("&dKills : 1"));
            meta.setLore(lore);



        }

    }

    public static void updateKills(ItemMeta meta, int newKills) {
        ArrayList<String> lore = (ArrayList<String>) meta.getLore();
        for (int i = 0; i < lore.size(); i++) {
            String line = lore.get(i);
            if (line.contains("Kills : ")) {
                String newLine = line.replaceFirst("\\d+", Integer.toString(newKills));
                lore.set(i, newLine);
                meta.setLore(lore);
                break;
            }
        }
    }

    public static int getKillsCount(ArrayList<String> lore) {
        for (String line : lore) {
            if (line.contains("Kills : ")) {
                String[] parts = line.split("Kills : ");
                if (parts.length > 1) {
                    String numberPart = parts[1].split(" ")[0];
                    try {
                        return Integer.parseInt(numberPart);
                    } catch (NumberFormatException e) {

                        System.err.println("Error parsing number: " + numberPart);
                        return -1;
                    }
                }
            }
        }
        return -1; // Ritorna -1 se c'é un errore
    }
}

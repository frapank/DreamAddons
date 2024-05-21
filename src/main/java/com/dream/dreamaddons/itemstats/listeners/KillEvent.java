package com.dream.dreamaddons.itemstats.listeners;

import com.dream.dreamaddons.itemstats.ItemStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class KillEvent implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent event){
        if(event.getEntity().getKiller() instanceof Player){

            Player player = event.getEntity().getKiller();
            if(player.getItemInHand() != null){
                ItemStack item = player.getItemInHand();
                ItemMeta meta = item.getItemMeta();

                if(!meta.hasLore()){
                    ItemStats.setLore(meta);
                    item.setItemMeta(meta);
                    return;
                }

                ItemStats.updateKills(meta, ItemStats.getKillsCount((ArrayList<String>) meta.getLore()) + 1);
                item.setItemMeta(meta);
            }
        }

    }
}

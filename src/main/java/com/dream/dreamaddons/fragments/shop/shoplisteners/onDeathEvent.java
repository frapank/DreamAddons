package com.dream.dreamaddons.fragments.shop.shoplisteners;

import com.dream.dreamaddons.DreamAddons;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onDeathEvent implements Listener {

    @EventHandler
    public static void onDeath(PlayerDeathEvent event){

       if(DreamAddons.getKeepInventoryPlayers().contains(event.getEntity().getUniqueId())){
           Player player = event.getEntity();

           //Da la keep inventory

           DreamAddons.getKeepInventoryPlayers().remove(player.getUniqueId());
       }

    }
}

package com.dream.dreamaddons.fragments.shop.shoplisteners;

import com.dream.dreamaddons.DreamAddons;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class KeepInventoryListener implements Listener {

    @EventHandler
    public static void onDeath(PlayerDeathEvent event){

       if(DreamAddons.getKeepInventoryPlayers().contains(event.getEntity().getPlayer().getUniqueId())){
           Player player = event.getEntity();

           DreamAddons.getInventorysForKeep().put(player, player.getInventory());
       }

    }
}

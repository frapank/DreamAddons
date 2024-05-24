package com.dream.dreamaddons.airdrops.listeners;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.Functions;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class LeftClickEvent implements Listener {

    FileConfiguration config = DreamAddons.getInstance().getConfig();

    @EventHandler
    public void armorStandEvent(PlayerArmorStandManipulateEvent event) {
        ArmorStand armorStand = event.getRightClicked();
        if(armorStand.getCustomName() != null && armorStand.getCustomName().equals(DreamAddons.color(config.getString("name")))){
            Functions.executeCommand(event.getPlayer());
            Functions.playExplosionEffect(armorStand.getLocation());
            armorStand.remove();
            event.setCancelled(true);
        }

    }

}

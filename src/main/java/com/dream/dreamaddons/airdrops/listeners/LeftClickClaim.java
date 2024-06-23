package com.dream.dreamaddons.airdrops.listeners;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.utils.Functions;
import com.dream.dreamaddons.utils.NmsUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;

public class LeftClickClaim implements Listener {

    FileConfiguration config = DreamAddons.getInstance().getConfig();

    @EventHandler
    public void armorStandEvent(PlayerArmorStandManipulateEvent event) {
        ArmorStand armorStand = event.getRightClicked();
        if(armorStand.getCustomName() != null && armorStand.getCustomName().equals(DreamAddons.color(config.getString("name")))){
            armorStand.remove();
            NmsUtils.playExplosionEffect(armorStand.getLocation());
            Functions.executeCommand(event.getPlayer());
            event.setCancelled(true);
        }

    }

}

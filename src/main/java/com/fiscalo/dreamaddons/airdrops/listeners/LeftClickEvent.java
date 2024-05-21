package com.fiscalo.dreamaddons.airdrops.listeners;

import com.fiscalo.dreamaddons.DreamAddons;
import com.fiscalo.dreamaddons.airdrops.commands.Functions;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class LeftClickEvent implements Listener {

    FileConfiguration config = DreamAddons.getInstance().getConfig();

    @EventHandler
    public void armorStandEvent(PlayerArmorStandManipulateEvent event) {
        ArmorStand armorStand = event.getRightClicked();
        if(armorStand.getCustomName() != null && armorStand.getCustomName().equals(DreamAddons.color(config.getString("name")))){
            Functions.executeCommand(event.getPlayer());
            armorStand.remove();
            event.setCancelled(true);
        }

    }

}

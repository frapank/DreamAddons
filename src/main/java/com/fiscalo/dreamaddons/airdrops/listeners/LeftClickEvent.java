package com.fiscalo.dreamaddons.airdrops.listeners;

import com.fiscalo.dreamaddons.DreamAddons;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class LeftClickEvent implements Listener {

    FileConfiguration config = DreamAddons.getInstance().getConfig();

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getInventory().getHolder() instanceof ArmorStand) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityPickupItem(PlayerPickupItemEvent event) {
        if (event.getPlayer() instanceof Player) {
            ItemStack item = event.getItem().getItemStack();
            if (isArmorStandItem(item)) {
                event.setCancelled(true);
                event.getItem().remove();

            }
        }
    }

    private boolean isArmorStandItem(ItemStack item) {
        return item.getType() == (Material.matchMaterial(config.getString("block")));
    }




    /*
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getClickedInventory().getHolder() instanceof ArmorStand) {
            ArmorStand armorStand = (ArmorStand) event.getClickedInventory().getHolder();
            if (event.getSlot() >= 5 && event.getSlot() <= 8) {

                event.setCancelled(true);
                Player player = (Player) event.getWhoClicked();
                player.sendMessage("debug option clicked armor stand");
                String soundClaim= config.getString("claim-sound");
                try{
                    armorStand.getWorld().playSound(armorStand.getLocation(), Sound.valueOf(soundClaim), 1.0f, 1.0f);
                } catch (IllegalArgumentException e){
                    ProjectAirFall.getInstance().getLogger().warning("Config error sound <claim-sound> :" + e);
                }
                ConfigManager.executeCommand(player);
                armorStand.remove();
            }
        }
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {

        Player player = (Player) event.getPlayer();
        Entity clickedEntity = event.getRightClicked();
        player.sendMessage("Event done, clicked: " + clickedEntity.getName());
        event.setCancelled(true);

        if (clickedEntity instanceof ArmorStand) {
            event.setCancelled(true);
            //Player player = (Player) event.getPlayer();
            player.sendMessage("debug option clicked armor stand");
            ArmorStand armorStand = (ArmorStand) event.getRightClicked();
            String soundClaim= config.getString("claim-sound");
            try{
                armorStand.getWorld().playSound(armorStand.getLocation(), Sound.valueOf(soundClaim), 1.0f, 1.0f);
            } catch (IllegalArgumentException e){
                ProjectAirFall.getInstance().getLogger().warning("Config error sound <claim-sound> :" + e);
            }
            ConfigManager.executeCommand(player);
            armorStand.remove();
        }


    }

     */
}

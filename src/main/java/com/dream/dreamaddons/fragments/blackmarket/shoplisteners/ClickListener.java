package com.dream.dreamaddons.fragments.blackmarket.shoplisteners;


import com.dream.dreamaddons.fragments.blackmarket.PerkActivator;
import com.dream.dreamaddons.fragments.blackmarket.gui.PerkMarket;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ClickListener implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if (event.getInventory().getName().equalsIgnoreCase("Black Market")) {

            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.hasItemMeta()) {
                ItemMeta meta = clickedItem.getItemMeta();

                if (meta != null && meta.hasDisplayName()) {
                    String displayName = ChatColor.stripColor(meta.getDisplayName());

                    if (displayName.equalsIgnoreCase("Item Shop")) {
                        //ItemMarket.openGui((Player) event.getWhoClicked());
                        event.setCancelled(true);
                        return;
                    }

                    if (displayName.equalsIgnoreCase("Perk Shop")) {
                        PerkMarket.openGui((Player) event.getWhoClicked());
                        event.setCancelled(true);
                        return;
                    }

                }
            }

            event.setCancelled(true);
        }

        if (event.getInventory().getName().equalsIgnoreCase("Perk Market")) {

            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.hasItemMeta()) {
                ItemMeta meta = clickedItem.getItemMeta();

                if (meta != null && meta.hasDisplayName()) {
                    String displayName = ChatColor.stripColor(meta.getDisplayName());

                    if (displayName.equalsIgnoreCase("KEEP INVENTORY")) {
                        PerkActivator.activateKeepInventory((Player) event.getWhoClicked());
                        event.setCancelled(true);
                        return;
                    }

                    if (displayName.equalsIgnoreCase("BOOST MONEY")) {
                        PerkActivator.activateMoneyBoost((Player) event.getWhoClicked());
                        event.setCancelled(true);
                        return;
                    }

                    if (displayName.equalsIgnoreCase("CRITICAL BOOST")) {
                        PerkActivator.activateCriticalBoost((Player) event.getWhoClicked());
                        event.setCancelled(true);
                        return;
                    }
                }
            }

            // Attiva la non si sa ancora e rimuovi i frammenti
            event.setCancelled(true);
        }
    }
}


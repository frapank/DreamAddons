package com.dream.dreamaddons.fragments.shop.shoplisteners;


import com.dream.dreamaddons.fragments.shop.gui.PerkActivator;
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

                    if (displayName.equalsIgnoreCase("Keep Inventory")) {
                        // Attiva la keep e rimuovi i frammenti
                        event.setCancelled(true);
                        return;
                    }

                    if (displayName.equalsIgnoreCase("Boost Money")) {
                        // Attiva la boost money e rimuovi i frammenti
                        event.setCancelled(true);
                        return;
                    }

                    if (displayName.equalsIgnoreCase("Critical Boost")) {
                        PerkActivator.activateCriticalBoost((Player) event.getWhoClicked());
                        event.getWhoClicked().sendMessage("Hai cliccato la gui");
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


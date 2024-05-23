package com.dream.dreamaddons.fragments.shop.shoplisteners;

import com.dream.dreamaddons.DreamAddons;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ClickListener implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent event){

        if(event.getInventory().getName().equalsIgnoreCase("Black Market"))
        {

            ItemStack clickedItem = event.getCurrentItem();
            String keepInventory = DreamAddons.color("&6Keep Inventory");
            if(clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(DreamAddons.color("&6Keep Inventory")))
            {
                //Attiva la keep e rimuovi i frammenti
                event.setCancelled(true);

                return;
            } else if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(DreamAddons.color("&aBoost XP")))
            {
                //Attiva la boost xp e rimuovi i frammenti
                event.setCancelled(true);

                return;
            }

            //Attiva la non si sa ancorae rimuovi i frammenti
            event.setCancelled(true);
        }
    }
}

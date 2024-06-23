package com.dream.dreamaddons.fragments.blackmarket.shoplisteners;


import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.fragments.blackmarket.PerkActivator;
import com.dream.dreamaddons.fragments.blackmarket.gui.ItemMarket;
import com.dream.dreamaddons.fragments.blackmarket.gui.MainGuiBlackMarket;
import com.dream.dreamaddons.fragments.blackmarket.gui.PerkMarket;
import com.dream.dreamaddons.utils.Functions;
import com.dream.dreamaddons.utils.NmsUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ClickListener implements Listener {

    private static FileConfiguration config = DreamAddons.getInstance().getConfig();
    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if (event.getInventory().getName().equalsIgnoreCase(DreamAddons.color("            &e✦ &0&lGENERALE &e✦"))) {
            event.setCancelled(true);
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.hasItemMeta()) {
                ItemMeta meta = clickedItem.getItemMeta();

                if (meta != null && meta.hasDisplayName()) {
                    String displayName = ChatColor.stripColor(meta.getDisplayName());

                    switch (displayName){
                        case "PERK SHOP":
                            PerkMarket.openGui((Player) event.getWhoClicked());
                            return;
                        case "ITEM SHOP":
                            ItemMarket.openGui((Player) event.getWhoClicked());
                            return;
                    }
                }
            }
        }

        if (event.getInventory().getName().equalsIgnoreCase(DreamAddons.color("            &e✦ &0&lOGGETTI &e✦"))) {

            ItemStack clickedItem = event.getCurrentItem();
            event.setCancelled(true);

            if (clickedItem != null && clickedItem.hasItemMeta()) {
                ItemMeta meta = clickedItem.getItemMeta();

                if (meta != null && meta.hasDisplayName()) {

                    Player player = (Player) event.getWhoClicked();
                    String displayName = ChatColor.stripColor(meta.getDisplayName());


                    if(clickedItem.getType().equals(Material.WEB)) {
                        if (Functions.getFragments(player) >= 9) {
                            Functions.setFragments(player, Functions.getFragments(player) - 9);
                            player.getInventory().addItem(new ItemStack(Material.WEB, 5));
                            player.sendMessage(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("black-messages.yes-fragments")));
                            event.setCancelled(true);
                            return;
                        }
                        player.sendMessage(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("black-messages.no-fragments")));

                        event.setCancelled(true);
                        return;
                    }
                    if(clickedItem.getType().equals(Material.OBSIDIAN)) {
                        if (Functions.getFragments(player) >= 7) {
                            Functions.setFragments(player, Functions.getFragments(player) - 7);
                            player.getInventory().addItem(new ItemStack(Material.OBSIDIAN, 8));
                            player.sendMessage(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("black-messages.yes-fragments")));
                            event.setCancelled(true);
                            return;
                        }
                        player.sendMessage(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("black-messages.no-fragments")));
                        event.setCancelled(true);


                        return;
                    }
                    if(clickedItem.getType().equals(Material.DIAMOND_AXE)) {
                        if(Functions.getFragments(player) >= 5){
                            Functions.setFragments(player, Functions.getFragments(player) - 5);
                            player.getInventory().addItem(new ItemStack(Material.DIAMOND_AXE));
                            player.sendMessage(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("black-messages.yes-fragments")));
                            event.setCancelled(true);
                            return;
                        }
                        player.sendMessage(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("black-messages.no-fragments")));
                        event.setCancelled(true);
                        return;
                    }
                    if(displayName.equalsIgnoreCase("« Torna indietro!")) {
                        MainGuiBlackMarket.openGui((Player) event.getWhoClicked());
                        return;
                    }

                }
            }
        }

        if (event.getInventory().getName().equalsIgnoreCase(DreamAddons.color("      &e✦ &0&lPOTENZIAMENTI &e✦"))) {

            ItemStack clickedItem = event.getCurrentItem();
            event.setCancelled(true);
            if (clickedItem != null && clickedItem.hasItemMeta()) {
                ItemMeta meta = clickedItem.getItemMeta();

                if (meta != null && meta.hasDisplayName()) {
                    String displayName = ChatColor.stripColor(meta.getDisplayName());

                    switch (displayName){
                        case "CRITICAL BOOST":
                            PerkActivator.activateCriticalBoost((Player) event.getWhoClicked());
                            return;
                        case "BOOST MONEY":
                            PerkActivator.activateMoneyBoost((Player) event.getWhoClicked());
                            return;
                        case "KEEP INVENTORY":
                            PerkActivator.activateKeepInventory((Player) event.getWhoClicked());
                            return;
                        case "« Torna indietro!":
                            MainGuiBlackMarket.openGui((Player) event.getWhoClicked());
                            return;
                    }
                }
            }
        }
    }
}


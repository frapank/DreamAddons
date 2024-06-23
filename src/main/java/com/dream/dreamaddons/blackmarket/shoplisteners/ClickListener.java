package com.dream.dreamaddons.blackmarket.shoplisteners;


import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.blackmarket.PerkActivator;
import com.dream.dreamaddons.blackmarket.gui.ItemMarket;
import com.dream.dreamaddons.blackmarket.gui.KeyMarket;
import com.dream.dreamaddons.blackmarket.gui.MainGuiBlackMarket;
import com.dream.dreamaddons.blackmarket.gui.PerkMarket;
import com.dream.dreamaddons.fragments.fragmentItem.Fragment;
import com.dream.dreamaddons.goldenhead.GoldenHead;
import com.dream.dreamaddons.utils.Functions;
import com.dream.dreamaddons.utils.NmsUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

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
                        case "KEY SHOP":
                            KeyMarket.openGui((Player) event.getWhoClicked());
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

                    if(clickedItem.getType().equals(Material.SKULL_ITEM)) {
                        if(Functions.getFragments(player) >= 15){
                            Functions.setFragments(player, Functions.getFragments(player) - 15);
                            GoldenHead.giveGoldenHead(player);
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

        if (event.getInventory().getName().equalsIgnoreCase(DreamAddons.color("      &e✦ &0&lNEGOZIO KEY &e✦"))) {

            ItemStack clickedItem = event.getCurrentItem();
            event.setCancelled(true);
            if (clickedItem != null && clickedItem.hasItemMeta()) {
                ItemMeta meta = clickedItem.getItemMeta();

                if (meta != null && meta.hasDisplayName()) {
                    String displayName = ChatColor.stripColor(meta.getDisplayName());
                    Player player = (Player) event.getWhoClicked();
                    String str;
                    switch (displayName){
                        case "CHIAVE COMUNE":
                            if(Functions.getFragments(player) >= 10){
                                str = "crate giveKey comune %player% 1";
                                str = str.replace("%player%", player.getName());
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), str);
                                player.sendMessage(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("black-messages.yes-fragments")));
                                event.setCancelled(true);
                                return;
                            }
                            player.sendMessage(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("black-messages.no-fragments")));
                            event.setCancelled(true);
                            return;

                        case "CHIAVE RARA":
                            if(Functions.getFragments(player) >= 15){
                                str = "crate giveKey rara %player% 1";
                                str = str.replace("%player%", player.getName());
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), str);
                                player.sendMessage(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("black-messages.yes-fragments")));
                                event.setCancelled(true);
                                return;
                            }
                            player.sendMessage(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("black-messages.no-fragments")));
                            event.setCancelled(true);
                            return;
                        case "CHIAVE EPICA":
                            if(Functions.getFragments(player) >= 25){
                                str = "crate giveKey epica %player% 1";
                                str = str.replace("%player%", player.getName());
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), str);
                                player.sendMessage(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("black-messages.yes-fragments")));
                                event.setCancelled(true);
                                return;
                            }
                            player.sendMessage(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("black-messages.no-fragments")));
                            event.setCancelled(true);
                            return;
                        case "CHIAVE LEGGENDARIA":
                            if(Functions.getFragments(player) >= 35){
                                str = "crate giveKey leggendaria %player% 1";
                                str = str.replace("%player%", player.getName());
                                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), str);
                                player.sendMessage(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("black-messages.yes-fragments")));
                                event.setCancelled(true);
                                return;
                            }
                            player.sendMessage(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("black-messages.no-fragments")));
                            event.setCancelled(true);
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


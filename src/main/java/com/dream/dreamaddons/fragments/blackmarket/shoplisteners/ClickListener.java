package com.dream.dreamaddons.fragments.blackmarket.shoplisteners;


import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.fragments.blackmarket.PerkActivator;
import com.dream.dreamaddons.fragments.blackmarket.gui.ItemMarket;
import com.dream.dreamaddons.fragments.blackmarket.gui.PerkMarket;
import com.dream.dreamaddons.utils.Functions;
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

        if (event.getInventory().getName().equalsIgnoreCase("Black Market")) {

            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.hasItemMeta()) {
                ItemMeta meta = clickedItem.getItemMeta();

                if (meta != null && meta.hasDisplayName()) {
                    String displayName = ChatColor.stripColor(meta.getDisplayName());

                    if (displayName.equalsIgnoreCase("Item Shop")) {
                        ItemMarket.openGui((Player) event.getWhoClicked());
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

        if (event.getInventory().getName().equalsIgnoreCase("Oggetti speciali")) {

            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null && clickedItem.hasItemMeta()) {
                ItemMeta meta = clickedItem.getItemMeta();

                if (meta != null && meta.hasDisplayName()) {
                    String displayName = ChatColor.stripColor(meta.getDisplayName());
                    Player player = (Player) event.getWhoClicked();
                    if (displayName.equalsIgnoreCase(config.getString("item-shop.axe.display-name"))) {
                        if(Functions.getFragments(player) >= 5){
                            Functions.setFragments(player, Functions.getFragments(player) - 5);
                            player.getInventory().addItem(new ItemStack(Material.DIAMOND_AXE));
                            player.sendMessage(DreamAddons.color("&eHai aquistato un ascia"));
                            event.setCancelled(true);
                            return;
                        }
                        player.sendMessage(DreamAddons.color("&cNon cai na lira"));
                        event.setCancelled(true);
                        return;
                    }

                    if (displayName.equalsIgnoreCase("item-shop.obsidian.display-name")) {
                        if(Functions.getFragments(player) >= 7){
                            Functions.setFragments(player, Functions.getFragments(player) - 7);
                            player.getInventory().addItem(new ItemStack(Material.OBSIDIAN, 8));
                            player.sendMessage(DreamAddons.color("&eHai aquistato dell'ossidiana"));
                            event.setCancelled(true);
                            return;
                        }
                        player.sendMessage(DreamAddons.color("&cNon cai na lira"));
                        event.setCancelled(true);
                        return;
                    }

                    if (displayName.equalsIgnoreCase("item-shop.cobweb.display-name")) {
                        if(Functions.getFragments(player) >= 9){
                            Functions.setFragments(player, Functions.getFragments(player) - 9);
                            player.getInventory().addItem(new ItemStack(Material.WEB, 5));
                            player.sendMessage(DreamAddons.color("&eHai aquistato delle ragnatele"));
                            event.setCancelled(true);
                            return;
                        }
                        player.sendMessage(DreamAddons.color("&cNon cai na lira"));
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


package com.dream.dreamaddons.fragments.blackmarket.gui;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.utils.Functions;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemMarket {

    private static FileConfiguration config = DreamAddons.getInstance().getConfig();
    public static void openGui(Player player){
        ItemStack lightGrayGlassPane = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.SILVER.getData());
        ItemStack darkGrayGlassPane = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData());
        Inventory gui = Bukkit.createInventory(player, 27, "Oggetti speciali");

        gui.setItem(10, new ItemStack(axe()));
        gui.setItem(13, new ItemStack(obsidian()));
        gui.setItem(16, new ItemStack(cobweb()));

        gui.setItem(31, new ItemStack(playerHead(player)));

        for (int slot = 0; slot < 36; slot++) {
            if (gui.getItem(slot) == null) {
                if (slot < 27) {
                    gui.setItem(slot, lightGrayGlassPane);
                } else {
                    gui.setItem(slot, darkGrayGlassPane);
                }
            }
        }

        Functions.openGuiAsync(player, gui);
    }

    private static ItemStack playerHead(Player player){

        ItemStack skull = new ItemStack(Material.SKULL_ITEM);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(player.getName());
        Functions.constructLore("main-gui.head-player", meta);

        skull.setItemMeta(meta);

        return skull;
    }

    private static ItemStack axe(){
        ItemStack item = new ItemStack(Material.OBSIDIAN);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(DreamAddons.color(config.getString("item-shop.axe.display-name")));
        Functions.constructLore("item-shop.axe.lore", meta);
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack obsidian(){
        ItemStack item = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(DreamAddons.color(config.getString("item-shop.obsidian.display-name")));
        Functions.constructLore("item-shop.obsidian.lore", meta);
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack cobweb(){
        ItemStack item = new ItemStack(Material.WEB);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(DreamAddons.color(config.getString("item-shop.cobweb.display-name")));
        Functions.constructLore("item-shop.cobweb.lore", meta);
        item.setItemMeta(meta);
        return item;
    }
}

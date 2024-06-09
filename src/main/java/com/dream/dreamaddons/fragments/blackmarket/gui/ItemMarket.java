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
        ItemStack lightGrayGlassPane = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData());
        ItemMeta lightMeta = lightGrayGlassPane.getItemMeta();
        lightMeta.setDisplayName(" ");
        lightGrayGlassPane.setItemMeta(lightMeta);

        ItemStack backGlassPane = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.RED.getData());
        ItemMeta bacMeta = backGlassPane.getItemMeta();
        bacMeta.setDisplayName(DreamAddons.color("&c« &7Torna indietro!"));
        backGlassPane.setItemMeta(bacMeta);

        ItemStack darkGrayGlassPane = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData());
        ItemMeta grayMeta = darkGrayGlassPane.getItemMeta();
        grayMeta.setDisplayName(" ");
        darkGrayGlassPane.setItemMeta(grayMeta);

        Inventory gui = Bukkit.createInventory(player, 36, DreamAddons.color("            &e✦ &0&lOGGETTI &e✦"));

        gui.setItem(11, axe());
        gui.setItem(13, obsidian());
        gui.setItem(15, cobweb());

        gui.setItem(35, backGlassPane);
        gui.setItem(31, playerHead(player));

        for (int slot = 0; slot < 36; slot++) {
            if (gui.getItem(slot) == null) {
                if (slot < 27) {
                    gui.setItem(slot, lightGrayGlassPane);
                } else {
                    gui.setItem(slot, darkGrayGlassPane);
                }
            }
        }

        player.openInventory(gui);
    }

    private static ItemStack playerHead(Player player){

        ItemStack skull = new ItemStack(Material.SKULL_ITEM);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(player.getName());
        Functions.constructLore("main-gui.head-player.lore", meta);

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

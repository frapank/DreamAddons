package com.dream.dreamaddons.blackmarket.gui;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.utils.Functions;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class KeyMarket {

    /* Gui */
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
        Inventory gui = Bukkit.createInventory(player,36, DreamAddons.color("      &e✦ &0&lNEGOZIO KEY &e✦"));

        gui.setItem(10, comuneKey());
        gui.setItem(12, rareKey());
        gui.setItem(14, epicKey());
        gui.setItem(16, leggendaryKey());

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
        meta.setDisplayName(DreamAddons.color("&e&l" + player.getName()));
        meta.setOwner(player.getName());
        Functions.constructLore(DreamAddons.getInstance().getLanguageConfig(), "main-gui.head-player.lore", meta, player);

        skull.setItemMeta(meta);

        return skull;
    }

    //crate giveKey comune G0urmet 1
    //crate giveKey epica G0urmet 1
    //crate giveKey leggendaria G0urmet 1
    //crate giveKey rara G0urmet 1

    /* Item for the Gui */
    private static ItemStack comuneKey(){

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.WHITE.getData());
        ItemMeta meta = glass.getItemMeta();
        meta.setDisplayName(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("key-shop.comune.display-name")));
        Functions.constructLore(DreamAddons.getInstance().getLanguageConfig(), "key-shop.comune.lore", meta);
        glass.setItemMeta(meta);
        return glass;
    }


    private static ItemStack rareKey(){

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GREEN.getData());
        ItemMeta meta = glass.getItemMeta();
        meta.setDisplayName(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("key-shop.raro.display-name")));
        Functions.constructLore(DreamAddons.getInstance().getLanguageConfig(), "key-shop.raro.lore", meta);
        glass.setItemMeta(meta);
        return glass;
    }

    private static ItemStack epicKey(){

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.PURPLE.getData());
        ItemMeta meta = glass.getItemMeta();
        meta.setDisplayName(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("key-shop.epico.display-name")));
        Functions.constructLore(DreamAddons.getInstance().getLanguageConfig(), "key-shop.epico.lore", meta);
        glass.setItemMeta(meta);
        return glass;
    }

    private static ItemStack leggendaryKey(){

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.ORANGE.getData());
        ItemMeta meta = glass.getItemMeta();
        meta.setDisplayName(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("key-shop.legendario.display-name")));
        Functions.constructLore(DreamAddons.getInstance().getLanguageConfig(), "key-shop.legendario.lore", meta);
        glass.setItemMeta(meta);
        return glass;
    }

}

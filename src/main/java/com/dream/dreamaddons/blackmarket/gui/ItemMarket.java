package com.dream.dreamaddons.blackmarket.gui;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.utils.Functions;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

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

        gui.setItem(10, axe());
        gui.setItem(12, obsidian());
        gui.setItem(14, cobweb());
        gui.setItem(16, headOP());

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
        Functions.constructLore(DreamAddons.getInstance().getLanguageConfig(), "main-gui.head-player.lore", meta, player);

        skull.setItemMeta(meta);

        return skull;
    }

    private static ItemStack axe(){
        ItemStack item = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("item-shop.axe.display-name")));
        Functions.constructLore(DreamAddons.getInstance().getLanguageConfig(), "item-shop.axe.lore", meta);
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack obsidian(){
        ItemStack item = new ItemStack(Material.OBSIDIAN);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("item-shop.obsidian.display-name")));
        Functions.constructLore(DreamAddons.getInstance().getLanguageConfig(), "item-shop.obsidian.lore", meta);
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack cobweb(){
        ItemStack item = new ItemStack(Material.WEB);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("item-shop.cobweb.display-name")));
        Functions.constructLore(DreamAddons.getInstance().getLanguageConfig(), "item-shop.cobweb.lore", meta);
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack headOP(){

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        String value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzAwYTFhN2JiMDdmZGI0ZTZhODZlMzQxODE2ZTg4NDNkZGFmN2NmMzcxM2EzNjY2ZDc0YjcyZjk4NjE5ZjA2MyJ9fX0=";
        profile.getProperties().put("textures", new Property("textures", value));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        Functions.constructLore(DreamAddons.getInstance().getLanguageConfig(), "item-shop.goldenapple.lore", meta);
        meta.setDisplayName(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("item-shop.goldenapple.display-name")));

        skull.setItemMeta(meta);

        return skull;
    }

}

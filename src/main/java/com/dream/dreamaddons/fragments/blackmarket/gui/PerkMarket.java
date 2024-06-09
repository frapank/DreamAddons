package com.dream.dreamaddons.fragments.blackmarket.gui;

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

public class PerkMarket {

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
        Inventory gui = Bukkit.createInventory(player,36, DreamAddons.color("      &e✦ &0&lPOTENZIAMENTI &e✦"));

        gui.setItem(10, keepInventoryItemStack());
        gui.setItem(13, doubleMoneyItemStack());
        gui.setItem(16, criticalBoost());
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


    /* Item for the Gui */
    private static ItemStack keepInventoryItemStack(){

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        String value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWYzMGZmYmMwMTEwZWZhMzRlMDMwODYwZGExOGM4YTFkNmIyMjNkZTBmMDBkOWU0YzVkMGNmYTdlY2ZhZmE0OCJ9fX0=";
        profile.getProperties().put("textures", new Property("textures", value));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        Functions.constructLore("keep-inv.lore", meta);
        meta.setDisplayName(DreamAddons.color("&6&lKEEP INVENTORY"));

        skull.setItemMeta(meta);
        return skull;
    }


    private static ItemStack doubleMoneyItemStack(){



        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        String value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWZkMTA4MzgzZGZhNWIwMmU4NjYzNTYwOTU0MTUyMGU0ZTE1ODk1MmQ2OGMxYzhmOGYyMDBlYzdlODg2NDJkIn19fQ==";
        profile.getProperties().put("textures", new Property("textures", value));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        Functions.constructLore("money-boost.lore", meta);
        meta.setDisplayName(DreamAddons.color("&e&lBOOST MONEY"));

        skull.setItemMeta(meta);
        return skull;
    }

    /*
        Avra con un random una possibilà d'infliggere
        più danno
     */
    private static ItemStack criticalBoost(){



        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        String value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzhjZDQ1MWQwODgzN2M2YWVlYjA4YjJhY2Q3ZDNkNzQ3YTNmOTY2MGUyNWZlZDY5Yzg3ZGQ4YTY2OGU1YTdiOCJ9fX0=";
        profile.getProperties().put("textures", new Property("textures", value));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        Functions.constructLore("critical-boost.lore", meta);
        meta.setDisplayName(DreamAddons.color("&c&lCRITICAL BOOST"));

        skull.setItemMeta(meta);
        return skull;
    }


}

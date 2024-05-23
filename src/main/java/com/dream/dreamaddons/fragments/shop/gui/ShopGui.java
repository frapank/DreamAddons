package com.dream.dreamaddons.fragments.shop.gui;

import com.dream.dreamaddons.DreamAddons;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopGui {


    public static void openGui(Player player){

        Inventory gui = Bukkit.createInventory(player,26, "Black Market");

        gui.setItem(10, new ItemStack(keepInventoryItemStack()));
        gui.setItem(13, new ItemStack(doubleXpItemStack()));

        player.openInventory(gui);
    }


    /*
        Questo item costera 12 punti
        Ti permettera di avere la keepInventory
        alla prossima morte

        Head : Una sorta di crafting table
        Nome : KeepInventory
        Lore : Indefinita
     */
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

        List<String> lore = new ArrayList<>();
        lore.add(DreamAddons.color("La &6Keep Inventory &fsarà"));
        lore.add(DreamAddons.color("rimosso alla prossima &cmorte &fo &criavvio"));

        meta.setDisplayName(DreamAddons.color("&6Keep Inventory"));
        meta.setLore(lore);

        skull.setItemMeta(meta);
        return skull;
    }


    /*
        Fino alla morte avra il doppio
        dell'esperienza ad ogni kill

        Head : Una sorta di blocco d'esperienza
        Nome : KeepInventory
        Lore : Indefinita
    */
    private static ItemStack doubleXpItemStack(){



        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        String value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODdkODg1YjMyYjBkZDJkNmI3ZjFiNTgyYTM0MTg2ZjhhNTM3M2M0NjU4OWEyNzM0MjMxMzJiNDQ4YjgwMzQ2MiJ9fX0=";
        profile.getProperties().put("textures", new Property("textures", value));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        List<String> lore = new ArrayList<>();
        lore.add(DreamAddons.color("Il &aBoost XP &fsarà"));
        lore.add(DreamAddons.color("rimosso alla prossima &cmorte &fo &criavvio"));

        meta.setDisplayName(DreamAddons.color("&aBoost XP"));
        meta.setLore(lore);

        skull.setItemMeta(meta);
        return skull;
    }


}

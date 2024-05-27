package com.dream.dreamaddons.fragments.shop.gui;

import com.dream.dreamaddons.DreamAddons;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopGui {

    /* Gui */
    public static void openGui(Player player){

        Inventory gui = Bukkit.createInventory(player,27, "Black Market");

        gui.setItem(11, new ItemStack(keepInventoryItemStack()));
        gui.setItem(13, new ItemStack(doubleMoneyItemStack()));

        player.openInventory(gui);
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

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(DreamAddons.color("&8| &7La Keep Inventory sarà"));
        lore.add(DreamAddons.color("&8| &7rimosso alla prossima morte o riavvio"));
        lore.add("");
        lore.add(DreamAddons.color("&8» &eClicca per aprire"));
        lore.add("");

        meta.setDisplayName(DreamAddons.color("&6Keep Inventory"));
        meta.setLore(lore);

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

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(DreamAddons.color("&8| &7Il Boost Money sarà"));
        lore.add(DreamAddons.color("&8| &7rimosso alla prossima morte o riavvio"));
        lore.add("");
        lore.add(DreamAddons.color("&8» &eClicca per aprire"));
        lore.add("");

        meta.setDisplayName(DreamAddons.color("&eBoost Money"));
        meta.setLore(lore);

        skull.setItemMeta(meta);
        return skull;
    }


}

package com.dream.dreamaddons.fragments.blackmarket.gui;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.utils.Functions;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.Slot;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class MainGuiBlackMarket {


    public static void openGui(Player player){
        ItemStack lightGrayGlassPane = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData());
        ItemMeta lightMeta = lightGrayGlassPane.getItemMeta();
        lightMeta.setDisplayName(" ");
        lightGrayGlassPane.setItemMeta(lightMeta);

        ItemStack darkGrayGlassPane = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData());
        ItemMeta grayMeta = darkGrayGlassPane.getItemMeta();
        grayMeta.setDisplayName(" ");
        darkGrayGlassPane.setItemMeta(grayMeta);

        Inventory gui = Bukkit.createInventory(player, 36, DreamAddons.color("            &e✦ &0&lGENERALE &e✦"));

        gui.setItem(11, itemShop());
        gui.setItem(13, kitShop());
        gui.setItem(15, perkShop());

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

    private static ItemStack itemShop(){

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        String value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTFiZTRiNWI1OTJmZWUyMWE2NWUwZjkwMzAzOGM1MzMzYmUzODgyMzRhNDM3MzFkNGFkZmU1ZDU3ZDM2NDRlNSJ9fX0=";
        profile.getProperties().put("textures", new Property("textures", value));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        Functions.constructLore(DreamAddons.getInstance().getLanguageConfig(), "main-gui.item-shop.lore", meta);
        meta.setDisplayName(DreamAddons.color("&6&lITEM SHOP"));

        skull.setItemMeta(meta);
        return skull;
    }

    private static ItemStack perkShop(){

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        String value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDdiNDRmY2E2MTJlMjRhMWRjZTczN2E0YzNkMjI3NDdmY2UwMDExZDY2MmY2Zjk1NTQ3OTQ1MDBjZGRiYjdjYyJ9fX0=";
        profile.getProperties().put("textures", new Property("textures", value));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        Functions.constructLore(DreamAddons.getInstance().getLanguageConfig(), "main-gui.perk-shop.lore", meta);
        meta.setDisplayName(DreamAddons.color("&6&lPERK SHOP"));

        skull.setItemMeta(meta);
        return skull;
    }

    private static ItemStack kitShop(){

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        String value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWNjZTgwMjQ2NzRiYTk0OGYzMWM0NjNjYmY5NzFiNDk0NmE1NGUzMjMxNDVmYWY0NTQ3YTYyNDJkY2Y2YTFjOCJ9fX0=";
        profile.getProperties().put("textures", new Property("textures", value));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        Functions.constructLore(DreamAddons.getInstance().getLanguageConfig(), "main-gui.kit-shop.lore", meta);
        meta.setDisplayName(DreamAddons.color("&c&lCOMING SOON..."));

        skull.setItemMeta(meta);
        return skull;
    }
}

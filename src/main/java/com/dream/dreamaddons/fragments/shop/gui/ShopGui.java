package com.dream.dreamaddons.fragments.shop.gui;

import com.dream.dreamaddons.DreamAddons;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ShopGui {


    public static void openGui(Player player){

        Inventory gui = Bukkit.createInventory(player,26, "Black Market");

        gui.setItem(10, new ItemStack(keepInventory()));

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
    private static ItemStack keepInventory(){
        ItemStack skull = new ItemStack(Material.SKULL_ITEM);
        ItemMeta meta = skull.getItemMeta();
        //Impostare il tipo di testa

        List<String> lore = new ArrayList<>();
        lore.add(DreamAddons.color("La &6Keep Inventory &fsarà"));
        lore.add(DreamAddons.color("applicata alla prossima &cmorte"));

        meta.setDisplayName(DreamAddons.color("&6Keep Inventory"));
        meta.setLore(lore);

        skull.setItemMeta(meta);
        return skull;
    }
}

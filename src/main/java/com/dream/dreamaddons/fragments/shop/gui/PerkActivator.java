package com.dream.dreamaddons.fragments.shop.gui;

import com.dream.dreamaddons.DreamAddons;
import org.bukkit.entity.Player;

public class PerkActivator {

    /*

    Qua mettere i metodi per attivare i vari perk
    Lo faccio dopo ora non ho voglia

     */

    public static void activateKeepInventory(Player player){
        if(!DreamAddons.getKeepInventoryPlayers().contains(player.getUniqueId())){

            DreamAddons.getKeepInventoryPlayers().add(player.getUniqueId());
        }
    }

    public static void activateBoostXp(Player player){
        if(!DreamAddons.getBoostXpPlayers().contains(player.getUniqueId())){

            DreamAddons.getBoostXpPlayers().add(player.getUniqueId());
        }
    }
}

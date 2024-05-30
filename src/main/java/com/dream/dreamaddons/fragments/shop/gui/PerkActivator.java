package com.dream.dreamaddons.fragments.shop.gui;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.utils.Functions;
import org.bukkit.entity.Player;

public class PerkActivator {

    /*

    Qua mettere i metodi per attivare i vari perk
    Lo faccio dopo ora non ho voglia

     */

    public static void activateKeepInventory(Player player){
        if(!DreamAddons.getKeepInventoryPlayers().contains(player.getUniqueId()) && Functions.getFragments(player) >= 10){

            Functions.setFragments(player, Functions.getFragments(player) - 10);
            DreamAddons.getKeepInventoryPlayers().add(player.getUniqueId());
        }
    }

    public static void activateMoneyBoost(Player player){
        if(!DreamAddons.getBoostMoneyPlayers().contains(player.getUniqueId()) && Functions.getFragments(player) >= 10){

            Functions.setFragments(player, Functions.getFragments(player) - 10);
            DreamAddons.getBoostMoneyPlayers().add(player.getUniqueId());
        }
    }

    public static void activateCriticalBoost(Player player){
        if(!DreamAddons.getCriticalBoostplayers().contains(player.getUniqueId()) && Functions.getFragments(player) >= 10){

            Functions.setFragments(player, Functions.getFragments(player) - 10);
            DreamAddons.getCriticalBoostplayers().add(player.getUniqueId());
        }
    }
}

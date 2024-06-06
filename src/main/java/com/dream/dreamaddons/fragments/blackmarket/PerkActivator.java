package com.dream.dreamaddons.fragments.blackmarket;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.utils.Functions;
import org.bukkit.entity.Player;

public class PerkActivator {

    /*

    Qua mettere i metodi per attivare i vari perk
    Lo faccio dopo ora non ho voglia

     */

    public static void activateKeepInventory(Player player){
        if(!player.hasPermission("dream.boost.keepinv")){
            if(Functions.getFragments(player) >= 10){
                Functions.setFragments(player, Functions.getFragments(player) - 25);
                player.sendMessage(DreamAddons.color("&eHai aquistato la keep invenotry"));
                DreamAddons.getLuckyFunctions().addPermission(player, "dream.boost.keepinv");
                return;
            }
            player.sendMessage(DreamAddons.color("&cNon cai na lira"));
            return;
        }
        player.sendMessage(DreamAddons.color("&cHai gia il boost"));
    }

    public static void activateMoneyBoost(Player player){
        if(!player.hasPermission("dream.boost.boostmoney")){
            if(Functions.getFragments(player) >= 15){
                Functions.setFragments(player, Functions.getFragments(player) - 15);
                player.sendMessage(DreamAddons.color("&eHai aquistato il boost money"));
                DreamAddons.getLuckyFunctions().addPermission(player, "dream.boost.boostmoney");
                return;
            }
            player.sendMessage(DreamAddons.color("&cNon cai na lira"));
            return;
        }
        player.sendMessage(DreamAddons.color("&cHai gia il boost"));
    }

    public static void activateCriticalBoost(Player player){
        if(!player.hasPermission("dream.boost.criticalboost")){
            if(Functions.getFragments(player) >= 25){
                Functions.setFragments(player, Functions.getFragments(player) - 20);
                player.sendMessage(DreamAddons.color("&eHai aquistato il booster critical!"));
                DreamAddons.getLuckyFunctions().addPermission(player, "dream.boost.criticalboost");
                return;
            }
            player.sendMessage(DreamAddons.color("&cNon cai na lira"));
            return;
        }
        player.sendMessage(DreamAddons.color("&cHai gia il boost"));
    }
}

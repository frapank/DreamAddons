package com.dream.dreamaddons.fragments.shop.gui;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.utils.Functions;
import com.dream.dreamaddons.utils.LuckyFunctions;
import org.bukkit.entity.Player;

public class PerkActivator {

    /*

    Qua mettere i metodi per attivare i vari perk
    Lo faccio dopo ora non ho voglia

     */
    private static LuckyFunctions luckyFunctions = new LuckyFunctions();

    public static void activateKeepInventory(Player player){
        if(!player.hasPermission("dream.boost.keepinv")){
            if(Functions.getFragments(player) >= 10){
                Functions.setFragments(player, Functions.getFragments(player) - 10);
                player.sendMessage(DreamAddons.color("&eHai aquistato la keep invenotry"));
                luckyFunctions.addPermission(player, "dream.boost.keepinv");
                return;
            }
            player.sendMessage(DreamAddons.color("&cNon cai na lira"));
            return;
        }
        player.sendMessage(DreamAddons.color("&cHai gia il boost"));
    }

    public static void activateMoneyBoost(Player player){
        if(!player.hasPermission("dream.boost.boostmoney")){
            if(Functions.getFragments(player) >= 10){
                Functions.setFragments(player, Functions.getFragments(player) - 10);
                player.sendMessage(DreamAddons.color("&eHai aquistato il boost money"));
                luckyFunctions.addPermission(player, "dream.boost.boostmoney");
                return;
            }
            player.sendMessage(DreamAddons.color("&cNon cai na lira"));
            return;
        }
        player.sendMessage(DreamAddons.color("&cHai gia il boost"));
    }

    public static void activateCriticalBoost(Player player){
        if(!player.hasPermission("dream.boost.criticalboost")){
            if(Functions.getFragments(player) >= 10){
                Functions.setFragments(player, Functions.getFragments(player) - 10);
                player.sendMessage(DreamAddons.color("&eHai aquistato il booster critical!"));
                luckyFunctions.addPermission(player, "dream.boost.criticalboost");
                return;
            }
            player.sendMessage(DreamAddons.color("&cNon cai na lira"));
            return;
        }
        player.sendMessage(DreamAddons.color("&cHai gia il boost"));
    }
}

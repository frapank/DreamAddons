package com.dream.dreamaddons.fragments.shop.shoplisteners;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.utils.LuckyFunctions;
import com.dream.dreamaddons.utils.NmsUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = (Player) event.getEntity();
        if(player.hasPermission("dream.boost.criticalboost")){
            NmsUtils.sendActionBar(player, DreamAddons.color("&cSei morto, i boost sono stati rimossi ):"));
            DreamAddons.getLuckyFunctions().removePermission(player, "dream.boost.criticalboost");
            player.sendMessage(DreamAddons.color("&eSei morto, di ciao alla Critical Boost"));
        }
        if(player.hasPermission("dream.boost.boostmoney")){
            NmsUtils.sendActionBar(player, DreamAddons.color("&cSei morto, i boost sono stati rimossi ):"));
            DreamAddons.getLuckyFunctions().removePermission(player, "dream.boost.boostmoney");
            player.sendMessage(DreamAddons.color("&eSei morto, di ciao al Boost Money"));
        }
        if(player.hasPermission("dream.boost.criticalboost")){
            NmsUtils.sendActionBar(player, DreamAddons.color("&cSei morto, i boost sono stati rimossi ):"));
            DreamAddons.getLuckyFunctions().removePermission(player, "dream.boost.criticalboost");
            player.sendMessage(DreamAddons.color("&eSei morto, di ciao alla KeepInventory"));
        }
    }
}

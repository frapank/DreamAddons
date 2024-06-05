package com.dream.dreamaddons.fragments.shop.shoplisteners;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.utils.LuckyFunctions;
import com.dream.dreamaddons.utils.NmsUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {

    private static LuckyFunctions luckyFunctions;
    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = (Player) event.getEntity();
        if(DreamAddons.getCriticalBoostplayers().contains(player.getUniqueId())){
            NmsUtils.sendActionBar(player, DreamAddons.color("&cSei morto, i boost sono stati rimossi ):"));
            luckyFunctions.removePermission(player, "dream.boost.criticalboost");
        }
        if(DreamAddons.getBoostMoneyPlayers().contains(player.getUniqueId())){
            NmsUtils.sendActionBar(player, DreamAddons.color("&cSei morto, i boost sono stati rimossi ):"));
            luckyFunctions.removePermission(player, "dream.boost.boostmoney");
        }
        if(DreamAddons.getKeepInventoryPlayers().contains(player.getUniqueId())){
            NmsUtils.sendActionBar(player, DreamAddons.color("&cSei morto, i boost sono stati rimossi ):"));
            luckyFunctions.removePermission(player, "dream.boost.keepinv");
        }
    }
}

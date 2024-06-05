package com.dream.dreamaddons.fragments.shop.shoplisteners;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.utils.Functions;
import com.dream.dreamaddons.utils.NmsUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import javax.swing.text.html.parser.Entity;
import java.util.Random;

public class CriticalListener implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player && event.getDamager().hasPermission("dream.boost.criticalboost")){
            Player damager = (Player) event.getDamager();
            Random random = new Random();
            int randomNumber = random.nextInt(10) + 1;
            if(randomNumber <= 2){
                damager.getWorld().playSound(damager.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
                damager.sendMessage("test");
                Double oldDamage = event.getDamage();
                Double newDamage = oldDamage + 1.5;
                event.setDamage(newDamage);
            }


        }

    }

}

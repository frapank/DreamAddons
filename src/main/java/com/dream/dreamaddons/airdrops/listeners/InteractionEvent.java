package com.dream.dreamaddons.airdrops.listeners;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.utils.Functions;
import com.dream.dreamaddons.utils.NmsUtils;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


public class InteractionEvent implements Listener {

    private static FileConfiguration config = DreamAddons.getInstance().getConfig();

    /* Click armorstand point */
    @EventHandler
    public void onArmorStandInteract(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof ArmorStand && event.getDamager() instanceof Player) {
            ArmorStand armorStand = (ArmorStand) event.getEntity();
            Player player = (Player) event.getDamager();
            if (armorStand.getCustomName() != null && armorStand.getCustomName().equals(DreamAddons.color(config.getString("name")))){
                String soundClaim= config.getString("claim-sound");
                try{
                    armorStand.getWorld().playSound(armorStand.getLocation(), Sound.valueOf(soundClaim), 1.0f, 1.0f);
                } catch (IllegalArgumentException e){
                    DreamAddons.getInstance().getLogger().warning("Config error sound <claim-sound> :" + e);
                }

                Functions.executeCommand(player);
                NmsUtils.playExplosionEffect(armorStand.getLocation());
                armorStand.remove();
            }
        }
    }



}

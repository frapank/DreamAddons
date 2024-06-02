package com.dream.dreamaddons.extra;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.utils.NmsUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DamagerIndicatorListener implements Listener {

    private static FileConfiguration config = DreamAddons.getInstance().getConfig();
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player damager = (Player) event.getDamager();
            Player victim = (Player) event.getEntity();

            new BukkitRunnable() {
                @Override
                public void run() {
                    double newHealth = Math.max(0, victim.getHealth());
                    String formattedHealth = String.format("%.1f", newHealth);

                    NmsUtils.sendActionBar(damager, DreamAddons.color("&f" + victim.getDisplayName() + ": " + formattedHealth + "&c❤"));
                }
            }.runTaskLater(DreamAddons.getInstance(), 1L);
        }
    }

}

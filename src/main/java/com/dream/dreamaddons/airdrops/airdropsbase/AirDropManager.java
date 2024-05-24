package com.dream.dreamaddons.airdrops.airdropsbase;

import com.dream.dreamaddons.DreamAddons;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.UUID;

public class AirDropManager {

    private static FileConfiguration config = DreamAddons.getInstance().getConfig();

    /* Initial spawn airdrop class */
    public static void spawnDrop(Location location){
        World world = location.getWorld();
        if (world == null) return;

        ArmorStand armorStand = world.spawn(location, ArmorStand.class);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        armorStand.setSmall(true);

        if(config.getBoolean("skull-head")){
            ItemStack skull = new ItemStack(skullItem(config.getString("skull-id")));
            armorStand.setHelmet(skull);
        } else {
            String materialName = config.getString("block");
            armorStand.setHelmet(new ItemStack(Material.matchMaterial(materialName)));
        }

        armorStand.setCustomName(DreamAddons.color(config.getString("name")));
        armorStand.setCustomNameVisible(true);

        String soundFall = config.getString("fall-sound");
        String soundEnd = config.getString("end-sound");

        new BukkitRunnable() {
            @Override
            public void run()
            {
                Location currentLocation = new Location(
                        armorStand.getWorld(),
                        armorStand.getLocation().getX(),
                        armorStand.getLocation().getY() + 0.3,
                        armorStand.getLocation().getZ()
                );

                if (currentLocation.getBlock().getType() != Material.AIR || armorStand.isDead()) {
                    try{
                        armorStand.getWorld().playSound(armorStand.getLocation(), Sound.valueOf(soundEnd), 1.0f, 1.0f);
                    } catch (IllegalArgumentException e){
                        DreamAddons.getInstance().getLogger().warning("Config error sound <end-sound> :" + e);
                    }
                    this.cancel();
                    return;
                }

                try{
                    armorStand.getWorld().playSound(armorStand.getLocation(), Sound.valueOf(soundFall), 1.0f, 1.0f);
                } catch (IllegalArgumentException e){
                    DreamAddons.getInstance().getLogger().warning("Config error sound <fall-sound> :" + e);
                    armorStand.remove();
                    this.cancel();
                    return;
                }



                Location newLocation = new Location(
                        armorStand.getWorld(),
                        armorStand.getLocation().getX(),
                        armorStand.getLocation().getY() - 0.4,
                        armorStand.getLocation().getZ(),
                        (float) (armorStand.getLocation().getYaw() + 9),
                        armorStand.getLocation().getPitch()

                );


                armorStand.teleport(newLocation);
            }

        }.runTaskTimer(DreamAddons.getInstance(), 0, 3);
    }

    public static ItemStack skullItem(String id){

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        String value = id;
        profile.getProperties().put("textures", new Property("textures", value));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        skull.setItemMeta(meta);
        return skull;

    }



}

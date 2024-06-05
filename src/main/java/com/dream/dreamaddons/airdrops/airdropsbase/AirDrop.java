package com.dream.dreamaddons.airdrops.airdropsbase;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.utils.NmsUtils;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AirDrop {

    private static FileConfiguration config = DreamAddons.getInstance().getConfig();
    private static Location currentLocation;
    private static Location newLocation;
    private static List<BukkitTask> activeParticleTasks = new ArrayList<>(); // Track active particle tasks

    public static void spawnDrop(Location location) {
        World world = location.getWorld();
        if (world == null) return;

        ArmorStand armorStand = world.spawn(location, ArmorStand.class);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        armorStand.setSmall(true);

        if (config.getBoolean("skull-head")) {
            ItemStack skull = skullItem(config.getString("skull-id"));
            armorStand.setHelmet(skull);
        } else {
            String materialName = config.getString("block");
            armorStand.setHelmet(new ItemStack(Material.valueOf(materialName.toUpperCase())));
        }

        armorStand.setCustomName(DreamAddons.color(config.getString("name")));
        armorStand.setCustomNameVisible(true);

        String soundFall = config.getString("fall-sound");
        String soundEnd = config.getString("end-sound");

        new BukkitRunnable() {
            @Override
            public void run() {
                currentLocation = armorStand.getLocation();

                if (currentLocation.getBlock().getType() != Material.AIR || armorStand.isDead()) {
                    try {
                        armorStand.getWorld().playSound(currentLocation, Sound.valueOf(soundEnd), 1.0f, 1.0f);
                    } catch (IllegalArgumentException e) {
                        DreamAddons.getInstance().getLogger().warning("Config error sound <end-sound>: " + e);
                    }

                    knockbackEntities(armorStand.getLocation(), 5, 2);

                    startLavaParticleEffect(armorStand);

                    this.cancel();
                    return;
                }

                try {
                    armorStand.getWorld().playSound(currentLocation, Sound.valueOf(soundFall), 1.0f, 1.0f);
                } catch (IllegalArgumentException e) {
                    DreamAddons.getInstance().getLogger().warning("Config error sound <fall-sound>: " + e);
                    armorStand.remove();
                    this.cancel();
                    return;
                }

                double deltaX = config.getDouble("airdebug-horizontal-speed", 0.1);
                double deltaZ = config.getDouble("airdebug-horizontal-speed", 0.1);
                double deltaY = config.getDouble("airdebug-down", 0.1);

                newLocation = currentLocation.add(deltaX, -deltaY, deltaZ);
                newLocation.setYaw((float) (armorStand.getLocation().getYaw() + config.getDouble("airdebug-rotation", 5.0)));

                armorStand.getWorld().spigot().playEffect(newLocation, Effect.LARGE_SMOKE, 0, 0, 0.1f, 0.3f, 0.1f, 0.05f, 10, 64);
                armorStand.getWorld().spigot().playEffect(newLocation, Effect.FLAME, 0, 0, 0.1f, 0.3f, 0.1f, 0.05f, 5, 64);

                NmsUtils.spawnFireParticle(newLocation);
                armorStand.teleport(newLocation);
            }
        }.runTaskTimer(DreamAddons.getInstance(), 0, config.getInt("airdebug-period", 2));
    }

    public static ItemStack skullItem(String id) {
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

    private static void knockbackEntities(Location location, double radius, double strength) {
        List<Entity> entities = location.getWorld().getEntities();
        for (Entity entity : entities) {
            if (entity.getLocation().distance(location) <= radius) {
                Vector knockback = entity.getLocation().toVector().subtract(location.toVector()).normalize().multiply(strength);
                entity.setVelocity(knockback);
            }
        }
    }

    private static void startLavaParticleEffect(ArmorStand armorStand) {
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                if (armorStand.isDead() || armorStand.getLocation().getBlock().getType() == Material.AIR) {
                    this.cancel();
                    return;
                }

                Location location = armorStand.getLocation();
                World world = location.getWorld();
                if (world != null) {
                    world.spigot().playEffect(location, Effect.LAVA_POP, 0, 0, 0.5f, 0.5f, 0.5f, 0.05f, 10, 64);
                }
            }
        }.runTaskTimer(DreamAddons.getInstance(), 0, 20);

        activeParticleTasks.add(task);
    }

    public static void removeAllParticles() {
        for (BukkitTask task : activeParticleTasks) {
            task.cancel();
        }
        activeParticleTasks.clear();
    }
}

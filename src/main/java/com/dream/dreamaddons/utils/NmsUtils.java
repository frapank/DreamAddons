package com.dream.dreamaddons.utils;

import com.dream.dreamaddons.DreamAddons;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class NmsUtils {

    public static FileConfiguration config = DreamAddons.getInstance().getConfig();

    public static void sendActionBar(Player player, String message) {
        IChatBaseComponent chatComponent = new ChatComponentText(message);
        PacketPlayOutChat packet = new PacketPlayOutChat(chatComponent, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public static void playExplosionEffect(Location location) {

        if(config.getString("claim-effect").isEmpty()){
            return;
        }

        try {
            EnumParticle paricella = EnumParticle.valueOf(config.getString("claim-effect"));
            float x = (float) location.getX();
            float y = (float) location.getY();
            float z = (float) location.getZ();
            float offsetX = 0;
            float offsetY = 0;
            float offsetZ = 0;
            float speed = 1;
            int count = 100;


            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                    paricella,
                    true,
                    x, y, z,
                    offsetX, offsetY, offsetZ,
                    speed,
                    count
            );

            for (Player player : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
        } catch (Exception e){
            DreamAddons.getInstance().getLogger().warning("Error in claim-effect config:" + e);
        }
    }

    public static void spawnFireParticle(Location location) {

        if(config.getString("particel-up").isEmpty()){
            return;
        }

        try {
            EnumParticle paricella = EnumParticle.valueOf(config.getString("particel-up"));
            float x = (float) location.getX();
            float y = (float) location.getY();
            float z = (float) location.getZ();
            float offsetX = 0;
            float offsetY = 0;
            float offsetZ = 0;
            float speed = 0;
            int count = 1;

            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                    paricella,
                    true,
                    x, y, z,
                    offsetX, offsetY, offsetZ,
                    speed,
                    count
            );

            for (Player user : Bukkit.getOnlinePlayers()) {
                ((CraftPlayer) user).getHandle().playerConnection.sendPacket(packet);
            }
        } catch (Exception e){
            DreamAddons.getInstance().getLogger().warning("Error in particel-up config:" + e);
        }
    }

    public void bossBarCreate(Player player, String name, int status, int timer) {
        World mcWorld = ((CraftWorld) player.getWorld()).getHandle();
        EntityEnderDragon enderDragon = new EntityEnderDragon(mcWorld);

        enderDragon.setLocation(player.getLocation().getX(), player.getLocation().getY() - 100, player.getLocation().getZ(), 0, 0);
        enderDragon.setCustomName(name);
        enderDragon.setCustomNameVisible(true);
        enderDragon.setHealth(enderDragon.getMaxHealth() * (status / 100.0f));

        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(enderDragon);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);

        new BukkitRunnable() {
            @Override
            public void run() {
                PacketPlayOutEntityDestroy destroyPacket = new PacketPlayOutEntityDestroy(enderDragon.getId());
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(destroyPacket);
            }
        }.runTaskLater(DreamAddons.getInstance(), timer * 20L);
    }


}

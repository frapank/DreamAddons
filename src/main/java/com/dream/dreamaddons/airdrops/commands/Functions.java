package com.dream.dreamaddons.airdrops.commands;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.airdrops.airdropsbase.AirDropManager;
import com.dream.dreamaddons.fragments.fragmentItem.Fragment;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class Functions {

    /*

    Qua ci saranno cose come le funzioni per i comandi
    Per esempio funzioni per mandare l'help command e altro.

     */
    private static FileConfiguration config = DreamAddons.getInstance().getConfig();

    public static void spawnAirDrop(Player player){
        Location loc = new Location(
                player.getWorld(),
                player.getLocation().getX(),
                player.getLocation().getY(),
                player.getLocation().getZ()
        );
        AirDropManager.spawnDrop(loc);
        player.sendMessage(DreamAddons.color(config.getString("general.prefix") + config.getString("spawn-message")));
    }

    public static void listAirDrop(Player player){
        player.sendMessage(DreamAddons.color(config.getString("list-message")));
        for(String str : DreamAddons.getSpawns().keySet()){
            String message = config.getString("list-spawn-message");
            message = message.replace("%spawn-id%", str);
            player.sendMessage(config.getString("general.prefix") + DreamAddons.color(message));
        }
    }

    public static void helpMessage(Player player){
        List<String> message = config.getStringList("help-message");
        for (String str : message) {
            player.sendMessage(DreamAddons.color(str));
        }
    }

    public static void createAirDrop(Player player, String[] args){
        for(String key : config.getConfigurationSection("locations").getKeys(false)){
            if(args[1].equalsIgnoreCase(key)){
                player.sendMessage(DreamAddons.color(config.getString("general.prefix") + config.getString("spawn-already-exist")));
                return;
            }
        }

        Location loc = new Location(
                player.getWorld(),
                player.getLocation().getX(),
                player.getLocation().getY(),
                player.getLocation().getZ()
        );

        DreamAddons.getSpawns().put(args[1], loc);
        config.createSection("locations." + args[1]);
        config.set("locations." + args[1] + ".x", player.getLocation().getX());
        config.set("locations." + args[1] + ".y", player.getLocation().getY());
        config.set("locations." + args[1] + ".z", player.getLocation().getZ());
        DreamAddons.getInstance().saveConfig();
        player.sendMessage(DreamAddons.color(config.getString("general.prefix") + config.getString("spawn-added")));
    }

    public static void executeCommand(Player player){
        Random random = new Random(); // Creazione dell'istanza di Random
        int randomNumber = random.nextInt(10) + 1;
        if(randomNumber <= 3){
            Fragment frammento = new Fragment(DreamAddons.getInstance());
            player.getInventory().addItem(frammento.giveFragment(1));
            sendActionBar(player, DreamAddons.color("&9Hai ottenuto 1 frammento"));
            return;
        }

        String str = config.getString("command-claim");
        str = str.replace("%player%", player.getName());
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), str);
        sendActionBar(player, DreamAddons.color("&9Hai ottenuto un pò di soldi"));
    }

    public static void removeAllArmorStand(){
        try{
            List<ArmorStand> armorStands = (List<ArmorStand>) Bukkit.getWorld(config.getString("world")).getEntitiesByClass(ArmorStand.class);
            for(ArmorStand armorStand : armorStands){
                if(armorStand.getCustomName() != null &&
                        (armorStand.getCustomName().equals(DreamAddons.color(config.getString("name"))) ||
                                armorStand.getCustomName().equals(DreamAddons.color("&bAirDrops")))){

                    armorStand.remove();
                }
            }
        } catch (NullPointerException e){
            DreamAddons.getInstance().getLogger().warning("World exeption: " + e);
        }
    }

    public static void sendActionBar(Player player, String message) {
        IChatBaseComponent chatComponent = new ChatComponentText(message);
        PacketPlayOutChat packet = new PacketPlayOutChat(chatComponent, (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public static void playExplosionEffect(Player player, Location location) {
        float x = (float) location.getX();
        float y = (float) location.getY();
        float z = (float) location.getZ();
        float offsetX = 0;
        float offsetY = 0;
        float offsetZ = 0;
        float speed = 1;
        int count = 100;


        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                EnumParticle.SMOKE_NORMAL,
                true,
                x, y, z,
                offsetX, offsetY, offsetZ,
                speed,
                count
        );

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}

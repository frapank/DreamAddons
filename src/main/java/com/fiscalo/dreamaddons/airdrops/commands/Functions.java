package com.fiscalo.dreamaddons.airdrops.commands;

import com.fiscalo.dreamaddons.DreamAddons;
import com.fiscalo.dreamaddons.airdrops.airdropsbase.AirDropManager;
import com.fiscalo.dreamaddons.fragments.fragmentItem.Fragment;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
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
        player.sendMessage(DreamAddons.color(config.getString("spawn-message")));
    }

    public static void listAirDrop(Player player){
        player.sendMessage(DreamAddons.color(config.getString("list-message")));
        for(String str : DreamAddons.getSpawns().keySet()){
            String message = config.getString("list-spawn-message");
            message = message.replace("%spawn-id%", str);
            player.sendMessage(DreamAddons.color(message));
        }
    }

    public static void helpMessage(Player player){
        List<String> preferredFruits = config.getStringList("help-message");
        for (String str : preferredFruits) {
            player.sendMessage(DreamAddons.color(str));
        }
    }

    public static void createAirDrop(Player player, String[] args){
        for(String key : config.getConfigurationSection("locations").getKeys(false)){
            if(args[1].equalsIgnoreCase(key)){
                player.sendMessage(DreamAddons.color(config.getString("spawn-already-exist")));
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
        player.sendMessage(DreamAddons.color(config.getString("spawn-added")));
    }

    public static void executeCommand(Player player){
        Random random = new Random(); // Creazione dell'istanza di Random
        int randomNumber = random.nextInt(2) + 1;
        if(randomNumber == 1){
            String str = config.getString("command-claim");
            str = str.replace("%player%", player.getName());
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), str);
            return;
        }

        Fragment frammento = new Fragment(DreamAddons.getInstance());
        player.getInventory().addItem(frammento.giveFragment(1));
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

}

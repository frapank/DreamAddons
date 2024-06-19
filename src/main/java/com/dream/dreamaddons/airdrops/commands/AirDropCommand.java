package com.dream.dreamaddons.airdrops.commands;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.utils.Functions;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class AirDropCommand implements CommandExecutor {

    private static FileConfiguration config = DreamAddons.getInstance().getConfig();

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("dream.airdrop.admin")) {

                /* Args = 1 */
                if (args.length == 1) {
                    switch (args[0].toLowerCase()) {
                        case "spawn":
                            Functions.spawnAirDrop(player);
                            return true;
                        case "list":
                            Functions.listAirDrop(player);
                            return true;
                        case "reload":
                            DreamAddons.getInstance().reloadConfig();
                            config = DreamAddons.getInstance().getConfig();
                            player.sendMessage(DreamAddons.color("&eConfig Reloaded"));
                            return true;
                        case "debug":
                            player.sendMessage("down: " + config.getInt("airdebug-down"));
                            player.sendMessage("rotation: " + config.getInt("airdebug-rotation"));
                            player.sendMessage("period: " + config.getInt("airdebug-period"));
                            player.sendMessage("count: " + config.getInt("spawn-count"));
                            player.sendMessage("time: " + config.getInt("spawn-time"));
                            return true;
                        default:
                            Functions.helpMessage(player);
                            return true;
                    }
                }

                /* Args = 2 */
                if (args.length == 2) {
                    switch (args[0]){
                        case "create":
                            Functions.createAirDrop(player, args);
                            return true;
                    }
                }

                if(args.length == 2 && args[0].equalsIgnoreCase("debug") && args[1].equalsIgnoreCase("remove")){
                    Location playerLocation = player.getLocation();
                    double radius = 10.0;

                    for (Entity entity : player.getWorld().getEntities()) {
                        if (entity instanceof ArmorStand) {
                            ArmorStand armorStand = (ArmorStand) entity;
                            if (armorStand.getLocation().distance(playerLocation) <= radius) {
                                armorStand.remove();
                            }
                        }
                    }

                    player.sendMessage("Tutti gli armor stand nel raggio di " + radius + " blocchi sono stati rimossi");
                    return true;
                }

            }

            player.sendMessage(DreamAddons.color(DreamAddons.getInstance().getLanguageConfig().getString("no-perm")));
            return true;
        }

        DreamAddons.getInstance().getLogger().warning("Eseguibbile solo in game!");

        return false;
    }

}

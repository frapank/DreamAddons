package com.dream.dreamaddons.airdrops.commands;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.utils.Functions;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;


public class Command implements CommandExecutor {

    private static FileConfiguration config = DreamAddons.getInstance().getConfig();

    /*
    *   Non mi piace
    *   Da rifare sicuramente
    *
    *   Pefforza
    *
    *   */
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if(sender instanceof Player){

            Player player = (Player) sender;
            if(player.hasPermission("dream.admin")){

                /* Args = 1 */
                if(args.length == 1){
                    if(args[0].equalsIgnoreCase("spawn")){
                        Functions.spawnAirDrop(player);
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("list")){
                        Functions.listAirDrop(player);
                        return true;
                    }
                    if(args[0].equalsIgnoreCase("reload")){
                        try {
                            config.save("config.yml");
                            DreamAddons.getInstance().reloadConfig();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        player.sendMessage("&eConfig Reloaded");
                    }
                }

                /* Args = 2 */
                if(args.length == 2 && args[1].equalsIgnoreCase("create")){
                    Functions.createAirDrop(player, args);
                }
                return true;
            }

            player.sendMessage(DreamAddons.color(config.getString("no-perm")));
            return true;
        }

        DreamAddons.getInstance().getLogger().warning("Eseguibbile solo in game!");

        return false;
    }







}

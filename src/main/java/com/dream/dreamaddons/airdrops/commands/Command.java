package com.dream.dreamaddons.airdrops.commands;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.Functions;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


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
                switch(args.length){
                    case 1:
                        one(args, player);
                        return true;
                    case 2:
                        two(args, player);
                        return true;
                    default:
                        Functions.helpMessage(player);
                }
                return true;
            }

            player.sendMessage(DreamAddons.color(config.getString("no-perm")));
            return true;
        }

        DreamAddons.getInstance().getLogger().warning("Eseguibbile solo in game!");

        return false;
    }


    private static void one(String[] args, Player player){
        switch(args[0]){

            case "spawn":
                Functions.spawnAirDrop(player);
                return;
            case "list":
                Functions.listAirDrop(player);
                return;
            default:
                Functions.listAirDrop(player);



        }
    }

    private static void two(String[] args, Player player){
        if(args[0].equalsIgnoreCase("create") && args.length == 2) {
            Functions.createAirDrop(player, args);
        }
    }







}

package com.fiscalo.dreamaddons.itemstats.commands;

import com.fiscalo.dreamaddons.DreamAddons;
import com.fiscalo.dreamaddons.itemstats.ItemStats;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TestCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(player.hasPermission("dream.admin") && player.getItemInHand() != null){
                ItemStack item = player.getItemInHand();
                ItemMeta meta = item.getItemMeta();

                if(!meta.hasLore()){
                    ItemStats.setLore(meta);
                    item.setItemMeta(meta);
                    player.sendMessage(" Meta impostata");
                    return true;
                }

                player.sendMessage(Integer.toString(ItemStats.getKillsCount((ArrayList<String>) meta.getLore())));
                ItemStats.updateKills(meta, ItemStats.getKillsCount((ArrayList<String>) meta.getLore()) + 1);
                item.setItemMeta(meta);
                player.sendMessage(" Meta aggiornata ver.2");
                return true;
            }
        }

        return false;
    }
}

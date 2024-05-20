package com.fiscalo.dreamaddons.fragments.commands;

import com.fiscalo.dreamaddons.DreamAddons;
import com.fiscalo.dreamaddons.fragments.fragmentItem.Fragment;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class FrammentoCommand implements CommandExecutor {

    private final DreamAddons plugin;
    private final Fragment fragment;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;

        if(args.length == 0){
            if(player.hasPermission("dreamfragments.admin")){
                player.getInventory().addItem(fragment.giveFragment(1));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("messages.fragment-item-given")));
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("messages.no-permission")));
            }
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("messages.no-permission")));
        }



        return true;
    }
}

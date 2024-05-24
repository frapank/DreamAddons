package com.dream.dreamaddons.fragments.shop.commandListener;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.fragments.shop.gui.ShopGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGui implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            ShopGui.openGui(player);
            player.sendMessage(DreamAddons.color("&aGui aperta"));

            return true;
        }
        return false;
    }
}

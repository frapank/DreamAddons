package com.dream.dreamaddons.fragments.blackmarket.commandListener;

import com.dream.dreamaddons.fragments.blackmarket.gui.MainGuiBlackMarket;
import com.dream.dreamaddons.fragments.blackmarket.gui.PerkMarket;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBlackMarketGui implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            MainGuiBlackMarket.openGui(player);

            return true;
        }
        return false;
    }
}

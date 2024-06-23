package com.dream.dreamaddons.blackmarket.commandListener;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.blackmarket.gui.MainGuiBlackMarket;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBlackMarketGui implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player){

            Player player = (Player) commandSender;
            if(player.hasPermission("dream.negozio")){
                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
                MainGuiBlackMarket.openGui(player);
            }
            return true;
        }

        if(strings.length == 1){
            String nome = strings[0];
            try {
                Player player = Bukkit.getPlayer(nome);
                MainGuiBlackMarket.openGui(player);
                return true;
            } catch (Exception e){
                DreamAddons.getInstance().getLogger().warning("Warning command Negozio console" + e);
            }
        }
        return false;
    }
}

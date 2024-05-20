package com.fiscalo.dreamaddons.fragments.Storage;

import com.fiscalo.dreamaddons.DreamAddons;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class FragmentManager {

    private final DreamAddons plugin;

    public int getFragments(Player player){
        return plugin.getFragmentsConfig().getInt(player.getName(), 0);
    }

    public void setFragments(Player player, int amount){
        plugin.getFragmentsConfig().set(player.getName(), amount);
        plugin.saveFragmentsConfig();
    }

    public void addFragments(Player player, int amount){
        int current = getFragments(player);
        setFragments(player, current + amount);
    }

}

package com.fiscalo.dreamaddons.fragments.listeners;

import com.fiscalo.dreamaddons.DreamAddons;
import com.fiscalo.dreamaddons.fragments.Storage.FragmentManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor
public class FileListener implements Listener {

    private final DreamAddons plugin;
    private final FragmentManager fragmentManager;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        String playerName = player.getName();

        if (!plugin.getFragmentsConfig().contains(playerName)) {
            plugin.getLogger().info("Il giocatore " + playerName + " è stato registrato nel file frammenti.yml");
            fragmentManager.setFragments(player, 0);
        }
    }
}

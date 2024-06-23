package com.dream.dreamaddons.fragments;

import com.avaje.ebean.validation.NotNull;
import com.dream.dreamaddons.DreamAddons;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class FragmentPlaceHolder extends PlaceholderExpansion {

    private FileConfiguration config = DreamAddons.getInstance().getFragmentsConfig();

    @Override
    public String getAuthor() {
        return "Gourmet"; //


    }

    @Override
    public String getIdentifier() {
        return "dream"; //


    }

    @Override
    public String getVersion() {
        return "1.0.0"; //


    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "null";
        }

        // %dream_fragments%
        if (identifier.equals("fragments")) {

            return String.valueOf(config.getInt(player.getName()));
        }
        return null;
    }
}
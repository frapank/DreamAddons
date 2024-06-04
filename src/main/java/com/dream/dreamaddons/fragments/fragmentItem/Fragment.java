package com.dream.dreamaddons.fragments.fragmentItem;


import com.dream.dreamaddons.DreamAddons;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Setter
@Getter
public class Fragment {

    private final DreamAddons plugin;

    public static ItemStack giveFragment(int amount) {
        ItemStack fragment = new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
        SkullMeta meta = (SkullMeta) fragment.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        String value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzExNDdkODlkMjQ5OGY5ZTIxZjM2YWQzZTUzNTZiMjMyN2M4Zjg1NTE4M2QzZTY5ZjRkNjYwZTViYzIxMGFiYiJ9fX0=";
        profile.getProperties().put("textures", new Property("textures", value));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        String displayName = DreamAddons.getInstance().getConfig().getString("fragments.display-name");
        if (displayName != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        }

        List<String> lore = DreamAddons.getInstance().getConfig().getStringList("fragments.lore");
        if (lore != null && !lore.isEmpty()) {
            for (int i = 0; i < lore.size(); i++) {
                lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
            }
            meta.setLore(lore);
        }

        fragment.setItemMeta(meta);

        return fragment;
    }
}

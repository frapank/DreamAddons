package com.dream.dreamaddons.utils;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.entity.Player;

public class LuckyFunctions {

    private LuckPerms luckPerms;

    public LuckyFunctions() {
        luckPerms = LuckPermsProvider.get();
    }

    public void addPermission(Player player, String permission) {
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user != null)
        {
            Node node = Node.builder(permission).build();
            user.data().add(node);
            luckPerms.getUserManager().saveUser(user);
        }
    }

    public void removePermission(Player player, String permission) {
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user != null)
        {
            Node node = Node.builder(permission).build();
            user.data().remove(node);
            luckPerms.getUserManager().saveUser(user);
        }
    }
}

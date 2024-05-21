package com.fiscalo.dreamaddons.fragments.listeners;

import com.fiscalo.dreamaddons.DreamAddons;
import com.fiscalo.dreamaddons.fragments.Storage.FragmentManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class FragmentListener implements Listener {

    private final DreamAddons plugin;
    private final FragmentManager fragmentManager;

    @EventHandler
    public void onPlayerRedeem(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();

        if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && item != null && item.hasItemMeta()) {
            redeemFragment(player, item);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Material block = event.getBlock().getType();
        if (block.equals(Material.SKULL_ITEM)) {
            fragmentManager.addFragments(player, player.getItemInHand().getAmount());
            player.getInventory().removeItem(player.getItemInHand());
            if(!player.hasPermission("dream.placefragments")){
                event.setCancelled(true);
            }
        }
    }

    private void redeemFragment(Player player, ItemStack item) {
        ItemMeta itemMeta = item.getItemMeta();
        String itemDisplayName = itemMeta.getDisplayName();
        List<String> itemLore = itemMeta.getLore();

        String configDisplayName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("fragments.display-name"));
        List<String> configLore = plugin.getConfig().getStringList("fragments.lore");
        for (int i = 0; i < configLore.size(); i++) {
            configLore.set(i, ChatColor.translateAlternateColorCodes('&', configLore.get(i)));
        }

        if (configDisplayName.equals(itemDisplayName) && configLore.equals(itemLore)) {
            int amount = item.getAmount();
            fragmentManager.addFragments(player, amount);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.fragments-redeemed").replace("%amount%", String.valueOf(amount))));
            player.getInventory().removeItem(item);
        }
    }

    public void removeAllFragments(Player player) {
        Inventory inventory = player.getInventory();
        String configDisplayName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("fragments.display-name"));
        List<String> configLore = plugin.getConfig().getStringList("fragments.lore");
        for (int i = 0; i < configLore.size(); i++) {
            configLore.set(i, ChatColor.translateAlternateColorCodes('&', configLore.get(i)));
        }

        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null && item.hasItemMeta()) {
                ItemMeta itemMeta = item.getItemMeta();
                String itemDisplayName = itemMeta.getDisplayName();
                List<String> itemLore = itemMeta.getLore();

                if (configDisplayName.equals(itemDisplayName) && configLore.equals(itemLore)) {
                    inventory.setItem(i, null);
                }
            }
        }
    }
}

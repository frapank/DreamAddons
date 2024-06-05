package com.dream.dreamaddons.fragments.listeners;

import com.dream.dreamaddons.DreamAddons;
import com.dream.dreamaddons.utils.Functions;
import com.dream.dreamaddons.utils.NmsUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
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

    @EventHandler
    public void onPlayerRedeem(PlayerInteractEvent event) {
        FileConfiguration config = plugin.getConfig();
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = player.getItemInHand();

        if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) {
            return;
        }

        String itemName = item.getItemMeta().getDisplayName();
        String strippedItemName = ChatColor.stripColor(itemName);
        String configuredName = config.getString("fragments.display-name");
        String strippedConfiguredName = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', configuredName));

//        player.sendMessage("redeem richiamato");
//        player.sendMessage("Item Display Name: " + itemName);
//        player.sendMessage("Configured Display Name: " + configuredName);
//        player.sendMessage("Stripped Configured Name: " + strippedConfiguredName);

        if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && item.hasItemMeta()) {
//            player.sendMessage("entrato nell'if1");
            if (strippedItemName.equalsIgnoreCase(strippedConfiguredName)) {
//                player.sendMessage("Entrato nell'if2");
                redeemFragment(player, item);
                NmsUtils.sendActionBar(player, DreamAddons.color("&aHai claimato un frammento"));
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Material block = event.getBlock().getType();
        if (block.equals(Material.SKULL_ITEM)) {
            Functions.addFragments(player, player.getItemInHand().getAmount());
            player.getInventory().removeItem(player.getItemInHand());
            NmsUtils.sendActionBar(player, DreamAddons.color("&aHai claimato un frammento"));
            if(!player.hasPermission("dream.fragment.placefragments")){
                event.setCancelled(true);
            }
        }
    }

    private void redeemFragment(Player player, ItemStack item) {
        ItemMeta itemMeta = item.getItemMeta();
        String itemDisplayName = itemMeta.getDisplayName();
        List<String> itemLore = itemMeta.getLore();

        String configDisplayName = DreamAddons.color(plugin.getConfig().getString("fragments.display-name"));
        List<String> configLore = plugin.getConfig().getStringList("fragments.lore");
        for (int i = 0; i < configLore.size(); i++) {
            configLore.set(i, ChatColor.translateAlternateColorCodes('&', configLore.get(i)));
        }

        if (configDisplayName.equals(itemDisplayName) && configLore.equals(itemLore)) {
            int amount = item.getAmount();
            Functions.addFragments(player, amount);
            player.sendMessage(DreamAddons.color(plugin.getConfig().getString("messages.fragments-redeemed").replace("%amount%", String.valueOf(amount))));
            player.getInventory().removeItem(item);
        }
    }

    public void removeAllFragments(Player player) {
        Inventory inventory = player.getInventory();
        String configDisplayName = DreamAddons.color(plugin.getConfig().getString("fragments.display-name"));
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

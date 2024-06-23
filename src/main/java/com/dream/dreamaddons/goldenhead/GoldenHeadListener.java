package com.dream.dreamaddons.goldenhead;

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
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@RequiredArgsConstructor
@Getter
@Setter
public class GoldenHeadListener implements Listener {

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
        String configuredName =  DreamAddons.getInstance().getLanguageConfig().getString("item-shop.goldenapple.display-name");
        String strippedConfiguredName = ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', configuredName));


        if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && item.hasItemMeta()) {
            if (strippedItemName.equalsIgnoreCase(strippedConfiguredName)) {
                player.getInventory().removeItem(item);
                giveEffect(player);
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
            if(!player.hasPermission("dream.fragment.placefragment")){
                event.setCancelled(true);
            }
        }
    }

    public void giveEffect(Player player){
        try {
            int regenerationDuration = DreamAddons.getInstance().getConfig().getInt("lunar-fragment.regenerationDuration");
            int absorptionDuration = DreamAddons.getInstance().getConfig().getInt("lunar-fragment.absorptionDuration");
            int slowDuration = DreamAddons.getInstance().getConfig().getInt("lunar-fragment.slowDuration");
            int blindDuration = DreamAddons.getInstance().getConfig().getInt("lunar-fragment.blindDuration");

            int regenerationLevel = DreamAddons.getInstance().getConfig().getInt("lunar-fragment.regenerationLevel");
            int slowLevel = DreamAddons.getInstance().getConfig().getInt("lunar-fragment.slowLevel");
            int absorptionLevel = DreamAddons.getInstance().getConfig().getInt("lunar-fragment.absorptionLevel");
            int blindLevel = DreamAddons.getInstance().getConfig().getInt("lunar-fragment.blindLevel");

            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, regenerationDuration, regenerationLevel));
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, absorptionDuration, absorptionLevel));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, slowDuration, slowLevel));
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, blindDuration, blindLevel));
        }catch (Exception e){
            DreamAddons.getInstance().getLogger().warning("Errore nella funzione giveEffect: " + e);
        }

    }
}

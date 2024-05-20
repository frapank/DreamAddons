package com.fiscalo.dreamaddons.fragments.commands;

import com.fiscalo.dreamaddons.DreamAddons;
import com.fiscalo.dreamaddons.fragments.Storage.FragmentManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

@RequiredArgsConstructor
public class MainCommand implements CommandExecutor {

    private final DreamAddons plugin;
    private final FragmentManager fragmentManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (sender instanceof Player) {

            /**
             * Blocco di controllo per vedere se il giocatore ha il permessi da amministratore
             * se lo ha, mostra il messaggio con i comandi da amminisratore,
             * altrimenti mostra quello di default.
             **/

            if (args.length == 0) {
                if (sender.hasPermission("dreamfragments.admin")) {
                    List<String> message = plugin.getConfig().getStringList("general.admin-message");
                    for (String s : message) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
                    }
                } else {
                    List<String> message = plugin.getConfig().getStringList("general.default-message");
                    for (String s : message) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
                    }
                }
            }

            /**
             * Blocco di controllo per i comandi riservati ai giocatori
             */

            if (args.length == 1) {
                String targetName = args[0];
                Player targetPlayer = plugin.getServer().getPlayer(targetName);

                if (targetPlayer != null) {
                    int fragments = fragmentManager.getFragments(targetPlayer);

                    if (targetPlayer.equals(sender)) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("messages.player-fragments-self")).replace("%amount%", fragments + ""));
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("messages.player-fragments")).replace("%amount%", fragments + "").replace("%player%", targetName));
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("messages.player-not-found")).replace("player%", targetName));
                }

            }

            if (args.length == 2) {
                if (player.hasPermission("dreamfragments.admin")) {
                    if (args[0].equalsIgnoreCase("azzera")) {
                        String targetName = args[1];
                        Player targetPlayer = plugin.getServer().getPlayer(targetName);

                        if (targetPlayer != null) {
                            fragmentManager.setFragments(targetPlayer, 0);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("messages.fragments-resetted").replace("%player%", targetName)));
                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("messages.player-not-found")).replace("player%", targetName));
                        }
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("messages.no-permission")));
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("messages.no-permission")));
                }
            }

            if (args.length == 3) {
                if (player.hasPermission("dreamfragments.admin")) {
                    if (args[0].equalsIgnoreCase("imposta")) {
                        String targetName = args[1];
                        Player targetPlayer = plugin.getServer().getPlayer(targetName);

                        if (targetPlayer != null) {
                            int amount = 0;

                            try {
                                amount = Integer.parseInt(args[2]);
                            } catch (NumberFormatException e) {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("messages.invalid-amount")));
                                plugin.getLogger().warning("Invalid amount provided: " + args[2]);
                                return true;
                            }

                            fragmentManager.setFragments(targetPlayer, amount);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("messages.fragments-setted").replace("%player%", targetName).replace("%amount%", amount + "")));
                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("messages.no-permission")));
                        }
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("messages.no-permission")));
                    }
                }

                if (args.length > 3) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + plugin.getConfig().getString("messages.no-permission")));
                }

            }
        } else {
            plugin.getLogger().info("Questo comando è eseguibile solo dai player.");
        }
        return true;
    }
}

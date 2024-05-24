package com.dream.dreamaddons;

import com.dream.dreamaddons.airdrops.airdropsbase.ConfigManager;
import com.dream.dreamaddons.airdrops.airdropsbase.SpawnTask;
import com.dream.dreamaddons.airdrops.commands.Command;
import com.dream.dreamaddons.airdrops.commands.Functions;
import com.dream.dreamaddons.airdrops.listeners.InteractionEvent;
import com.dream.dreamaddons.airdrops.listeners.LeftClickEvent;
import com.dream.dreamaddons.fragments.Storage.FragmentManager;
import com.dream.dreamaddons.fragments.commands.FrammentoCommand;
import com.dream.dreamaddons.fragments.commands.MainCommand;
import com.dream.dreamaddons.fragments.fragmentItem.Fragment;
import com.dream.dreamaddons.fragments.listeners.FileListener;
import com.dream.dreamaddons.fragments.listeners.FragmentListener;
import com.dream.dreamaddons.fragments.shop.commandListener.CommandGui;
import com.dream.dreamaddons.fragments.shop.shoplisteners.ClickListener;
import com.dream.dreamaddons.itemstats.commands.TestCommand;
import com.dream.dreamaddons.itemstats.listeners.KillEvent;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;
import java.util.logging.Level;

public final class DreamAddons extends JavaPlugin {

    @Getter
    private static DreamAddons instance;  // Singleton instance
    @Getter
    private FileConfiguration fragmentsConfig;
    @Getter
    private static HashMap<String, Location> spawns;
    @Getter
    private static LinkedList<UUID> keepInventoryPlayers;
    @Getter
    private static LinkedList<UUID> boostXpPlayers;
    private ConfigManager configManager;
    private BukkitTask spawnerTask;
    private File fragmentsFile;
    private FragmentManager fragmentManager;
    public String prefix;


    @Override
    public void onEnable() {

        /* Base */
        getLogger().warning("<--------------------->");
        getLogger().warning(" Airdrop plugin stared ");
        getLogger().warning("                       ");
        getLogger().warning("   By Gourmet/Fiscalo  ");
        getLogger().warning("<--------------------->");
        saveDefaultConfig();

        /* Initialize */
        instance = this;
        spawns = new HashMap<>();
        configManager = new ConfigManager();
        spawnerTask = getServer().getScheduler().runTaskTimer(this, new SpawnTask(), 0, 20 * 60 * getConfig().getInt("spawn-time"));
        Functions.removeAllArmorStand();
        this.fragmentManager = new FragmentManager(this);
        instance = this;
        prefix = this.getConfig().getString("general.prefix");
        createFragmentsFile();

        /* Commands && events */
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
        saveFragmentsConfig();
    }

    /* Colors */
    public static String color(String msg) {return msg == null ? " " : ChatColor.translateAlternateColorCodes('&', msg);}

    /*Command & events*/
    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new InteractionEvent(), this);
        getServer().getPluginManager().registerEvents(new LeftClickEvent(), this);
        getServer().getPluginManager().registerEvents(new FileListener(this, this.fragmentManager), this);
        getServer().getPluginManager().registerEvents(new FragmentListener(this, this.fragmentManager), this);
        getServer().getPluginManager().registerEvents(new KillEvent(), this);
        getServer().getPluginManager().registerEvents(new ClickListener(), this);
    }
    private void registerCommands() {
        this.getCommand("airdrop").setExecutor(new Command());
        this.getCommand("frammento").setExecutor(new FrammentoCommand(this, new Fragment(this)));
        this.getCommand("frammenti").setExecutor(new MainCommand(this, this.fragmentManager));
        this.getCommand("itemstats").setExecutor(new TestCommand());
        this.getCommand("blackgui").setExecutor(new CommandGui());
    }

    /* Config fragments */
    public void createFragmentsFile() {
        fragmentsFile = new File(getDataFolder(), "frammenti.yml");
        if (!fragmentsFile.exists()) {
            fragmentsFile.getParentFile().mkdirs();
            saveResource("frammenti.yml", false);
        }
        fragmentsConfig = YamlConfiguration.loadConfiguration(fragmentsFile);
    }

    public void saveFragmentsConfig() {
        try {
            fragmentsConfig.save(fragmentsFile);
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Could not save frammenti.yml", e);
        }
    }


}
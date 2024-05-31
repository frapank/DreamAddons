package com.dream.dreamaddons;

import com.dream.dreamaddons.airdrops.airdropsbase.ConfigManager;
import com.dream.dreamaddons.airdrops.airdropsbase.SpawnTask;
import com.dream.dreamaddons.airdrops.commands.Command;
import com.dream.dreamaddons.airdrops.listeners.InteractionEvent;
import com.dream.dreamaddons.airdrops.listeners.LeftClickEvent;
import com.dream.dreamaddons.fragments.commands.FrammentoCommand;
import com.dream.dreamaddons.fragments.commands.MainCommand;
import com.dream.dreamaddons.fragments.fragmentItem.Fragment;
import com.dream.dreamaddons.fragments.listeners.FileListener;
import com.dream.dreamaddons.fragments.listeners.FragmentListener;
import com.dream.dreamaddons.fragments.shop.commandListener.CommandGui;
import com.dream.dreamaddons.fragments.shop.shoplisteners.ClickListener;
import com.dream.dreamaddons.fragments.shop.shoplisteners.CriticalListener;
import com.dream.dreamaddons.fragments.shop.shoplisteners.DeathEvent;
import com.dream.dreamaddons.fragments.shop.shoplisteners.KeepInventoryListener;
import com.dream.dreamaddons.utils.Functions;
import lombok.Getter;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.RegisteredServiceProvider;
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
    private static ArrayList<UUID> keepInventoryPlayers;
    @Getter
    private static ArrayList<UUID> boostMoneyPlayers;
    @Getter
    private static ArrayList<UUID> criticalBoostplayers;
    @Getter
    private static HashMap<Player, Inventory> inventorysForKeep;
    private ConfigManager configManager;
    private BukkitTask spawnerTask;
    private File fragmentsFile;
    public String prefix;


    @Override
    public void onEnable() {


        /* Base */
        getLogger().warning("<--------------------->");
        getLogger().warning(" Airdrop plugin stared ");
        getLogger().warning("                       ");
        getLogger().warning("  By Gourmet/Fiscalo   ");
        getLogger().warning("<--------------------->");
        saveDefaultConfig();

        /* Initialize */
        instance = this;
        spawns = new HashMap<>();
        configManager = new ConfigManager();
        keepInventoryPlayers = new ArrayList<>();
        boostMoneyPlayers = new ArrayList<>();
        criticalBoostplayers = new ArrayList<>();
        inventorysForKeep = new HashMap<>();
        spawnerTask = getServer().getScheduler().runTaskTimer(this, new SpawnTask(), 0, 20 * 60 * getConfig().getInt("spawn-time"));
        instance = this;
        prefix = this.getConfig().getString("general.prefix");

        /* Other */
        Functions.removeAllArmorStand();
        createFragmentsFile();

        /* Commands && events */
        registerCommands();
        registerListeners();

        /* LuckPerms */
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            LuckPerms api = provider.getProvider();

        }

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
        getServer().getPluginManager().registerEvents(new FileListener(this), this);
        getServer().getPluginManager().registerEvents(new FragmentListener(this), this);
        getServer().getPluginManager().registerEvents(new ClickListener(), this);
        getServer().getPluginManager().registerEvents(new CriticalListener(), this);
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        getServer().getPluginManager().registerEvents(new KeepInventoryListener(), this);
    }
    private void registerCommands() {
        this.getCommand("airdrop").setExecutor(new Command());
        this.getCommand("frammento").setExecutor(new FrammentoCommand(this, new Fragment(this)));
        this.getCommand("frammenti").setExecutor(new MainCommand(this));
        this.getCommand("blackmarketgui").setExecutor(new CommandGui());
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
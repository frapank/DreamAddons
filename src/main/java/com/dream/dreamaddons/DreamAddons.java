package com.dream.dreamaddons;

import com.dream.dreamaddons.airdrops.airdropsbase.AirDropSpawnerLoader;
import com.dream.dreamaddons.airdrops.airdropsbase.SpawnTask;
import com.dream.dreamaddons.airdrops.commands.AirDropCommand;
import com.dream.dreamaddons.airdrops.listeners.RightClickClaim;
import com.dream.dreamaddons.airdrops.listeners.LeftClickClaim;
import com.dream.dreamaddons.extra.DamagerIndicator;
import com.dream.dreamaddons.fragments.commands.FragmentMainCommand;
import com.dream.dreamaddons.fragments.listeners.FileListener;
import com.dream.dreamaddons.fragments.listeners.FragmentListener;
import com.dream.dreamaddons.fragments.shop.commandListener.CommandBlackMarketGui;
import com.dream.dreamaddons.fragments.shop.shoplisteners.ClickListener;
import com.dream.dreamaddons.fragments.shop.shoplisteners.CriticalListener;
import com.dream.dreamaddons.fragments.shop.shoplisteners.DeathEvent;
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
    private AirDropSpawnerLoader configManager;
    private BukkitTask spawnerTask;
    private File fragmentsFile;
    public String prefix;


    @Override
    public void onEnable() {


        /* Base */
        getLogger().warning("<------------------------>");
        getLogger().warning(" DreamAddon plugin stared ");
        getLogger().warning("                          ");
        getLogger().warning("     By Gourmet/Fiscalo   ");
        getLogger().warning("<------------------------>");
        saveDefaultConfig();

        /* Initialize */
        instance = this;
        spawns = new HashMap<>();
        configManager = new AirDropSpawnerLoader();
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
        getServer().getPluginManager().registerEvents(new RightClickClaim(), this);
        getServer().getPluginManager().registerEvents(new LeftClickClaim(), this);
        getServer().getPluginManager().registerEvents(new FileListener(this), this);
        getServer().getPluginManager().registerEvents(new FragmentListener(this), this);
        getServer().getPluginManager().registerEvents(new ClickListener(), this);
        getServer().getPluginManager().registerEvents(new CriticalListener(), this);
        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
        getServer().getPluginManager().registerEvents(new DamagerIndicator(), this);
    }
    private void registerCommands() {
        this.getCommand("airdrop").setExecutor(new AirDropCommand());
        this.getCommand("frammenti").setExecutor(new FragmentMainCommand(this));
        this.getCommand("frammento").setExecutor(new FragmentMainCommand(this));
        this.getCommand("blackmarketgui").setExecutor(new CommandBlackMarketGui());
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
package com.dream.dreamaddons;

import com.dream.dreamaddons.airdrops.airdropsbase.AirDropSpawnerLoader;
import com.dream.dreamaddons.airdrops.airdropsbase.SpawnTask;
import com.dream.dreamaddons.airdrops.commands.AirDropCommand;
import com.dream.dreamaddons.airdrops.listeners.RightClickClaim;
import com.dream.dreamaddons.airdrops.listeners.LeftClickClaim;
import com.dream.dreamaddons.goldenhead.GoldenHeadListener;
import com.dream.dreamaddons.utils.DamagerIndicator;
import com.dream.dreamaddons.fragments.FragmentPlaceHolder;
import com.dream.dreamaddons.fragments.commands.FragmentMainCommand;
import com.dream.dreamaddons.fragments.listeners.FileListener;
import com.dream.dreamaddons.fragments.listeners.FragmentListener;
import com.dream.dreamaddons.blackmarket.commandListener.CommandBlackMarketGui;
import com.dream.dreamaddons.blackmarket.shoplisteners.ClickListener;
import com.dream.dreamaddons.blackmarket.shoplisteners.CriticalListener;
import com.dream.dreamaddons.blackmarket.shoplisteners.DeathEvent;
import com.dream.dreamaddons.utils.Functions;
import com.dream.dreamaddons.utils.LuckyFunctions;
import lombok.Getter;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

public final class DreamAddons extends JavaPlugin {

    @Getter
    private static DreamAddons instance;
    @Getter
    private static HashMap<String, Location> spawns;
    @Getter
    private static LuckyFunctions luckyFunctions;
    private AirDropSpawnerLoader airDropSpawnerLoader;
    private BukkitTask spawnerTask;
    public String prefix;

    /* Config dec. */
    @Getter
    private FileConfiguration fragmentsConfig;
    @Getter
    private FileConfiguration languageConfig;
    private File fragmentsFile;
    private File languageFile;


    @Override
    public void onEnable() {


        /* Base */
        getLogger().warning("<------------------------>");
        getLogger().warning(" DreamAddon plugin stared ");
        getLogger().warning("                          ");
        getLogger().warning("    By Gourmet/Fiscalo    ");
        getLogger().warning("<------------------------>");

        /* Conifg */
        saveDefaultConfig();
        createLanguageFile();
        createFragmentsFile();
        saveLanguageConfig();
        saveFragmentsConfig();
        this.prefix = "&f&lDREAM&9&lADDONS &7»";

        /* Initialize */
        instance = this;
        luckyFunctions = new LuckyFunctions();
        spawns = new HashMap<>();
        this.airDropSpawnerLoader = new AirDropSpawnerLoader(this);
        this.spawnerTask = getServer().getScheduler().runTaskTimer(this, new SpawnTask(), 0, 20 * 60 * getConfig().getInt("spawn-time"));


        /* Other */
        Functions.removeAllArmorStand();

        /* Commands && events */
        registerCommands();
        registerListeners();

        /* LuckPerms */
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            LuckPerms api = provider.getProvider();

        }

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new FragmentPlaceHolder().register();
        } else {
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

    }

    @Override
    public void onDisable() {
        saveFragmentsConfig();
        saveLanguageConfig();
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
        getServer().getPluginManager().registerEvents(new GoldenHeadListener(this), this);
    }
    private void registerCommands() {
        this.getCommand("airdrop").setExecutor(new AirDropCommand());
        this.getCommand("frammenti").setExecutor(new FragmentMainCommand(this));
        this.getCommand("frammento").setExecutor(new FragmentMainCommand(this));
        this.getCommand("negozio").setExecutor(new CommandBlackMarketGui());
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

    /* Config fragments */
    public void createLanguageFile() {
        languageFile = new File(getDataFolder(), "language.yml");
        if (!languageFile.exists()) {
            languageFile.getParentFile().mkdirs();
            saveResource("language.yml", false);
        }
        languageConfig = YamlConfiguration.loadConfiguration(languageFile);
    }

    public void saveLanguageConfig() {
        try {
            languageConfig.save(languageFile);
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Could not save language.yml", e);
        }
    }






}
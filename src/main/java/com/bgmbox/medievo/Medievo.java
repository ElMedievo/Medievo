package com.bgmbox.medievo;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;

import static com.bgmbox.medievo.Configuration.LoadConfig.loadConfig;
import static com.bgmbox.medievo.Ranks.CreateRanksFile.createRanksXMLFile;
import static com.bgmbox.medievo.util.CommandRegistry.registerCommands;
import static com.bgmbox.medievo.util.EventRegistry.registerEvents;

public final class Medievo extends JavaPlugin {

    public static Medievo instance;

    private Logger log = Bukkit.getLogger();

    @Override
    public void onEnable() {
        instance = this;
        log.info("The Medieval plugin has been enabled");
        registerCommands();
        registerEvents();

        loadConfig();
        createRanksXMLFile();
    }

    @Override
    public void onDisable() {
        log.info("The Medieval plugin has been disabled");
    }
}

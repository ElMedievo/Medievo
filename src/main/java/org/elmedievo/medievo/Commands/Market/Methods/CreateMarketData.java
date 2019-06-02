package org.elmedievo.medievo.Commands.Market.Methods;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.elmedievo.medievo.Medievo;

import java.io.*;

import static org.elmedievo.medievo.util.Generic.*;
import static org.elmedievo.medievo.util.Methods.ConsoleAlerts.sendConsoleAlert;

public class CreateMarketData {
    private static Medievo plugin;

    private static File marketDataYMLFile;
    private static FileConfiguration marketDataYMLConfiguration;

    public static void createMarketDataYML() {
        try {
            generateMarketDataYML();
            if (!marketDataYMLFile.exists()) {
                sendConsoleAlert(MARKET_DATA_FILE_NOT_FOUND);
                marketDataYMLFile.createNewFile();
                writeMarketDataDefaults();
            } else {
                sendConsoleAlert(MARKET_DATA_FILE_FOUND);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            sendConsoleAlert(MARKET_DATA_CANNOT_CREATE);
        }
    }

    private static void generateMarketDataYML() {
        marketDataYMLFile = new File(getMedievoFolder(), "market.yml");
        marketDataYMLConfiguration = YamlConfiguration.loadConfiguration(marketDataYMLFile);
    }

    static FileConfiguration getMarketData() {
        return marketDataYMLConfiguration;
    }

    private static void saveMarketData() {
        try {
            marketDataYMLConfiguration.save(marketDataYMLFile);
        } catch (IOException exception) {
            exception.printStackTrace();
            sendConsoleAlert(MARKET_DATA_CANNOT_SAVE);
        }
    }

    public static void reloadMarketData() {
        marketDataYMLConfiguration = YamlConfiguration.loadConfiguration(marketDataYMLFile);
    }

    private static void writeMarketDataDefaults() {
        String examplePath = "interface.stock.example.";
        getMarketData().set("interface.title", "Mercado Medieval");
        getMarketData().set("interface.rows", 3);
        getMarketData().set(examplePath + "material", "GOLD_PICKAXE");
        getMarketData().set(examplePath + "damage", 17);
        getMarketData().set(examplePath + "amount", 3);
        getMarketData().set(examplePath + "name", "&e&lTHE TEST");
        getMarketData().set(examplePath + "lore", "&e&lTEST LORE");
        getMarketData().set(examplePath + "special", false);
        getMarketData().set(examplePath + "position", 13);
        getMarketData().set(examplePath + "price", 500);
        saveMarketData();
    }
}

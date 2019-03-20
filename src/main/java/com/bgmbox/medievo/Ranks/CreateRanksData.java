package com.bgmbox.medievo.Ranks;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static com.bgmbox.medievo.util.Generic.*;
import static com.bgmbox.medievo.util.Methods.ConsoleAlerts.sendConsoleAlert;

public class CreateRanksData {
    private static File ranksDataYMLFile;
    private static FileConfiguration ranksDataYMLConfiguration;

    public static void createRanksDataYML() {
        try {
            generateRanksDataYML();
            if (!ranksDataYMLFile.exists()) {
                sendConsoleAlert(RANKS_DATA_FILE_NOT_FOUND);
                ranksDataYMLFile.createNewFile();
            } else {
                sendConsoleAlert(RANKS_DATA_FILE_FOUND);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            sendConsoleAlert(RANKS_DATA_CANNOT_CREATE);
        }
    }

    private static void generateRanksDataYML() {
        ranksDataYMLFile = new File(getMedievoFolder(), "ranks.yml");
        ranksDataYMLConfiguration = YamlConfiguration.loadConfiguration(ranksDataYMLFile);
    }

    public static FileConfiguration getRanksData() {
        return ranksDataYMLConfiguration;
    }

    public static void saveRanksData() {
        try {
            ranksDataYMLConfiguration.save(ranksDataYMLFile);
        } catch (IOException exception) {
            exception.printStackTrace();
            sendConsoleAlert(RANKS_DATA_CANNOT_SAVE);
        }
    }

    public static void reloadRanksData() {
        ranksDataYMLConfiguration = YamlConfiguration.loadConfiguration(ranksDataYMLFile);
    }
}

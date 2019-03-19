package com.bgmbox.medievo.Configuration;

import com.bgmbox.medievo.Medievo;

import static com.bgmbox.medievo.util.Generic.LOADED_CONFIG;
import static com.bgmbox.medievo.util.Methods.ConsoleAlerts.sendConsoleAlert;

public class LoadConfig {
    public static void loadConfig() {
        Medievo.instance.getConfig().options().copyDefaults(true);
        Medievo.instance.saveConfig();
        sendConsoleAlert(LOADED_CONFIG);
    }
}

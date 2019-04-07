package org.elmedievo.medievo.Configuration;

import org.elmedievo.medievo.Medievo;

import static org.elmedievo.medievo.util.Generic.LOADED_CONFIG;
import static org.elmedievo.medievo.util.Methods.ConsoleAlerts.sendConsoleAlert;

public class LoadConfig {
    public static void loadConfig() {
        Medievo.instance.getConfig().options().copyDefaults(true);
        Medievo.instance.saveConfig();
        sendConsoleAlert(LOADED_CONFIG);
    }
}

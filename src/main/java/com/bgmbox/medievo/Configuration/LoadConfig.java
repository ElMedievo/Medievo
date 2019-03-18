package com.bgmbox.medievo.Configuration;

import com.bgmbox.medievo.Medievo;

public class LoadConfig {
    public static void loadConfig() {
        Medievo.instance.getConfig().options().copyDefaults(true);
        Medievo.instance.saveConfig();
    }
}

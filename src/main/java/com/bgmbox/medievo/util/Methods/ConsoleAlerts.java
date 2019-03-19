package com.bgmbox.medievo.util.Methods;

import org.bukkit.Bukkit;

import java.util.logging.Logger;

public class ConsoleAlerts {
    public static void sendConsoleAlert(String msg) {
        Bukkit.getServer().getConsoleSender().sendMessage(msg);
    }

    public static void logInfo(String msg) {
        Logger log = Bukkit.getLogger();
        log.info(msg);
    }
}

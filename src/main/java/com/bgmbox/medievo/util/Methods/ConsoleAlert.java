package com.bgmbox.medievo.util.Methods;

import org.bukkit.Bukkit;

public class ConsoleAlert {
    public static void sendConsoleAlert(String msg) {
        Bukkit.getServer().getConsoleSender().sendMessage(msg);
    }
}

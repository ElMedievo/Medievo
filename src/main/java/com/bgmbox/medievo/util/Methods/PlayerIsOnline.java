package com.bgmbox.medievo.util.Methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerIsOnline {
    public static boolean playerIsOnline(String name, Boolean offlineInclusive) {
        if (!offlineInclusive) {
            Player player = Bukkit.getServer().getPlayer(name);
            return player != null;
        }
        //TODO: Actually fix this thing. Cycle through data.
        return false;
    }
}

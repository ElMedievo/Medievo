package org.elmedievo.medievo.util.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerIsOnline {
    public static boolean playerIsOnline(String name) {
        Player player = Bukkit.getServer().getPlayer(name);
        return player != null;
    }

    public static String evalOnlinePlayer(String name) {
        if (playerIsOnline(name)) {
            Player player = Bukkit.getServer().getPlayer(name);
            return player.getDisplayName();
        } else {
            return ChatColor.DARK_AQUA + name;
        }
    }
}

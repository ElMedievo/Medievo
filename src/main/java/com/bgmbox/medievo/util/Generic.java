package com.bgmbox.medievo.util;

import org.bukkit.ChatColor;

public class Generic {
    private static String errorPrefix = ChatColor.YELLOW + "⚠ " + ChatColor.RED;
    public static String
            NO_CONSOLE = errorPrefix + "You must be a player to execute this command",
            TOO_MANY_ARGS = errorPrefix + "Too many arguments",
            TOO_FEW_ARGS = errorPrefix + "Too few arguments",
            NO_PERMISSION = errorPrefix + "You do not have permission";
}

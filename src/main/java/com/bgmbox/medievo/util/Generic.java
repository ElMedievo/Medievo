package com.bgmbox.medievo.util;

import com.bgmbox.medievo.Medievo;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.Contract;

import java.io.File;

public class Generic {

    @Contract(pure = true)
    public static File getMedievoFolder() {
        return Medievo.instance.getDataFolder();
    }

    private static String errorPrefix = ChatColor.YELLOW + "⚠ " + ChatColor.RED;
    public static String
            NO_CONSOLE = errorPrefix + "You must be a player to execute this command",
            TOO_MANY_ARGS = errorPrefix + "Too many arguments",
            TOO_FEW_ARGS = errorPrefix + "Too few arguments",
            NO_PERMISSION = errorPrefix + "You do not have permission";
}

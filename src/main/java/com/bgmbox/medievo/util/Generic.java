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
            NO_PERMISSION = errorPrefix + "You do not have permission",
            SQL_CANNOT_CONNECT = "An internal error has occurred while connecting to the SQL Database.",
            RANKS_CANNOT_CREATE = "An internal error has occurred while generating the server ranks file";

    private static String successPrefix = ChatColor.GREEN + "";
    public static String
            LOADED_CONFIG = successPrefix + "Plugin configuration was successfully loaded",
            RANKS_FILE_FOUND = successPrefix + "Successfully loaded the ranks file. Parsing...",
            RANKS_FILE_NOT_FOUND = successPrefix + "Ranks file not found. Creating one...",
            SQL_CONNECT_SUCCESS = successPrefix + "Connected to SQL database successfully";
}

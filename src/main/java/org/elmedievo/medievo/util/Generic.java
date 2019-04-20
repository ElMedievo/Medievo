package org.elmedievo.medievo.util;

import org.elmedievo.medievo.Medievo;
import org.bukkit.ChatColor;
import org.jetbrains.annotations.Contract;

import java.io.File;

public class Generic {

    @Contract(pure = true)
    public static File getMedievoFolder() {
        return Medievo.instance.getDataFolder();
    }

    public static String WARNING_ICON = ChatColor.YELLOW + "⚠ ";


    private static String chatErrorPrefix = ChatColor.YELLOW + "⚠ " + ChatColor.RED;
    private static String consoleErrorPrefix = ChatColor.RED + "";
    public static String
            NO_CONSOLE = consoleErrorPrefix + "You must be a player to execute this command",
            TOO_MANY_ARGS = chatErrorPrefix + "Too many arguments",
            TOO_FEW_ARGS = chatErrorPrefix + "Too few arguments",
            NO_PERMISSION = chatErrorPrefix + "You do not have permission",
            SQL_CANNOT_CONNECT = consoleErrorPrefix + "An internal error has occurred while connecting to SQL Database.",
            RANKS_CANNOT_CREATE = consoleErrorPrefix + "An internal error has occurred while generating ranks.xml file",
            RANKS_DATA_CANNOT_CREATE = consoleErrorPrefix + "An internal error has occurred while generating ranks.yml file",
            RANKS_DATA_CANNOT_SAVE = consoleErrorPrefix + "An internal error has occurred while saving ranks.yml file",
            FEATURE_NOT_IMPLEMENTED = chatErrorPrefix + "Feature not implemented yet",
            GENERIC_SYNTAX_ERROR = chatErrorPrefix + "Syntax error",
            NO_PENDANT_INVITE = chatErrorPrefix + "You don't have any pendant clan invitation",
            CLANS_COMMAND_ERROR = "\n" + ChatColor.RED + "Try using " + ChatColor.YELLOW + "/clan help " + ChatColor.RED + "for more information.",
            NO_CHAT_MATCHED_QUERY = chatErrorPrefix + "No chat mode matched query",
            NOT_IN_A_CLAN = chatErrorPrefix + "You are not in a clan!",
            ALREADY_IN_ADMIN_CHAT = chatErrorPrefix + "Your chat is already set to admin mode.",
            ALREADY_IN_GLOBAL_CHAT = chatErrorPrefix + "Your chat is already set to global mode.",
            ALREADY_IN_CLAN_CHAT = chatErrorPrefix  + "Your chat is already set to clan mode",
            CLAN_NAME_TOO_LONG = chatErrorPrefix + "Clan names must be under 16 characters long.",
            CLANS_NOT_ENABLED = chatErrorPrefix + "Clans are not enabled.",
            CANNOT_DEPOSIT = chatErrorPrefix + "You may not deposit that material! Medieval gold only!",
            CLAN_NOT_FOUND = chatErrorPrefix + "Clan not found.";

    private static String successPrefix = ChatColor.GREEN + "";
    public static String
            LOADED_CONFIG = successPrefix + "Plugin configuration was successfully loaded",
            RANKS_FILE_FOUND = successPrefix + "Successfully loaded ranks.xml file.",
            RANKS_FILE_NOT_FOUND = successPrefix + "ranks.xml file not found. Creating one...",
            RANKS_DATA_FILE_FOUND = successPrefix + "Successfully loaded the ranks.yml file.",
            RANKS_DATA_FILE_NOT_FOUND = successPrefix + "ranks.yml file not found. Creating one...",
            SQL_CONNECT_SUCCESS = successPrefix + "Connected to SQL database successfully",
            ADMIN_CHAT_SET = successPrefix + "Chat mode set to: " + ChatColor.RESET + ChatColor.ITALIC + "Admin",
            CLAN_CHAT_SET = successPrefix + "Chat mode set to: " + ChatColor.RESET + ChatColor.ITALIC + "Clan",
            GLOBAL_CHAT_SET = successPrefix + "Chat mode set to: " + ChatColor.RESET + ChatColor.ITALIC + "Global",
            RELOADED_CONFIG = successPrefix + "Configuration reloaded successfully",
            DEPOSIT_SUCCESS = successPrefix + "Deposit complete";
}

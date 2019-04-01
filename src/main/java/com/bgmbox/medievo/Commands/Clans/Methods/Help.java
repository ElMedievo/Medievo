package com.bgmbox.medievo.Commands.Clans.Methods;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Help {
    public static void displayClanHelpMenu(Player player) {
        player.sendMessage(ChatColor.RED + "#################### " + ChatColor.AQUA + ChatColor.BOLD + "CLANS HELP" + ChatColor.RESET + ChatColor.RED + " ####################" + "\n"
                + ChatColor.GREEN + "» " + ChatColor.GOLD + "/clan info" + "\n"
                + ChatColor.GREEN + "» " + ChatColor.GOLD + "/clan create 'name'" + "\n"
                + ChatColor.GREEN + "» " + ChatColor.GOLD + "/clan disband 'name'" + "\n"
                + ChatColor.GREEN + "» " + ChatColor.GOLD + "/clan invite 'player'" + "\n"
                + ChatColor.RED + "########################################"
        );
    }
}

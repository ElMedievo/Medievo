package org.elmedievo.medievo.Commands.Clans.Methods;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Help {
    public static void displayClanHelpMenuToPlayer(Player player) {
        player.sendMessage(ChatColor.RED + "########## " + ChatColor.AQUA + ChatColor.BOLD + "CLAN HELP" + ChatColor.RESET + ChatColor.RED + " ##########" + "\n"
                + ChatColor.GREEN + "» " + ChatColor.GOLD + "/clan info 'clan'" + "\n"
                + ChatColor.GREEN + "» " + ChatColor.GOLD + "/clan create 'clan'" + "\n"
                + ChatColor.GREEN + "» " + ChatColor.GOLD + "/clan disband 'clan'" + "\n"
                + ChatColor.GREEN + "» " + ChatColor.GOLD + "/clan invite 'player'" + "\n"
                + ChatColor.GREEN + "» " + ChatColor.GOLD + "/clan remove 'player'" + "\n"
                + ChatColor.GREEN + "» " + ChatColor.GOLD + "/clan deposit" + "\n"
                + ChatColor.GREEN + "» " + ChatColor.GOLD + "/clan leave" + "\n"
                + ChatColor.GREEN + "» " + ChatColor.GOLD + "/clan list" + "\n"
                + ChatColor.RED + "###############################"
        );
    }
}

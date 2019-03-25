package com.bgmbox.medievo.Commands.Ranks;

import com.bgmbox.medievo.Medievo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.bgmbox.medievo.Ranks.Methods.checkRankExistence.rankExists;
import static com.bgmbox.medievo.Ranks.Methods.rankAdd.addRank;
import static com.bgmbox.medievo.Ranks.Methods.rankRemove.removeRank;
import static com.bgmbox.medievo.util.Generic.*;
import static com.bgmbox.medievo.util.Methods.PlayerIsOnline.playerIsOnline;

public class rank implements CommandExecutor {

    private final Medievo plugin;

    private rank(Medievo instance) {
        plugin = instance;
    }

    // /rank add player rank

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("rank") && sender.hasPermission("medievo.rank")) {
            if (args.length == 3) {
                String givenPlayerName = args[1];
                String givenRank = args[2];
                if (playerIsOnline(givenPlayerName)) {
                    Player reciever = Bukkit.getServer().getPlayer(args[1]);
                    if (rankExists(givenRank)) {
                        String giver;
                        if (sender instanceof Player) {
                            Player player = (Player) sender;
                            giver = player.getDisplayName();
                        } else {
                            giver = CONSOLE_PREFIX;
                        }
                        switch (args[0]) {
                            case "add":
                                addRank(reciever, givenRank);
                                sender.sendMessage(ChatColor.GREEN + "Rank successfully added");
                                reciever.sendMessage(ChatColor.GREEN + "You have been granted the " + ChatColor.WHITE + givenRank + ChatColor.GREEN + " rank by: " + ChatColor.RESET + giver);
                                break;
                            case "remove":
                                sender.sendMessage(ChatColor.RED + "Rank successfully removed");
                                reciever.sendMessage(ChatColor.RED + "You have been demoted from " + ChatColor.WHITE + givenRank + ChatColor.RED + " by: " + ChatColor.RESET + giver);
                                removeRank(reciever, givenRank);
                                break;
                            default:
                                sender.sendMessage(GENERIC_SYNTAX_ERROR);
                        }
                    } else {
                        sender.sendMessage(WARNING_ICON + ChatColor.WHITE + givenRank + ChatColor.RED + " is not a rank.");
                    }
                } else {
                    sender.sendMessage(WARNING_ICON + ChatColor.DARK_AQUA + givenPlayerName + ChatColor.RED + " is currently Offline or is not a valid player.");
                }
            } else if (args.length <= 2) {
                sender.sendMessage(TOO_FEW_ARGS);
            } else {
                sender.sendMessage(TOO_MANY_ARGS);
            }
        }
        return true;
    }

    public static void registerRankCommand() {
        Medievo.instance.getCommand("rank").setExecutor(new rank(Medievo.instance));
    }
}

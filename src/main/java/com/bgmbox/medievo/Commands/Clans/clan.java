package com.bgmbox.medievo.Commands.Clans;

import com.bgmbox.medievo.Medievo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.UUID;

import static com.bgmbox.medievo.Database.Destroys.ClanDestroy.destroyClanInSQL;
import static com.bgmbox.medievo.Database.Entires.ClanEntry.clanExistsInSQLDatabase;
import static com.bgmbox.medievo.Database.Entires.ClanEntry.createClanInSQLDatabase;
import static com.bgmbox.medievo.util.Generic.*;

public class clan implements CommandExecutor {

    private final Medievo plugin;

    private clan(Medievo instance) {
        plugin = instance;
    }

    // /clan
    // /clan create {ClanName}
    // /clan invite {player}
    // /clan remove {player}
    // /clan disband {ClanName}
    // /clan setleader {player}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("clan")) {
            if (sender instanceof Player) {
                if (args.length == 2) {
                    Player player = (Player) sender;
                    UUID player_uuid = player.getUniqueId();
                    String player_name = player.getName();
                    String clanName = args[1];
                    switch (args[0]) {
                        case "create":
                            try {
                                if (!clanExistsInSQLDatabase(clanName)) {
                                    createClanInSQLDatabase(clanName, player_uuid, player_name);
                                    sender.sendMessage(ChatColor.AQUA + clanName + ChatColor.GREEN + " was successfully founded!");
                                    Bukkit.broadcastMessage(player.getDisplayName() + ChatColor.GREEN + " has founded the clan: " + ChatColor.AQUA + clanName);
                                } else {
                                    sender.sendMessage(WARNING_ICON + ChatColor.RED + "The clan " + ChatColor.AQUA + clanName + ChatColor.RED +" already exists!");
                                }
                            } catch (SQLException exception) {
                                exception.printStackTrace();
                            }
                            break;
                        case "disband":
                            destroyClanInSQL(clanName);
                            Bukkit.broadcastMessage(ChatColor.AQUA + clanName + ChatColor.RED + " has fallen! Disbanded by: " + player.getDisplayName());
                            sender.sendMessage(ChatColor.RED + "You have disbanded the " + ChatColor.AQUA + clanName + ChatColor.RED + " clan!");
                            break;
                        case "invite":

                        default:
                            sender.sendMessage(GENERIC_SYNTAX_ERROR);
                    }
                } else {
                    sender.sendMessage(GENERIC_SYNTAX_ERROR);
                }
            } else {
                sender.sendMessage(NO_CONSOLE);
            }
        }
        return true;
    }

    public static void registerClanCommand() {
        Medievo.instance.getCommand("clan").setExecutor(new clan(Medievo.instance));
    }
}

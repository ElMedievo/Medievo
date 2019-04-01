package com.bgmbox.medievo.Commands.Clans;

import com.bgmbox.medievo.Medievo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import static com.bgmbox.medievo.Commands.Clans.Methods.Create.foundClanAsPlayer;
import static com.bgmbox.medievo.Commands.Clans.Methods.Disband.destroyClanAsPlayer;
import static com.bgmbox.medievo.Commands.Clans.Methods.Help.displayClanHelpMenu;
import static com.bgmbox.medievo.Commands.Clans.Methods.Info.getClanInfo;
import static com.bgmbox.medievo.Commands.Clans.Methods.Invite.createPlayerToPlayerInvite;
import static com.bgmbox.medievo.util.Generic.*;

public class clan implements CommandExecutor {

    private final Medievo plugin;

    private clan(Medievo instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("clan")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                UUID player_uuid = player.getUniqueId();
                String player_name = player.getName();
                if (args.length == 2) {
                    String clanName = args[1];
                    String invited_name = args[1];
                    switch (args[0]) {
                        case "create":
                            foundClanAsPlayer(player, clanName);
                            break;
                        case "disband":
                            destroyClanAsPlayer(player, clanName);
                            break;
                        case "invite":
                            createPlayerToPlayerInvite(player_name, invited_name);
                            break;
                        default:
                            sender.sendMessage(GENERIC_SYNTAX_ERROR + CLANS_COMMAND_ERROR);
                    }
                } else if (args.length == 1) {
                    switch (args[0]) {
                        case "info":
                            getClanInfo(player);
                            break;
                        case "help":
                            displayClanHelpMenu(player);
                            break;
                        case "setleader":
                            sender.sendMessage(FEATURE_NOT_IMPLEMENTED);
                        default:
                            sender.sendMessage(GENERIC_SYNTAX_ERROR + CLANS_COMMAND_ERROR);
                            break;
                    }
                } else {
                    sender.sendMessage(GENERIC_SYNTAX_ERROR + CLANS_COMMAND_ERROR);
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

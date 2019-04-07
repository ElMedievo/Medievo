package org.elmedievo.medievo.Commands.Clans;

import org.elmedievo.medievo.Medievo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.elmedievo.medievo.Commands.Clans.Methods.*;

import java.util.UUID;

import static org.elmedievo.medievo.util.Generic.*;

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
                    String removed = args[1];
                    switch (args[0]) {
                        case "create":
                            Create.foundClanAsPlayer(player, clanName);
                            break;
                        case "disband":
                            Disband.destroyClanAsPlayer(player, clanName);
                            break;
                        case "invite":
                            Invite.createPlayerToPlayerInvite(player_name, invited_name);
                            break;
                        case "setleader":
                            sender.sendMessage(FEATURE_NOT_IMPLEMENTED);
                            break;
                        case "remove":
                            Remove.playerRemovePlayerFromClan(player.getName(), removed);
                            break;
                        default:
                            sender.sendMessage(GENERIC_SYNTAX_ERROR + CLANS_COMMAND_ERROR);
                    }
                } else if (args.length == 1) {
                    switch (args[0]) {
                        case "leave":
                            Leave.removePlayerFromClan(player);
                            break;
                        case "info":
                            Info.getClanInfo(player);
                            break;
                        case "help":
                            Help.displayClanHelpMenuToPlayer(player);
                            break;
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

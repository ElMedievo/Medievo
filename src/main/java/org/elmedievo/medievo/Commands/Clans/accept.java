package org.elmedievo.medievo.Commands.Clans;

import org.elmedievo.medievo.Medievo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.elmedievo.medievo.Database.Getters.PlayerClanGetter;
import org.elmedievo.medievo.Database.Setters.PlayerClanSetter;
import org.elmedievo.medievo.Queues.CreateQueues;
import org.elmedievo.medievo.Queues.Methods.ClanQueue;

import static org.elmedievo.medievo.util.Generic.NO_CONSOLE;
import static org.elmedievo.medievo.util.Generic.NO_PENDANT_INVITE;
import static org.elmedievo.medievo.util.Generic.TOO_MANY_ARGS;
import static org.elmedievo.medievo.util.Methods.PlayerIsOnline.playerIsOnline;

public class accept implements CommandExecutor {

    private final Medievo plugin;

    private accept(Medievo instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("accept")) {
            if (sender instanceof Player) {
                if (args.length == 0) {
                    Player player = (Player) sender;
                    String player_name = player.getName();
                    if (ClanQueue.isAlreadyBeingInvited(player_name)) {
                        String inviter = CreateQueues.inviteQueue.get(player_name);
                        if (playerIsOnline(inviter)) {
                            Player inviter_player = Bukkit.getPlayer(inviter);
                            String clan = PlayerClanGetter.getPlayerClan(inviter_player.getUniqueId());
                            PlayerClanSetter.setPlayerClan(player.getUniqueId(), clan);
                            player.sendMessage(ChatColor.GREEN + "You have joined " + ChatColor.AQUA + clan + ChatColor.GREEN + "!");
                            inviter_player.sendMessage(player.getDisplayName() + ChatColor.GREEN + " has joined the clan!");
                        }
                        ClanQueue.destroyPendantInvitation(player_name);
                    } else {
                        sender.sendMessage(NO_PENDANT_INVITE);
                    }
                } else {
                    sender.sendMessage(TOO_MANY_ARGS);
                }
            } else {
                sender.sendMessage(NO_CONSOLE);
            }
        }
        return true;
    }

    public static void registerAcceptCommand() {
        Medievo.instance.getCommand("accept").setExecutor(new accept(Medievo.instance));
    }
}

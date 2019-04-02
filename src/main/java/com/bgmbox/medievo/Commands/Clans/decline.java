package com.bgmbox.medievo.Commands.Clans;

import com.bgmbox.medievo.Medievo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.bgmbox.medievo.Queues.ClanQueueMethods.destroyPendantInvitation;
import static com.bgmbox.medievo.Queues.ClanQueueMethods.isAlreadyBeingInvited;
import static com.bgmbox.medievo.Queues.CreateQueues.inviteQueue;
import static com.bgmbox.medievo.util.Generic.NO_CONSOLE;
import static com.bgmbox.medievo.util.Generic.TOO_MANY_ARGS;
import static com.bgmbox.medievo.util.Methods.PlayerIsOnline.playerIsOnline;

public class decline implements CommandExecutor {

    private final Medievo plugin;

    private decline(Medievo instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("decline")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    if (isAlreadyBeingInvited(player.getName())) {
                        String inviter = inviteQueue.get(player.getName());
                        if (playerIsOnline(inviter, false)) {
                            Player inviter_player = Bukkit.getPlayer(inviter);
                            inviter_player.sendMessage(player.getDisplayName() + ChatColor.RED + " has declined your clan invitation!");
                        }
                        player.sendMessage(ChatColor.RED + "Invitation declined!");
                        destroyPendantInvitation(player.getName());
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

    public static void registerDeclineCommand() {
        Medievo.instance.getCommand("decline").setExecutor(new decline(Medievo.instance));
    }
}

package org.elmedievo.medievo.Commands.Clans;

import org.elmedievo.medievo.Medievo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.elmedievo.medievo.Queues.CreateQueues;
import org.elmedievo.medievo.Queues.Methods.ClanQueue;

import static org.elmedievo.medievo.util.Generic.NO_CONSOLE;
import static org.elmedievo.medievo.util.Generic.TOO_MANY_ARGS;
import static org.elmedievo.medievo.util.Methods.PlayerIsOnline.playerIsOnline;

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
                    if (ClanQueue.isAlreadyBeingInvited(player.getName())) {
                        String inviter = CreateQueues.inviteQueue.get(player.getName());
                        if (playerIsOnline(inviter)) {
                            Player inviter_player = Bukkit.getPlayer(inviter);
                            inviter_player.sendMessage(player.getDisplayName() + ChatColor.RED + " has declined your clan invitation!");
                        }
                        player.sendMessage(ChatColor.RED + "Invitation declined!");
                        ClanQueue.destroyPendantInvitation(player.getName());
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

package org.elmedievo.medievo.Commands.Clans.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.elmedievo.medievo.Queues.Methods.ClanQueue;

import java.util.Objects;

import static org.elmedievo.medievo.Database.Getters.PlayerClanGetter.getPlayerClan;
import static org.elmedievo.medievo.Queues.CreateQueues.inviteQueue;
import static org.elmedievo.medievo.Queues.Methods.ClanQueue.isAlreadyBeingInvited;
import static org.elmedievo.medievo.util.Generic.WARNING_ICON;
import static org.elmedievo.medievo.util.Methods.PlayerIsOnline.playerIsOnline;

public class Invite {
    public static void createPlayerToPlayerInvite(String inviter_name, String invited_name) {
        Player inviter_player = Bukkit.getPlayer(inviter_name);
        String inviters_clan = getPlayerClan(inviter_player.getUniqueId());
        if (playerIsOnline(invited_name)) {
            Player invited_player = Bukkit.getPlayer(invited_name);
            String inviteds_clan = getPlayerClan(invited_player.getUniqueId());
            if (Objects.requireNonNull(inviteds_clan).equals("none")) {
                if (!isAlreadyBeingInvited(invited_name)) {
                    inviteQueue.put(invited_name, inviter_name);
                    invited_player.sendMessage(
                            ChatColor.GREEN + "You have been invited to join " + ChatColor.AQUA + inviters_clan + ChatColor.GREEN + " by: " + inviter_player.getDisplayName() + ChatColor.GREEN + "!" + "\n"
                                    + ChatColor.GREEN + "Use " + ChatColor.AQUA + "/accept" + ChatColor.GREEN + " to join or " + ChatColor.AQUA + "/decline " + ChatColor.GREEN + " to dismiss this invitation"
                    );
                    inviter_player.sendMessage(ChatColor.GREEN + "Invitation successfully sent to " + invited_player.getDisplayName());
                } else {
                    inviter_player.sendMessage(WARNING_ICON + invited_player.getDisplayName() + ChatColor.RED + " is already invited to join a clan! Tell them to " + ChatColor.YELLOW + "/decline " + ChatColor.RED + "their invitation!");
                }
            } else {
                inviter_player.sendMessage(WARNING_ICON + invited_player.getDisplayName() + ChatColor.RED + " is already in a clan!");
            }
        } else {
            inviter_player.sendMessage(WARNING_ICON + ChatColor.DARK_AQUA + invited_name + ChatColor.RED + " is currently offline");
        }
    }
}

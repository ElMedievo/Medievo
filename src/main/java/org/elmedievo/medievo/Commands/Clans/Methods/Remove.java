package org.elmedievo.medievo.Commands.Clans.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.elmedievo.medievo.Database.Entires.PlayerEntry;
import org.elmedievo.medievo.Database.Getters.ClanLeaderGetter;
import org.elmedievo.medievo.Database.Getters.PlayerClanGetter;
import org.elmedievo.medievo.Database.Setters.PlayerClanSetter;
import org.elmedievo.medievo.Queues.CreateQueues;

import java.sql.SQLException;
import java.util.Objects;

import static org.elmedievo.medievo.util.Generic.WARNING_ICON;
import static org.elmedievo.medievo.util.Methods.PlayerIsOnline.playerIsOnline;

public class Remove {
    //clan remove player
    public static void playerRemovePlayerFromClan(String remover, String removed) {
        Player remover_player = Bukkit.getPlayer(remover);
        if (!remover.equals(removed)) {
            if (playerIsOnline(removed)) {
                Player removed_player = Bukkit.getPlayer(removed);
                if (inSameClan(remover_player, removed_player)) {
                    if (isClanLeader(remover_player)) {
                        String clan = PlayerClanGetter.getPlayerClan(removed_player.getUniqueId());
                        PlayerClanSetter.setPlayerClan(removed_player.getUniqueId(), "none");
                        removed_player.sendMessage(ChatColor.RED + "You have been removed from " + ChatColor.AQUA + clan + ChatColor.RED + "!");
                        remover_player.sendMessage(ChatColor.RED + "Removed " + removed_player.getDisplayName() + ChatColor.RED + " from clan!");
                        CreateQueues.chatQueue.put(removed, "global");
                    } else {
                        remover_player.sendMessage(WARNING_ICON + ChatColor.RED + "You cannot perform this action.");
                    }
                } else {
                    remover_player.sendMessage(WARNING_ICON + ChatColor.RED + "You are not in " + removed_player.getDisplayName() + ChatColor.RED + "'s clan");
                }
            } else {
                remover_player.sendMessage(WARNING_ICON + ChatColor.AQUA + removed + ChatColor.RED + "is currently offline or is invalid");
            }
        } else {
            remover_player.sendMessage(WARNING_ICON + ChatColor.RED + "You may not remove yourself from your own clan!");
        }
    }

    private static boolean inSameClan(Player player, Player player2) {
        try {
            if (PlayerEntry.playerExistsInDatabase(player.getUniqueId()) && PlayerEntry.playerExistsInDatabase(player2.getUniqueId())) {
                String playerClan = PlayerClanGetter.getPlayerClan(player.getUniqueId());
                String player2Clan = PlayerClanGetter.getPlayerClan(player2.getUniqueId());

                return Objects.requireNonNull(playerClan).equals(player2Clan);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    private static boolean isClanLeader(Player player) {
        try {
            if (PlayerEntry.playerExistsInDatabase(player.getUniqueId())) {
                String player_clan = PlayerClanGetter.getPlayerClan(player.getUniqueId());
                String player_clan_leader = ClanLeaderGetter.getClanLeaderName(player_clan);
                String player_name = player.getName();

                return Objects.requireNonNull(player_clan_leader).equals(player_name);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }
}

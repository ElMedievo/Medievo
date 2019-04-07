package org.elmedievo.medievo.Commands.Clans.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.elmedievo.medievo.Database.Destroys.ClanDestroy;
import org.elmedievo.medievo.Database.Entires.ClanEntry;
import org.elmedievo.medievo.Database.Getters.ClanLeaderGetter;
import org.elmedievo.medievo.Queues.CreateQueues;

import java.sql.SQLException;
import java.util.Objects;

import static org.elmedievo.medievo.util.Generic.WARNING_ICON;

public class Disband {
    public static void destroyClanAsPlayer(Player player, String clanName) {
        try {
            if (ClanEntry.clanExistsInSQLDatabase(clanName)) {
                String inputClanLeadersUUID = ClanLeaderGetter.getClanLeaderUUID(clanName);
                if (Objects.requireNonNull(inputClanLeadersUUID).equals(player.getUniqueId().toString())) {
                    ClanDestroy.destroyClanInSQL(clanName);
                    Bukkit.broadcastMessage(ChatColor.AQUA + clanName + ChatColor.RED + " has fallen! Disbanded by: " + player.getDisplayName());
                    player.sendMessage(ChatColor.RED + "You have disbanded " + ChatColor.AQUA + clanName + ChatColor.RED + "!");
                    CreateQueues.chatQueue.put(player.getName(), "global");
                } else {
                    player.sendMessage(WARNING_ICON + ChatColor.RED + "You are not the leader of " + ChatColor.AQUA + clanName);
                }
            } else {
                player.sendMessage(WARNING_ICON + ChatColor.AQUA + clanName + ChatColor.RED + " doesn't exist!");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}

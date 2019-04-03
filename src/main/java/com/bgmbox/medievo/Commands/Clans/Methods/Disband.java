package com.bgmbox.medievo.Commands.Clans.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Objects;

import static com.bgmbox.medievo.Database.Destroys.ClanDestroy.destroyClanInSQL;
import static com.bgmbox.medievo.Database.Entires.ClanEntry.clanExistsInSQLDatabase;
import static com.bgmbox.medievo.Database.Getters.ClanLeaderGetter.getClanLeaderUUID;
import static com.bgmbox.medievo.Database.Setters.PlayerClanSetter.setPlayerClan;
import static com.bgmbox.medievo.util.Generic.WARNING_ICON;

public class Disband {
    public static void destroyClanAsPlayer(Player player, String clanName) {
        try {
            if (clanExistsInSQLDatabase(clanName)) {
                String inputClanLeadersUUID = getClanLeaderUUID(clanName);
                if (Objects.requireNonNull(inputClanLeadersUUID).equals(player.getUniqueId().toString())) {
                    destroyClanInSQL(clanName);
                    Bukkit.broadcastMessage(ChatColor.AQUA + clanName + ChatColor.RED + " has fallen! Disbanded by: " + player.getDisplayName());
                    player.sendMessage(ChatColor.RED + "You have disbanded " + ChatColor.AQUA + clanName + ChatColor.RED + "!");
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

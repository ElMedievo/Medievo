package org.elmedievo.medievo.Commands.Clans.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.elmedievo.medievo.Database.Entires.ClanEntry;
import org.elmedievo.medievo.Database.Getters.PlayerClanGetter;
import org.elmedievo.medievo.Database.Setters.PlayerClanSetter;

import java.sql.SQLException;
import java.util.Objects;

import static org.elmedievo.medievo.util.Generic.CLAN_NAME_TOO_LONG;
import static org.elmedievo.medievo.util.Generic.WARNING_ICON;

public class Create {
    public static void foundClanAsPlayer(Player founder, String clanName) {
        if (clanName.toCharArray().length > 16) {
            founder.sendMessage(CLAN_NAME_TOO_LONG);
            return;
        }
        try {
            String playerActualClan = PlayerClanGetter.getPlayerClan(founder.getUniqueId());
            if (!ClanEntry.clanExistsInSQLDatabase(clanName)) {
                if (Objects.requireNonNull(playerActualClan).equals("none")) {
                    ClanEntry.createClanInSQLDatabase(clanName, founder.getUniqueId(), founder.getName());
                    PlayerClanSetter.setPlayerClan(founder.getUniqueId(), clanName);
                    founder.sendMessage(ChatColor.GREEN + "You have successfully founded the " + ChatColor.AQUA + clanName + ChatColor.GREEN + " clan!");
                    Bukkit.broadcastMessage(founder.getDisplayName() + ChatColor.GREEN + " has founded the clan: " + ChatColor.AQUA + clanName);
                } else {
                    founder.sendMessage(WARNING_ICON + ChatColor.RED + "You already are in clan!");
                }
            } else {
                founder.sendMessage(WARNING_ICON + ChatColor.AQUA + clanName + ChatColor.RED + " already exists!");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}

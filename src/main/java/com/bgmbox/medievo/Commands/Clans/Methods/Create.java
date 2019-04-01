package com.bgmbox.medievo.Commands.Clans.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Objects;

import static com.bgmbox.medievo.Database.Entires.ClanEntry.clanExistsInSQLDatabase;
import static com.bgmbox.medievo.Database.Entires.ClanEntry.createClanInSQLDatabase;
import static com.bgmbox.medievo.Database.Getters.PlayerClanGetter.getPlayerClan;
import static com.bgmbox.medievo.Database.Setters.PlayerClanSetter.setPlayerClan;
import static com.bgmbox.medievo.util.Generic.WARNING_ICON;

public class Create {
    public static void foundClanAsPlayer(Player founder, String clanName) {
        try {
            String playerActualClan = getPlayerClan(founder.getUniqueId());
            if (!clanExistsInSQLDatabase(clanName)) {
                if (Objects.requireNonNull(playerActualClan).equals("none")) {
                    createClanInSQLDatabase(clanName, founder.getUniqueId(), founder.getName());
                    setPlayerClan(founder.getUniqueId(), clanName);
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

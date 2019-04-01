package com.bgmbox.medievo.Commands.Clans.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;

import static com.bgmbox.medievo.Database.Getters.ClanLeaderGetter.getClanLeaderName;
import static com.bgmbox.medievo.Database.Getters.ClanLeaderGetter.getClanLeaderUUID;
import static com.bgmbox.medievo.Database.Getters.PlayerClanGetter.getPlayerClan;
import static com.bgmbox.medievo.Database.Setters.PlayerClanSetter.setPlayerClan;
import static com.bgmbox.medievo.util.Generic.WARNING_ICON;
import static com.bgmbox.medievo.util.Methods.PlayerIsOnline.playerIsOnline;

public class Leave {
    public static void removePlayerFromClan(Player player) {
        String player_clan = getPlayerClan(player.getUniqueId());
        if (!Objects.requireNonNull(player_clan).equals("none")) {
            if (!Objects.requireNonNull(getClanLeaderUUID(player_clan)).equals(player.getUniqueId().toString())) {
                String leader_name = getClanLeaderName(player_clan);
                setPlayerClan(player.getUniqueId(), "none");
                player.sendMessage(ChatColor.RED + "You have left " + ChatColor.AQUA + player_clan);
                if (playerIsOnline(leader_name, false)) {
                    Player leader = Bukkit.getPlayer(leader_name);
                    leader.sendMessage(player.getDisplayName() + ChatColor.RED + " has left " + ChatColor.AQUA + player_clan);
                }
            } else {
                player.sendMessage(
                        WARNING_ICON + ChatColor.RED + "You are the leader of " + ChatColor.AQUA + player_clan + ChatColor.RED + " therefore you may not leave it!" + "\n"
                        + ChatColor.RED + "You may disband your clan by using: " + ChatColor.YELLOW + "/clan disband " + player_clan
                );
            }
        } else {
            player.sendMessage(WARNING_ICON + ChatColor.RED + "You are not in a clan!");
        }
    }
}

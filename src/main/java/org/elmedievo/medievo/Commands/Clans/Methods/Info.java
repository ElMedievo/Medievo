package org.elmedievo.medievo.Commands.Clans.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

import static org.elmedievo.medievo.Commands.Clans.Mercantilism.Valuables.CURRENCY_SYMBOL;
import static org.elmedievo.medievo.Commands.Clans.Methods.displayMembersList.displayClanMemberList;
import static org.elmedievo.medievo.Commands.Clans.Methods.displayMembersList.getClanMembersList;
import static org.elmedievo.medievo.Database.Entires.ClanEntry.clanExistsInSQLDatabase;
import static org.elmedievo.medievo.Database.Getters.ClanAlfonsosGetter.getClanAlfonsos;
import static org.elmedievo.medievo.Database.Getters.ClanLeaderGetter.getClanLeaderName;
import static org.elmedievo.medievo.Database.Getters.PlayerClanGetter.getPlayerClan;
import static org.elmedievo.medievo.util.Generic.CLAN_NOT_FOUND;
import static org.elmedievo.medievo.util.Generic.WARNING_ICON;
import static org.elmedievo.medievo.util.Methods.PlayerIsOnline.playerIsOnline;

public class Info {
    @SuppressWarnings("deprecation")
    public static void getClanInfoSelf(Player player) {
        String players_clan = getPlayerClan(player.getUniqueId());
        if (!Objects.requireNonNull(players_clan).equals("none")) {
            String players_clan_leader = getClanLeaderName(players_clan);
            if (!playerIsOnline(players_clan_leader)) {
                player.sendMessage(ChatColor.RED + "########## " + ChatColor.AQUA + ChatColor.BOLD + "CLAN INFO" + ChatColor.RESET + ChatColor.RED + " ##########" + "\n"
                        + ChatColor.GRAY + "Your clan: " + ChatColor.GREEN + players_clan + "\n"
                        + ChatColor.GRAY + "Your leader: " + ChatColor.DARK_AQUA + players_clan_leader + "\n"
                        + ChatColor.GRAY + "Your clan's Balance: " + ChatColor.GOLD + CURRENCY_SYMBOL + getClanAlfonsos(players_clan) + "\n"
                        + ChatColor.GRAY + "Members: " + ChatColor.WHITE + "(" + ChatColor.AQUA + getClanMembersList(players_clan).size() + ChatColor.WHITE + ")" + "\n"
                        + displayClanMemberList(players_clan) + "\n"
                        + ChatColor.RED + "##############################"
                );
            } else {
                Player leader = Bukkit.getPlayer(players_clan_leader);
                player.sendMessage(ChatColor.RED + "######### " + ChatColor.AQUA + ChatColor.BOLD + "CLAN INFO" + ChatColor.RESET + ChatColor.RED + " ##########" + "\n"
                        + ChatColor.GRAY + "Your clan: " + ChatColor.GREEN + players_clan + "\n"
                        + ChatColor.GRAY + "Your leader: " + leader.getDisplayName() + "\n"
                        + ChatColor.GRAY + "Your clan's Balance: " + ChatColor.GOLD + CURRENCY_SYMBOL + getClanAlfonsos(players_clan) + "\n"
                        + ChatColor.GRAY + "Members: " + ChatColor.WHITE + "(" + ChatColor.AQUA + getClanMembersList(players_clan).size() + ChatColor.WHITE + ")" + "\n"
                        + displayClanMemberList(players_clan) + "\n"
                        + ChatColor.RED + "##############################"
                );
            }
        } else {
            player.sendMessage(WARNING_ICON + ChatColor.RED + "You are not in a clan!");
        }
    }

    @SuppressWarnings("deprecation")
    public static void getClanInfoOther(Player player, String clan) {
        try {
            if (clanExistsInSQLDatabase(clan)) {
                String clan_leader = getClanLeaderName(clan);
                if (!playerIsOnline(clan_leader)) {
                    UUID clan_leaderUUID = Bukkit.getOfflinePlayer(clan_leader).getUniqueId();
                    player.sendMessage(ChatColor.RED + "########## " + ChatColor.AQUA + ChatColor.BOLD + "CLAN INFO" + ChatColor.RESET + ChatColor.RED + " ##########" + "\n"
                            + ChatColor.GRAY + "Clan Name: " + ChatColor.GREEN + getPlayerClan(clan_leaderUUID) + "\n"
                            + ChatColor.GRAY + "Clan Leader: " + ChatColor.DARK_AQUA + clan_leader + "\n"
                            + ChatColor.GRAY + "Clan Balance: " + ChatColor.GOLD + CURRENCY_SYMBOL + getClanAlfonsos(clan) + "\n"
                            + ChatColor.GRAY + "Members: " + ChatColor.WHITE + "(" + ChatColor.AQUA + getClanMembersList(clan).size() + "\n"
                            + displayClanMemberList(clan) + "\n"
                            + ChatColor.RED + "##############################"
                    );
                } else {
                    Player leader = Bukkit.getPlayer(clan_leader);
                    player.sendMessage(ChatColor.RED + "######### " + ChatColor.AQUA + ChatColor.BOLD + "CLAN INFO" + ChatColor.RESET + ChatColor.RED + " ##########" + "\n"
                            + ChatColor.GRAY + "Clan Name: " + ChatColor.GREEN + clan + "\n"
                            + ChatColor.GRAY + "Clan Leader: " + leader.getDisplayName() + "\n"
                            + ChatColor.GRAY + "Clan Balance: " + ChatColor.GOLD + CURRENCY_SYMBOL + getClanAlfonsos(clan) + "\n"
                            + ChatColor.GRAY + "Members: " + ChatColor.WHITE + "(" + ChatColor.AQUA + getClanMembersList(clan).size() + ChatColor.WHITE + ")"  + "\n"
                            + displayClanMemberList(clan) + "\n"
                            + ChatColor.RED + "##############################"
                    );
                }
            } else {
                player.sendMessage(CLAN_NOT_FOUND);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}

package org.elmedievo.medievo.Commands.Clans.Methods;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

import static org.elmedievo.medievo.Commands.Clans.Mercantilism.Valuables.CURRENCY_SYMBOL;
import static org.elmedievo.medievo.Database.Getters.ClanAlfonsosGetter.getClanAlfonsos;
import static org.elmedievo.medievo.Database.Getters.ClanLeaderGetter.getClanLeaderName;
import static org.elmedievo.medievo.Database.Getters.ClansToListGetter.getRankedClansList;
import static org.elmedievo.medievo.util.Methods.PlayerIsOnline.evalOnlinePlayer;

public class displayClansList {
    public static void sendClansListToPlayer(Player player) {
        List<String> rankedClans = getRankedClansList();

        if (Objects.requireNonNull(rankedClans).size() == 0) {
            player.sendMessage(ChatColor.RED + "##########" + ChatColor.AQUA + ChatColor.BOLD + " CLANS " + ChatColor.RED + "##########" + "\n"
                    + "- " + ChatColor.GOLD + " THERE ARE NO CLANS :(" + "\n"
                    + (ChatColor.RED + "###########################")
            );
            return;
        }

        StringBuilder clansListMessage = new StringBuilder();

        clansListMessage.append(ChatColor.RED + "##########" + ChatColor.AQUA + ChatColor.BOLD + " CLANS " + ChatColor.RED + "##########" + "\n");
        Objects.requireNonNull(rankedClans).forEach(clan -> {
            if (!clan.equalsIgnoreCase("Medieval Bank")) {
                clansListMessage
                        .append(ChatColor.RED + "- " + ChatColor.AQUA + ChatColor.BOLD).append(clan)
                        .append(ChatColor.GRAY).append( " Leader: ").append(evalOnlinePlayer(getClanLeaderName(clan)))
                        .append(ChatColor.GRAY).append(" Balance: ").append(ChatColor.GOLD).append(CURRENCY_SYMBOL).append(getClanAlfonsos(clan)).append("\n");
            }
        });
        clansListMessage.append(ChatColor.RED + "###########################");

        String finalClansListMessage = clansListMessage.toString();
        player.sendMessage(finalClansListMessage);
    }
}

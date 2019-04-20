package org.elmedievo.medievo.Commands.Clans.Methods;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.Objects;

import static org.elmedievo.medievo.Database.Getters.ClanMembersToList.getClanMembers;

public class displayMembersList {
    public static String displayClanMemberList(String clan) {
        List<String> members = getClanMembers(clan);

        StringBuilder memberListMessage = new StringBuilder();
        Objects.requireNonNull(members).forEach(member -> memberListMessage.append(ChatColor.GRAY + "- " + ChatColor.YELLOW).append(member).append("\n"));

        return memberListMessage.toString();
    }
}

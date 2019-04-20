package org.elmedievo.medievo.Database.Getters;

import org.elmedievo.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClanMembersToList {

    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static List<String> getClanMembers(String clan) {
        try {
            return lookupClanMembers(clan);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private static List<String> lookupClanMembers(String clan) throws SQLException {
        PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT name FROM " + plugin.player_data_table + " WHERE clan=?");
        statement.setString(1, clan);
        ResultSet results = statement.executeQuery();
        List<String> members = new ArrayList<>();

        while (results.next()) {
            members.add(results.getString("name"));
        }

        return members;
    }
}

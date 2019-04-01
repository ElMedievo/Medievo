package com.bgmbox.medievo.Database.Getters;

import com.bgmbox.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClanLeaderGetter {

    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static String getClanLeaderUUID(String clanName) {
        try {
            return lookupClanLeaderUUID(clanName);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private static String lookupClanLeaderUUID(String clanName) throws SQLException {
        PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.clans_data_table + " WHERE name=?");
        statement.setString(1, clanName);
        ResultSet results = statement.executeQuery();
        results.next();
        return results.getString("leader_uuid");
    }
}

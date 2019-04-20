package org.elmedievo.medievo.Database.Setters;

import org.elmedievo.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddColonesToClan {

    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static void addColonestoClan(String clan, int colones) {
        try {
            lookUpClanColones(clan, colones);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void lookUpClanColones(String clan, int colones) throws SQLException {
        PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT colones FROM " + plugin.clans_economy_data_table + " WHERE clan=?");
        statement.setString(1, clan);
        ResultSet results = statement.executeQuery();
        results.next();
        int actualColones = results.getInt("colones");

        PreparedStatement insert = plugin.getConnection().prepareStatement("UPDATE " + plugin.clans_economy_data_table + " SET colones=? WHERE clan=?");
        insert.setInt(1, actualColones + colones);
        insert.setString(2, clan);
        insert.executeUpdate();
    }
}

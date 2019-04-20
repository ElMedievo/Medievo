package org.elmedievo.medievo.Database.Setters;

import org.elmedievo.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddAlfonsosToClan {

    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static void addAlfonsosToClan(String clan, int alfonsosIn) {
        try {
            lookUpClanAlfonsos(clan, alfonsosIn);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void lookUpClanAlfonsos(String clan, int alfonsosIn) throws SQLException {
        PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT alfonsos FROM " + plugin.clans_economy_data_table + " WHERE clan=?");
        statement.setString(1, clan);
        ResultSet results = statement.executeQuery();
        results.next();
        int actualAlfonsos = results.getInt("alfonsos");

        PreparedStatement insert = plugin.getConnection().prepareStatement("UPDATE " + plugin.clans_economy_data_table + " SET alfonsos=? WHERE clan=?");
        insert.setInt(1, actualAlfonsos + alfonsosIn);
        insert.setString(2, clan);
        insert.executeUpdate();
    }
}

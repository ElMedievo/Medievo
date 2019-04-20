package org.elmedievo.medievo.Database.Setters;

import org.elmedievo.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddGoldToClan {

    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static void addGoldToClan(String clan, String material, Integer amount) {
        try {
            lookUpClanGold(clan, material, amount);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void lookUpClanGold(String clan, String material, Integer amount) throws SQLException {
        PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.clans_economy_data_table+ " WHERE clan=?");
        statement.setString(1, clan);
        ResultSet results = statement.executeQuery();
        results.next();
        int actualMaterialAmount = results.getInt(material);

        PreparedStatement insert = plugin.getConnection().prepareStatement("UPDATE " + plugin.clans_economy_data_table + " SET " + material + "=?" + " WHERE clan=?");
        insert.setInt(1, actualMaterialAmount + amount);
        insert.setString(2, clan);
        insert.executeUpdate();
    }
}

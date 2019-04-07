package org.elmedievo.medievo.Database.Entires;

import org.elmedievo.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ClanEntry {

    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static boolean clanExistsInSQLDatabase(String name) throws SQLException {
        PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.clans_data_table + " WHERE name=?");
        statement.setString(1, name);
        ResultSet results = statement.executeQuery();
        return results.next();
    }

    public static void createClanInSQLDatabase(String name, UUID leader_uuid, String leader_name) {
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.clans_data_table + " WHERE name=?");
            statement.setString(1, name);
            ResultSet results = statement.executeQuery();
            results.next();
            if (!clanExistsInSQLDatabase(name)) {
                PreparedStatement insert = plugin.getConnection().prepareStatement("INSERT INTO " + plugin.clans_data_table + " (name,leader_uuid,leader_name) VALUES (?,?,?)");
                insert.setString(1, name);
                insert.setString(2, leader_uuid.toString());
                insert.setString(3, leader_name);
                insert.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}

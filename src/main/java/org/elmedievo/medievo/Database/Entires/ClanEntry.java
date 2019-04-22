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
                addClanToEconomyTable(name);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void addClanToEconomyTable(String clan) throws SQLException {
        PreparedStatement insert = plugin.getConnection().prepareStatement(
                "INSERT INTO " + plugin.clans_economy_data_table +
                        " (clan,gold_ingot,gold_nugget,golden_carrot,gold_barding,gold_block,gold_sword,gold_spade,gold_pickaxe,gold_axe,gold_hoe,gold_helmet,gold_chestplate,gold_leggings,gold_boots,golden_apple,alfonsos) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
        );
        insert.setString(1, clan);
        insert.setInt(2, 0);
        insert.setInt(3, 0);
        insert.setInt(4, 0);
        insert.setInt(5, 0);
        insert.setInt(6, 0);
        insert.setInt(7, 0);
        insert.setInt(8, 0);
        insert.setInt(9, 0);
        insert.setInt(10, 0);
        insert.setInt(11, 0);
        insert.setInt(12, 0);
        insert.setInt(13, 0);
        insert.setInt(14, 0);
        insert.setInt(15, 0);
        insert.setInt(16, 0);
        insert.setInt(17, 0);
        insert.executeUpdate();
    }
}

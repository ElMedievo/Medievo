package com.bgmbox.medievo.Database.Entires;

import com.bgmbox.medievo.Medievo;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerEntry {

     private static Medievo plugin = Medievo.getPlugin(Medievo.class);

     public static boolean playerExistsInDatabase(@NotNull UUID uuid) throws SQLException {
         PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.player_data_table + " WHERE uuid=?");
         statement.setString(1, uuid.toString());
         ResultSet results = statement.executeQuery();
         return results.next();
     }
    public static void registerPlayerInDatabase(@NotNull final UUID uuid, String name, int gold) {
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.player_data_table + " WHERE uuid=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            if (!playerExistsInDatabase(uuid)) {
                PreparedStatement insert = plugin.getConnection().prepareStatement("INSERT INTO " + plugin.player_data_table + " (uuid,name,gold) VALUES (?,?,?)");
                insert.setString(1, uuid.toString());
                insert.setString(2, name);
                insert.setInt(3, gold);
                insert.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}

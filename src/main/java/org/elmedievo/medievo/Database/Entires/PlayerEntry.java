package org.elmedievo.medievo.Database.Entires;

import org.elmedievo.medievo.Medievo;
import org.elmedievo.medievo.Ranks.CreateRanksData;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerEntry {

     private static Medievo plugin = Medievo.getPlugin(Medievo.class);

     public static boolean playerExistsInDatabase(@NotNull UUID uuid) throws SQLException {
         PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.player_data_table + " WHERE uuid=?");
         statement.setString(1, uuid.toString());
         ResultSet results = statement.executeQuery();
         return results.next();
     }

     public static boolean playerNameExistsInSQLDatabase(String name) {
         try {
             if (lookForPlayerNameInSQLDatabase(name)) {
                 return true;
             }
         } catch (SQLException exception) {
             exception.printStackTrace();
         }
         return false;
     }

     private static boolean lookForPlayerNameInSQLDatabase(String name) throws SQLException {
         PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.player_data_table + " WHERE name=?");
         statement.setString(1, name);
         ResultSet results = statement.executeQuery();
         return results.next();
     }

    public static void registerPlayerInSQLDatabase(@NotNull final UUID uuid, String name, int gold, String defaultClan) {
        try {
            PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.player_data_table + " WHERE uuid=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            if (!playerExistsInDatabase(uuid)) {
                PreparedStatement insert = plugin.getConnection().prepareStatement("INSERT INTO " + plugin.player_data_table + " (uuid,name,gold,clan,kills,killed,deaths) VALUES (?,?,?,?,?,?,?)");
                insert.setString(1, uuid.toString());
                insert.setString(2, name);
                insert.setInt(3, gold);
                insert.setString(4, defaultClan);
                insert.setInt(5, 0);
                insert.setInt(6, 0);
                insert.setInt(7, 0);
                insert.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void registerPlayerInRanksDatabase(@NotNull final UUID uuid, String name) {
         if (!CreateRanksData.getRanksData().isConfigurationSection("players." + uuid)) {
             List<String> ranksList = new ArrayList<>();
             ranksList.add("Default");
             CreateRanksData.getRanksData().set("players." + uuid.toString() + ".ranks", ranksList);
         }
        CreateRanksData.getRanksData().set("players." + uuid.toString() + ".name", name);
        CreateRanksData.saveRanksData();
    }
}

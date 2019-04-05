package com.bgmbox.medievo.Database.Entires;

import com.bgmbox.medievo.Medievo;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.bgmbox.medievo.Ranks.CreateRanksData.getRanksData;
import static com.bgmbox.medievo.Ranks.CreateRanksData.saveRanksData;

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
                PreparedStatement insert = plugin.getConnection().prepareStatement("INSERT INTO " + plugin.player_data_table + " (uuid,name,gold,clan) VALUES (?,?,?,?)");
                insert.setString(1, uuid.toString());
                insert.setString(2, name);
                insert.setInt(3, gold);
                insert.setString(4, defaultClan);
                insert.executeUpdate();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void registerPlayerInRanksDatabase(@NotNull final UUID uuid, String name) {
         if (!getRanksData().isConfigurationSection("players." + uuid)) {
             List<String> ranksList = new ArrayList<>();
             ranksList.add("Default");
             getRanksData().set("players." + uuid.toString() + ".ranks", ranksList);
         }
        getRanksData().set("players." + uuid.toString() + ".name", name);
        saveRanksData();
    }
}

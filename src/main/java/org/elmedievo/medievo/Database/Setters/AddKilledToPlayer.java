package org.elmedievo.medievo.Database.Setters;

import org.elmedievo.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import static org.elmedievo.medievo.Database.Getters.PlayerKilledGetter.getPlayerKilled;

public class AddKilledToPlayer {

    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static void addKilledToPlayer(UUID uuid) {
        try {
            lookUpPlayerKilled(uuid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void lookUpPlayerKilled(UUID uuid) throws SQLException {
        int actualKilled = getPlayerKilled(uuid);
        PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.player_data_table + " SET killed=? WHERE uuid=?");
        statement.setInt(1, actualKilled + 1);
        statement.setString(2, uuid.toString());

        statement.executeUpdate();
    }
}

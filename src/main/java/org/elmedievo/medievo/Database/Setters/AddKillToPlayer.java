package org.elmedievo.medievo.Database.Setters;

import org.elmedievo.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import static org.elmedievo.medievo.Database.Getters.PlayerKillsGetter.getPlayerKills;

public class AddKillToPlayer {

    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static void addKillToPlayer(UUID uuid) {
        try {
            lookUpPlayerKills(uuid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void lookUpPlayerKills(UUID uuid) throws SQLException {
        int actualKills = getPlayerKills(uuid);
        PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.player_data_table + " SET kills=? WHERE uuid=?");
        statement.setInt(1, actualKills + 1);
        statement.setString(2, uuid.toString());
        statement.executeUpdate();
    }
}

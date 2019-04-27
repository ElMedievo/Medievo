package org.elmedievo.medievo.Database.Setters;

import org.elmedievo.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import static org.elmedievo.medievo.Database.Getters.PlayerDeathsGetter.getPlayerDeaths;

public class AddDeathToPlayer {

    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static void addDeathToPlayer(UUID uuid) {
        try {
            lookUpPlayerDeaths(uuid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void lookUpPlayerDeaths(UUID uuid) throws SQLException {
        int actualDeaths = getPlayerDeaths(uuid);
        PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.player_data_table + " SET deaths=? WHERE uuid=?");
        statement.setInt(1, actualDeaths + 1);
        statement.setString(2, uuid.toString());
        statement.executeUpdate();
    }
}

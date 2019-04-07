package org.elmedievo.medievo.Database.Setters;

import org.elmedievo.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerClanSetter {

    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static void setPlayerClan(UUID uuid, String clanName) {
        try {
            lookUpPlayerClan(uuid, clanName);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void lookUpPlayerClan(UUID uuid, String clanName) throws SQLException {
        PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.player_data_table + " SET clan=? WHERE uuid=?");
        statement.setString(1, clanName);
        statement.setString(2, uuid.toString());
        statement.executeUpdate();
    }
}

package org.elmedievo.medievo.Database.Setters;

import org.elmedievo.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerNameSetter {

    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static void updatePlayerNameInSQL(UUID uuid, String name) {
        try {
            lookupPlayerName(uuid, name);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void lookupPlayerName(UUID uuid, String name) throws SQLException {
        PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.player_data_table + " SET name=? WHERE uuid=?");
        statement.setString(1, name);
        statement.setString(2, uuid.toString());
        statement.executeUpdate();

        PreparedStatement statement1 = plugin.getConnection().prepareStatement("UPDATE " + plugin.clans_data_table + " SET leader_name=? WHERE leader_uuid=?");
        statement1.setString(1, name);
        statement1.setString(2, uuid.toString());
        statement1.executeUpdate();
    }
}

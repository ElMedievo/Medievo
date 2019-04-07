package org.elmedievo.medievo.Database.Getters;

import org.elmedievo.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerClanGetter {

    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static String getPlayerClan(UUID uuid) {
        try {
            return lookupPlayerClan(uuid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private static String lookupPlayerClan(UUID uuid) throws SQLException {
        PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.player_data_table + " WHERE uuid=?");
        statement.setString(1, uuid.toString());
        ResultSet results = statement.executeQuery();
        results.next();
        return results.getString("clan");
    }
}

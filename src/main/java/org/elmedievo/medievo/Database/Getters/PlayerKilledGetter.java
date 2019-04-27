package org.elmedievo.medievo.Database.Getters;

import org.elmedievo.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerKilledGetter {
    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static int getPlayerKilled(UUID uuid) {
        try {
            return lookUpPlayerKilled(uuid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    private static int lookUpPlayerKilled(UUID uuid) throws SQLException {
        PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.player_data_table + " WHERE uuid=?");
        statement.setString(1, uuid.toString());
        ResultSet results = statement.executeQuery();
        results.next();
        return results.getInt("killed");
    }
}

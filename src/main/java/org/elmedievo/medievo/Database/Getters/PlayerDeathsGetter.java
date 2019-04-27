package org.elmedievo.medievo.Database.Getters;

import org.elmedievo.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerDeathsGetter {

    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static int getPlayerDeaths(UUID uuid) {
        try {
            return lookUpPlayerDeaths(uuid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    private static int lookUpPlayerDeaths(UUID uuid) throws SQLException {
        PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.player_data_table + " WHERE uuid=?");
        statement.setString(1, uuid.toString());
        ResultSet results = statement.executeQuery();
        results.next();
        return results.getInt("deaths");
    }
}

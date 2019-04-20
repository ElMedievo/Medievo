package org.elmedievo.medievo.Database.Getters;

import org.elmedievo.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClanAlfonsosGetter {

    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static int getClanAlfonsos(String clan) {
        try {
            return lookUpClanAlfonsos(clan);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    private static int lookUpClanAlfonsos(String clan) throws SQLException {
        PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT alfonsos FROM " + plugin.clans_economy_data_table + " WHERE clan=?");
        statement.setString(1, clan);
        ResultSet results = statement.executeQuery();
        results.next();
        return results.getInt("alfonsos");
    }
}

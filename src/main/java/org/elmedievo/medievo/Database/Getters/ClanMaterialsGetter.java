package org.elmedievo.medievo.Database.Getters;

import org.elmedievo.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClanMaterialsGetter {

    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static int getClanMaterialAmount(String clan, String material) {
        try {
            return lookupMaterialsAmountinClan(clan, material);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    private static int lookupMaterialsAmountinClan(String clan, String material) throws SQLException {
        PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT " + material + " FROM " + plugin.clans_economy_data_table + " WHERE clan=?");
        statement.setString(1, clan);
        ResultSet results = statement.executeQuery();
        results.next();
        return results.getInt(material);
    }
}

package org.elmedievo.medievo.Database.Getters;

import org.elmedievo.medievo.Medievo;

import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class ClanAlfonsosGetter {

    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static float getClanAlfonsos(String clan) {
        try {
            return lookUpClanAlfonsos(clan);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    private static float lookUpClanAlfonsos(String clan) throws SQLException {
        PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT alfonsos FROM " + plugin.clans_economy_data_table + " WHERE clan=?");
        statement.setString(1, clan);
        ResultSet results = statement.executeQuery();
        results.next();

        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        decimalFormat.setRoundingMode(RoundingMode.UP);

        float alfonsos = results.getFloat("alfonsos");

        return Float.parseFloat(decimalFormat.format((alfonsos)));
    }
}

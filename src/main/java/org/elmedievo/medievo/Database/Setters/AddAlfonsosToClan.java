package org.elmedievo.medievo.Database.Setters;

import org.elmedievo.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.elmedievo.medievo.Database.Getters.ClanAlfonsosGetter.getClanAlfonsos;

public class AddAlfonsosToClan {

    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static void addAlfonsosToClan(String clan, int alfonsosIn) {
        try {
            lookUpClanAlfonsos(clan, alfonsosIn);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void lookUpClanAlfonsos(String clan, int alfonsosIn) throws SQLException {
        int actualAlfonsos = getClanAlfonsos(clan);

        PreparedStatement insert = plugin.getConnection().prepareStatement("UPDATE " + plugin.clans_economy_data_table + " SET alfonsos=? WHERE clan=?");
        insert.setInt(1, actualAlfonsos + alfonsosIn);
        insert.setString(2, clan);
        insert.executeUpdate();
    }
}

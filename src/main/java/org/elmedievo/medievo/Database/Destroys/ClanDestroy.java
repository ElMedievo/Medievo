package org.elmedievo.medievo.Database.Destroys;

import org.elmedievo.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClanDestroy {

    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static void destroyClanInSQL(String name) {
        try {
            deleteClanInSQL(name);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static void deleteClanInSQL(String name) throws SQLException {
        PreparedStatement destroy_in_players = plugin.getConnection().prepareStatement("UPDATE " + plugin.player_data_table + " SET clan=? WHERE clan=?");
        destroy_in_players.setString(1, "none");
        destroy_in_players.setString(2, name);
        destroy_in_players.executeUpdate();

        PreparedStatement destroy_in_clans = plugin.getConnection().prepareStatement("DELETE FROM " + plugin.clans_data_table + " WHERE name=?");
        destroy_in_clans.setString(1, name);
        destroy_in_clans.executeUpdate();

        PreparedStatement destroy_in_economy = plugin.getConnection().prepareStatement("DELETE FROM " + plugin.clans_economy_data_table + " WHERE clan=?");
        destroy_in_economy.setString(1, name);
        destroy_in_economy.executeUpdate();
    }
}

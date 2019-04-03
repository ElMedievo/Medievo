package com.bgmbox.medievo.Database.Destroys;

import com.bgmbox.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    }
}

package com.bgmbox.medievo.Database.Destroys;

import com.bgmbox.medievo.Medievo;

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
        PreparedStatement destroy = plugin.getConnection().prepareStatement("DELETE FROM " + plugin.clans_data_table + " WHERE name=?");
        destroy.setString(1, name);
        destroy.executeUpdate();
    }
}

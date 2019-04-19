package org.elmedievo.medievo.Database.Getters;

import org.elmedievo.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClansToListGetter {

    private static Medievo plugin = Medievo.getPlugin(Medievo.class);

    public static List<String> getClansList() {
        try {
            return lookupEveryClan();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private static List<String> lookupEveryClan() throws SQLException {
        PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT name FROM " + plugin.clans_data_table);
        ResultSet results = statement.executeQuery();
        List<String> clans = new ArrayList<>();

        while (results.next()) {
            clans.add(results.getString("name"));
        }

        return clans;
    }
}

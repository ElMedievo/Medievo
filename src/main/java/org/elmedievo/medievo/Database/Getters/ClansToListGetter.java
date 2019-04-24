package org.elmedievo.medievo.Database.Getters;

import org.elmedievo.medievo.Medievo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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

    public static List<String> getRankedClansList() {
        try {
            return lookUpRankedClans();
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

    private static List<String> lookUpRankedClans() throws SQLException {
        List<String> orderedClans = new ArrayList<>();
        List<Integer> alfonsos = new ArrayList<>();
        PreparedStatement alfonsos_statement = plugin.getConnection().prepareStatement("SELECT alfonsos FROM " + plugin.clans_economy_data_table);
        ResultSet alfonsos_results = alfonsos_statement.executeQuery();

        while (alfonsos_results.next()) {
            alfonsos.add(alfonsos_results.getInt("alfonsos"));
        }
        alfonsos.sort(Collections.reverseOrder());

        alfonsos.forEach(alfonso -> {
            try {
                PreparedStatement clans_statement = plugin.getConnection().prepareStatement("SELECT DISTINCT clan FROM " + plugin.clans_economy_data_table + " WHERE alfonsos=?");
                clans_statement.setInt(1, alfonso);
                ResultSet clans_results = clans_statement.executeQuery();
                while (clans_results.next()) {
                    String clan = clans_results.getString("clan");
                    if (!orderedClans.contains(clan)) {
                        orderedClans.add(clan);
                    } else {
                        return;
                    }
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        });

        return orderedClans;
    }
}

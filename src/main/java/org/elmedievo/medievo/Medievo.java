package org.elmedievo.medievo;

import org.elmedievo.medievo.Ranks.CreateRanksData;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.elmedievo.medievo.Configuration.LoadConfig;
import org.elmedievo.medievo.Queues.CreateQueues;
import org.elmedievo.medievo.Ranks.CreateRanksFile;
import org.elmedievo.medievo.util.CommandRegistry;
import org.elmedievo.medievo.util.EventRegistry;
import org.elmedievo.medievo.util.Generic;
import org.elmedievo.medievo.util.Methods.ConsoleAlerts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.elmedievo.medievo.Commands.TabComplete.Resources.LoadResources.loadTabCompleteArguments;

public final class Medievo extends JavaPlugin {

    public static Medievo instance;
    private CreateRanksData configurationManager;

    public void loadConfigurationManager() {
        configurationManager = new CreateRanksData();
        configurationManager.createRanksDataYML();
    }

    @Override
    public void onEnable() {
        instance = this;
        ConsoleAlerts.sendConsoleAlert(ChatColor.GREEN + "-----" + " Medievo " + "-----");

        LoadConfig.loadConfig();
        CommandRegistry.registerCommands();
        loadTabCompleteArguments();
        EventRegistry.registerEvents();

        CreateRanksFile.createRanksXMLFile();
        loadConfigurationManager();
        connectSQLDatabase();

        CreateQueues.createQueues();

        ConsoleAlerts.sendConsoleAlert(ChatColor.GREEN + "-------------------");
    }

    @Override
    public void onDisable() {
        ConsoleAlerts.logInfo("The Medieval plugin has been disabled");
    }

    //---------- SQL Connection Logic ----------//
    private  Connection connection;
    private  String host;
    private String database;
    private String username;
    private String password;
    public String player_data_table;
    public String clans_data_table;
    public String clans_economy_data_table;
    private int port;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private void connectSQLDatabase() {
        try {
            stablishSQLConnection();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
            ConsoleAlerts.sendConsoleAlert(Generic.SQL_CANNOT_CONNECT);
        }
    }

    private void stablishSQLConnection() throws SQLException, ClassNotFoundException {
        host = getConfig().getString("sql.host");
        port = Integer.parseInt(getConfig().getString("sql.port"));
        database = getConfig().getString("sql.database");
        username = getConfig().getString("sql.username");
        password = getConfig().getString("sql.password");
        player_data_table = getConfig().getString("sql.player_data_table");
        clans_data_table = getConfig().getString("sql.clans_data_table");
        clans_economy_data_table = getConfig().getString("sql.clans_economy_data_table");

        synchronized (this) {
            if (getConnection() != null && !getConnection().isClosed()) {
                return;
            }

            Class.forName("com.mysql.jdbc.Driver");
            String URL = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true&useSSL=false";
            setConnection(DriverManager.getConnection(URL, this.username, this.password));
            ConsoleAlerts.sendConsoleAlert(Generic.SQL_CONNECT_SUCCESS);
        }
    }
}

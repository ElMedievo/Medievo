package org.elmedievo.medievo;

import org.elmedievo.medievo.Ranks.CreateRanksData;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.elmedievo.medievo.Queues.CreateQueues;
import org.elmedievo.medievo.util.Generic;
import org.elmedievo.medievo.util.Methods.ConsoleAlerts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.elmedievo.medievo.Commands.Market.Methods.CreateMarketData.createMarketDataYML;
import static org.elmedievo.medievo.Commands.TabComplete.Resources.LoadResources.loadTabCompleteArguments;
import static org.elmedievo.medievo.Configuration.LoadConfig.loadConfig;
import static org.elmedievo.medievo.EventHandlers.BlockBreak.loadSmelterMaterials;
import static org.elmedievo.medievo.Queues.CreateQueues.createQueues;
import static org.elmedievo.medievo.Ranks.CreateRanksData.createRanksDataYML;
import static org.elmedievo.medievo.Ranks.CreateRanksFile.createRanksXMLFile;
import static org.elmedievo.medievo.util.CentralBank.generateCentralBank;
import static org.elmedievo.medievo.util.CommandRegistry.registerCommands;
import static org.elmedievo.medievo.util.EventRegistry.registerEvents;

public final class Medievo extends JavaPlugin {

    public static Medievo instance;
    private CreateRanksData configurationManager;

    private void loadConfigurationManager() {
        createRanksDataYML();
        createMarketDataYML();
    }

    @Override
    public void onEnable() {
        instance = this;
        ConsoleAlerts.sendConsoleAlert(ChatColor.GREEN + "-----" + " Medievo " + "-----");
        loadConfig();
        registerCommands();
        loadTabCompleteArguments();
        registerEvents();
        createRanksXMLFile();
        loadConfigurationManager();
        connectSQLDatabase();
        createQueues();
        loadSmelterMaterials();
        generateCentralBank();
        ConsoleAlerts.sendConsoleAlert(ChatColor.GREEN + "-------------------");
    }

    @Override
    public void onDisable() {
        ConsoleAlerts.logInfo("The Medieval plugin has been disabled");
    }

    //---------- SQL Connection Logic ----------//
    private Connection connection;
    private String host;
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

    private void setConnection(Connection connection) {
        this.connection = connection;
    }

    private void connectSQLDatabase() {
        try {
            establishSQLConnection();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
            ConsoleAlerts.sendConsoleAlert(Generic.SQL_CANNOT_CONNECT);
        }
    }

    private void establishSQLConnection() throws SQLException, ClassNotFoundException {
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

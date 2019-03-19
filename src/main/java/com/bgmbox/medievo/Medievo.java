package com.bgmbox.medievo;

import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.bgmbox.medievo.Configuration.LoadConfig.loadConfig;
import static com.bgmbox.medievo.Ranks.CreateRanksFile.createRanksXMLFile;
import static com.bgmbox.medievo.util.CommandRegistry.registerCommands;
import static com.bgmbox.medievo.util.EventRegistry.registerEvents;
import static com.bgmbox.medievo.util.Generic.SQL_CANNOT_CONNECT;
import static com.bgmbox.medievo.util.Generic.SQL_CONNECT_SUCCESS;
import static com.bgmbox.medievo.util.Methods.ConsoleAlerts.logInfo;
import static com.bgmbox.medievo.util.Methods.ConsoleAlerts.sendConsoleAlert;

public final class Medievo extends JavaPlugin {

    public static Medievo instance;

    @Override
    public void onEnable() {
        instance = this;
        loadConfig();
        registerCommands();
        registerEvents();

        createRanksXMLFile();
        connectSQLDatabase();
        logInfo("The Medieval plugin has been enabled");
    }

    @Override
    public void onDisable() {
        logInfo("The Medieval plugin has been disabled");
    }

    //---------- SQL Connection Logic ----------//
    private  Connection connection;
    private  String host, database, username, password;
    private  int port;

    private Connection getConnection() {
        return connection;
    }

    private void setConnection(Connection connection) {
        this.connection = connection;
    }

    private void connectSQLDatabase() {
        try {
            stablishSQLConnection();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
            sendConsoleAlert(SQL_CANNOT_CONNECT);
        }
    }

    private void stablishSQLConnection() throws SQLException, ClassNotFoundException {
        host = getConfig().getString("sql.host");
        port = Integer.parseInt(getConfig().getString("sql.port"));
        database = getConfig().getString("sql.database");
        username = getConfig().getString("sql.username");
        password = getConfig().getString("sql.password");

        synchronized (this) {
            if (getConnection() != null && !getConnection().isClosed()) {
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password));
            sendConsoleAlert(SQL_CONNECT_SUCCESS);
        }
    }
}

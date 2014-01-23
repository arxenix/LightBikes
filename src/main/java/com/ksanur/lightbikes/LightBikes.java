package com.ksanur.lightbikes;

import lib.PatPeter.SQLibrary.Database;
import lib.PatPeter.SQLibrary.Mongo;
import lib.PatPeter.SQLibrary.MySQL;
import lib.PatPeter.SQLibrary.SQLite;
import mondocommand.MondoCommand;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * User: bobacadodl
 * Date: 1/20/14
 * Time: 3:26 PM
 */
public class LightBikes extends JavaPlugin {
    private static LightBikes instance;
    private static String NAME;

    private Database sql;

    public void onEnable() {
        instance = this;

        MondoCommand base = new MondoCommand();
        base.autoRegisterFrom(this);
        getCommand("lightbikes").setExecutor(base);

        //add custom entities
        for (BikeType bikeType : BikeType.values()) {
            BikeNMS.addCustomEntity(bikeType.getBikeClass(), bikeType.getName(), bikeType.getId());
        }

        loadConfig();
    }

    public void loadConfig() {
        saveDefaultConfig();

        //LOAD SQL DATABASE

        DatabaseType databaseType = DatabaseType.SQLite;
        String prefix = "[" + NAME + "] ";
        String hostname = "localhost";
        int port = 3306;
        String database = NAME;
        String username = "username";
        String password = "password";

        String directory = this.getDataFolder().getAbsolutePath();

        if (getConfig().isString("database.type")) {
            String databaseTypeString = getConfig().getString("database");
            if (DatabaseType.isDatabaseType(databaseTypeString)) {
                databaseType = DatabaseType.getDatabaseType(databaseTypeString);
            } else {
                getLogger().severe("Database \"" + databaseTypeString + "\" is not a valid database type!");
                getLogger().severe("Defaulting to SQLite instead...");
            }
        }

        if (getConfig().isString("database.hostname")) {
            hostname = getConfig().getString("database.hostname");
        }
        if (getConfig().isInt("database.port")) {
            port = getConfig().getInt("database.port");
        }
        if (getConfig().isString("database.database")) {
            database = getConfig().getString("database.database");
        }
        if (getConfig().isString("database.username")) {
            username = getConfig().getString("database.username");
        }
        if (getConfig().isString("database.password")) {
            password = getConfig().getString("database.password");
        }
        if (getConfig().isString("database.directory")) {
            directory = getConfig().getString("database.directory");
        }

        switch (databaseType) {
            case MySQL:
                sql = new MySQL(
                        getLogger(),
                        prefix,
                        hostname,
                        3306,
                        database,
                        username,
                        password);
                break;
            case MongoDB:
                sql = new Mongo(
                        getLogger(),
                        prefix,
                        hostname,
                        port,
                        database,
                        username,
                        password);
                break;
            case SQLite:
                sql = new SQLite(
                        getLogger(),
                        prefix,
                        directory,
                        database);
                break;
        }

        getLogger().info("Attempting to establish connection to database...");
        //if it didnt open
        if (!sql.open()) {
            getLogger().severe("Could not connect to database! Defaulting to SQLite instead!");
            sql = new SQLite(
                    getLogger(),
                    prefix,
                    directory,
                    database);

            //if not sqlite try sqlite instead
            if (!(sql instanceof SQLite)) {
                getLogger().info("Attempting to establish connection to database...");
                if (!sql.open()) {
                    getLogger().severe("Could not connect to SQLite either! Disabling plugin... :(");
                    getServer().getPluginManager().disablePlugin(this);
                    return;
                } else {
                    getLogger().info("Database connection successful!");
                }
            } else {
                getLogger().severe("Could not connect to SQLite! Disabling plugin... :(");
                getServer().getPluginManager().disablePlugin(this);
                return;
            }

        } else {
            getLogger().info("Database connection successful!");
        }
        //TODO read sql data


    }

    private enum DatabaseType {
        MySQL("mysql", 3306),
        MongoDB("mongodb", 27017),
        SQLite("sqlite");

        private String name;
        private int defaultPort;

        private DatabaseType(String name) {
            this.name = name;
        }

        private DatabaseType(String name, int port) {
            this.name = name;
            this.defaultPort = port;
        }

        public String getName() {
            return name;
        }

        public int getDefaultPort() {
            return defaultPort;
        }

        public static boolean isDatabaseType(String name) {
            for (DatabaseType type : values()) {
                if (type.getName().equalsIgnoreCase(name)) {
                    return true;
                }
            }
            return false;
        }

        public static DatabaseType getDatabaseType(String name) {
            for (DatabaseType type : values()) {
                if (type.getName().equalsIgnoreCase(name)) {
                    return type;
                }
            }
            return null;
        }
    }

    public static LightBikes getInstance() {
        return instance;
    }
}

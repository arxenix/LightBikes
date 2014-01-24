package com.ksanur.lightbikes;

/**
 * User: bobacadodl
 * Date: 1/23/14
 * Time: 9:12 PM
 */
public enum DatabaseType {
    MySQL("mysql", 3306),
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
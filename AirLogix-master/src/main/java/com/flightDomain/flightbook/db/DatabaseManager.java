package com.flightDomain.flightbook.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;

public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:" + getDatabasePath();
    private static Connection connection;

    private DatabaseManager() {
        // Privater Konstruktor, um die Instanziierung zu verhindern
    }

    private static String getDatabasePath() {
        String workingDir = System.getProperty("user.dir");
        String dbPath = workingDir + "/db/flightbook.db";
        return new File(dbPath).getAbsolutePath();
    }

    public static synchronized Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL);
                System.out.println("Connection to SQLite has been established.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static synchronized void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection to SQLite has been closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection = null;
            }
        }
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='User';";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.println("Table 'User' already exists.");
            } else {
                System.out.println("Table 'User' does not exist. Creating table...");
                String createTableSQL = "CREATE TABLE User ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "Name TEXT,"
                        + "Address TEXT,"
                        + "DateOfBirth TEXT,"
                        + "Birthplace TEXT,"
                        + "Nationality TEXT"
                        + ");";
                stmt.execute(createTableSQL);
                System.out.println("Table 'User' created.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

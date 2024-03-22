/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.DataAccess;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Madusanka(MadusankaB
 */
public class DatabaseConfig {
    // Singleton instance
    private static DatabaseConfig instance;
    private Connection connection;
    
    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/abc_lab";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private DatabaseConfig() {
        try {
            // Create database connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get the singleton instance
    public static DatabaseConfig getInstance() {
        instance = null;
        if (instance == null) {
            synchronized (DatabaseConfig.class) {
                if (instance == null) {
                    instance = new DatabaseConfig();
                }
            }
        }
        return instance;
    }

    // Get connection
    public Connection getConnection() {
        return connection;
    }
}

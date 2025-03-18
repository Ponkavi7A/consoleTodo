package jdbc;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import datamanagement.TaskManagement;

public class DatabaseConnection {
    private static DatabaseConnection instanceObject = null;
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(TaskManagement.class);

    private DatabaseConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/TODO", "root", "newpassword");
    }

    public static DatabaseConnection getDataBase() {
        if (instanceObject == null) {
            try {
                instanceObject = new DatabaseConnection();
            } catch (SQLException e) {
               logger.error("Database connection failed: " + e);
                return null;
            }
        }
        return instanceObject;
    }

    public Connection getConnection() {
        return connection;
    }
}

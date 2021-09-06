package com.project.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
	private static Connection connection = null;

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
        	// Step 1: load the driver
    		//Class.forName("jdbc:mysql://localhost:3306/project0");
    		// Step 2: Create connection object
    		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project_1", "root", "root");
        }
        return connection;
    }
}

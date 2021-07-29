package com.rishav.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {

	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
		// Note: Change the connection parameters accordingly.
		String hostName = "localhost";
		String dbName = "demo_db";
		String userName = "root";
		String password = "XXXXXXX";
		return getConnection(hostName, dbName, userName, password);
	}

	private static Connection getConnection(String hostName, String dbName, String userName, String password)
			throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		// URL Connection for MySQL:
		// Example:
		// jdbc:mysql://localhost:3306/simplehr
		String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		return conn;
	}
}
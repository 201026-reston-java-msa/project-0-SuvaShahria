package com.revature.dao.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class mySqlConnector {

	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			//System.out.println("Driver Loaded Successfully");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String url = "jdbc:postgresql://localhost:5432/";
		String username = "postgres";
		String password = "postgres";

		
		return DriverManager.getConnection(url, username, password);
		//System.out.println("Connection sucessful");
	
	}

}

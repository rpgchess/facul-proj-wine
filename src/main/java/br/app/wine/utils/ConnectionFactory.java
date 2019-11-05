package br.app.wine.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	public static Connection getConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:db_wine.db";  
			return DriverManager.getConnection(url);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
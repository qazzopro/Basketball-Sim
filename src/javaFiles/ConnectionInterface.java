package javaFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Abstract class for establishing a connection from an SQLite Database
 * @author Dean Jariv
 * @since 24 Aug 2019
 */
public abstract class ConnectionInterface {
	/**
	 * Creates a 'Connection' object from a SQLite database.
	 * May catch an SQLException, imported from java.sql
	 * @return Connection object using DriverManager.getConnection(), imported from java.sql
	 */
	public Connection getConnectionFromDatabase() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(getDatabasePath());
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	/**
	 * Returns the path to the database as a string.
	 * @return path to database.
	 */
	public abstract String getDatabasePath(); 
}
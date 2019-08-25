package javaFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Creates a Connection object from an SQLite Database
 * @author Dean Jariv
 * @since 26 Aug 2019
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
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * @return Path to the database as a string.
	 */
	public abstract String getDatabasePath(); 
}
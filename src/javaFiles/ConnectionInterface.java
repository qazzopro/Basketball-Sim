package javaFiles;

import java.sql.*;


/**
 * Interface class for establishing a connection from an SQLite Database
 * 
 */
public interface ConnectionInterface {
	/**
	 * Creates a Connection object from the database
	 * @return Connection object from SQLite Database
	 */
	public abstract Connection getConnectionFromDatabase();
}
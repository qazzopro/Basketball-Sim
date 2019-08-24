package javaFiles;

import java.sql.*;

/**
 * Interface class for establishing a connection from an SQLite Database
 * @author Dean Jariv
 * @since 24 Aug 2019
 */
public interface ConnectionInterface {
	/**
	 * Creates a 'Connection' object from a SQLite database.
	 * @return Connection object from SQLite Database
	 */
	public abstract Connection getConnectionFromDatabase();
}
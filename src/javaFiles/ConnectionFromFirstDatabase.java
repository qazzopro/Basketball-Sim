package javaFiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This implementation creates a Connection using "src/sql/firstDB.db".
 * @author Dean Jariv
 * @since 24 Aug 2019
 */

public class ConnectionFromFirstDatabase extends ConnectionInterface {
	/**
	 * Returns path from firstDB.db.
	 * Requires JDBC. 
	 * @return path to the first database.
	 */
	@Override
	public String getDatabasePath() {
		return "jdbc:sqlite:C:/Users/Dean Jariv/git/Basketball Simulation/src/sql/firstDB.db";
	}
	
}
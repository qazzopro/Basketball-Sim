package javaFiles;

import java.io.File;

/**
 * Implementation creates a Connection object using "src/sql/firstDB.db".
 * @author Dean Jariv
 * @since 26 Aug 2019
 */

public class ConnectionFromFirstDatabase extends ConnectionInterface {
	/**
	 * Returns path as a string to "/src/resources/databases/firstDB.db", that the application can use.
	 * Requires JDBC (found from googling "JDBC"). 
	 */
	@Override
	public String getDatabasePath() {
		File dbfile = new File ("");
		return "jdbc:sqlite:" + dbfile.getAbsolutePath() + "/src/resources/databases/firstDB.db";
	}
	
}
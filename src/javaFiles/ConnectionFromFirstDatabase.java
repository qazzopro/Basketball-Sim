package javaFiles;

import java.sql.*;

/**
 * This implementation creates a Connection using "src/sql/firstDB.db".
 */

public class ConnectionFromFirstDatabase implements ConnectionInterface {
	private String sql = "jdbc:sqlite:C:/Users/Dean Jariv/git/Basketball Simulation/src/sql/firstDB.db";
	
	@Override
	public Connection getConnectionFromDatabase() {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(this.sql);
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return conn;
	}
}
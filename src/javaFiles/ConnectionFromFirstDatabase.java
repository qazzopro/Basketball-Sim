package javaFiles;

import java.sql.*;

public class ConnectionFromFirstDatabase implements ConnectionInterface {
	private String sql = "jdbc:sqlite:D:/Users/Dean Jariv/Desktop/Basketball Simulation/src/sql/firstDB.db";
	
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
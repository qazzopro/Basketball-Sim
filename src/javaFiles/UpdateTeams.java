package javaFiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTeams extends UpdateFirstDatabase {
	private Connection conn;
	
	public UpdateTeams(Connection conn) {
		super(conn);
		this.conn = conn;
	}

	@Override
	/** 
	 * TODO Add possible Team Name Changes?
	 */
	public <E> void modifyDatabase(E e) {
		
		// Convert generic
		Team team = (Team)(e);
		
		// SQL Command
		String sqlCmd = "UPDATE teams SET Wins = ?, Losses = ?, [W/L%] = ? WHERE TeamId = ?";
		
		// Try update
		try {
			PreparedStatement stmt = conn.prepareStatement(sqlCmd);
			stmt.setInt(1, team.getWins());
			stmt.setInt(2, team.getLosses());
			stmt.setFloat(3, team.getWinLossRatio());
			stmt.setInt(4, team.getTeamID());
			stmt.executeUpdate();
		}
		
		catch (SQLException err) {
			System.out.println(err.getMessage());
		}
	}
}
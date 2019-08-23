package javaFiles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeamsGrabber implements GrabDataFromDatabase {
	private Connection conn;
	private List<Team> teams = new ArrayList<>();
	
	public TeamsGrabber(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public void addTeam(Team team) {
		teams.add(team);
	}
	
	public void deleteTeam(Team team) {
		teams.remove(team);
	}
	
	public List<Team> getDataList() {
		return teams;
	}
	
	@Override
	public void grabFromDatabase() {
		String sqlCommand = "SELECT TeamID, TeamName, WINS, Losses, [W/L%] FROM Teams";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlCommand);
			
			while (rs.next())
				this.addTeam(new Team(rs.getInt("TeamID"), rs.getString("TeamName"), rs.getInt("Wins"), rs.getInt("Losses")));
			
		}
		
		catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
}
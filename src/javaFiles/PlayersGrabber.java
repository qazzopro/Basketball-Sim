package javaFiles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlayersGrabber implements GrabDataFromDatabase {
	private Connection conn;
	private List<Player> players = new ArrayList<>();

	public PlayersGrabber(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public void deleteplayer(Player player) {
		players.remove(player);
	}
	
	public List<Player> getDataList() {
		return players;
	}
	
	@Override
	public void grabFromDatabase() {
		String sqlCommand = "SELECT PlayerID, PlayerName, TeamID, Skill FROM Players";
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlCommand);
			
			while (rs.next()) {
				this.addPlayer(new Player(rs.getInt("PlayerID"), rs.getString("PlayerName"), 
						rs.getInt("TeamID"), rs.getInt("Skill")));
			}
		}
		
		catch (SQLException e){
			System.out.println(e.getMessage());
		}
	}
}
package javaFiles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayersGrabber extends GrabDataFromDatabase {
	private Connection conn;
	private List<Player> players = new ArrayList<>();
	private String sqlCommand = "SELECT PlayerID, PlayerName, TeamID, Skill FROM Players";

	public PlayersGrabber(Connection conn) {
		super();
		this.conn = conn;
	}
	
	@Override
	public <E> void addData(E e) {
		players.add((Player)e);
	}
	
	@Override
	public <E> void deleteData(E e) {
		players.remove((Player) e);
	}
	
	/**
	 * @return A list of players retreived from the database.
	 */
	@Override
	public List<Player> getDataList() {
		return players;
	}

	@Override
	public String getSQLCommand() {
		return sqlCommand;
	}

	@Override
	public Connection getConn() {
		return conn;
	}

	@Override
	public Object FromSQL() {
		try {
			return (Object)(new Player(super.getCurrRS().getInt("PlayerID"), super.getCurrRS().getString("PlayerName"), super.getCurrRS().getInt("TeamID"), super.getCurrRS().getInt("Skill")));
		} 
		
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
package javaFiles;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for grabbing players from "resources/databases/firstdb.db".
 * @author Dean Jariv
 * @since 26 Aug 2019
 */
public class PlayersGrabber extends GrabDataFromDatabase {
	private List<Player> players = new ArrayList<>();
	private String sqlCommand = "SELECT PlayerID, PlayerName, TeamID, Skill FROM Players";

	/**
	 * Constructor for creating a PlayersGrabber object.
	 * @param conn Connection object for grabbing players.
	 */
	public PlayersGrabber(Connection conn) {
		super(conn);
	}
	
	/** 
	 * Adds a player to this object's players list.
	 */
	@Override
	public <E> void addData(E e) {
		players.add((Player)e);
	}
	
	/**
	 * Deletes a player from this object's players list.
	 */
	@Override
	public <E> void deleteData(E e) {
		players.remove((Player) e);
	}
	
	/**
	 * Returns a list of players retreived from "resources/databases/firstdb.db".
	 */
	@Override
	public List<Player> getDataList() {
		return players;
	}
	
	/**
	 * Returns the SQLite command for retreiving the player's data in "resources/databases/firstdb.db".
	 */
	@Override
	public String getSQLCommand() {
		return sqlCommand;
	}

	/**
	 * Returns a player using the data from a specific row in "resources/databases/firstdb.db".
	 */
	@Override
	public Object FromSQL() {
		try {
			return new Player(super.getCurrRS().getInt("PlayerID"), super.getCurrRS().getString("PlayerName"), super.getCurrRS().getInt("TeamID"), super.getCurrRS().getInt("Skill"));
		}
		
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
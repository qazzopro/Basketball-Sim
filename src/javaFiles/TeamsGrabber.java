package javaFiles;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for grabbing teams from "resources/databases/firstdb.db".
 * @author Dean Jariv
 * @since 26 Aug 2019
 */
public class TeamsGrabber extends GrabDataFromDatabase {
	private Connection conn;
	private List<Team> teams = new ArrayList<>();
	private String sqlCommand = "SELECT TeamID, TeamName, WINS, Losses, [W/L%] FROM Teams";
	
	/**
	 * Constructor for creating a TeamsGrabber object.
	 * @param conn Connection object for grabbing teams.
	 */
	public TeamsGrabber(Connection conn) {
		super();
		this.setConn(conn);
	}
	
	/**
	 * Returns a Connection object from "resources/databases/firstdb.db".
	 * TODO: Extract method to a new superclass, extending from GrabDataFromDatabase to GrabDatafromFirstDatabase.
	 */
	@Override
	public Connection getConn() {
		return conn;
	}

	/**
	 * A setter for conn.
	 * @param conn to set.
	 */
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	/**
	 * Adds a team to the object's list of teams.
	 */
	@Override
	public <E> void addData(E e) {
		teams.add((Team)e);
	}
	
	/**
	 * Deletes a team to the object's list of teams.
	 */
	@Override
	public <E> void deleteData(E e) {
		teams.remove((Team)e);
	}
	
	/**
	 * Returns the object's list of teams.
	 */
	@Override
	public List<Team> getDataList() {
		return teams;
	}
	
	/**
	 * Returns the SQLite command used to retrieve teams from "resources/databases/firstdb.db".
	 * TODO: Extract method to a new superclass, extending from GrabDataFromDatabase to GrabDatafromFirstDatabase.
	 */
	@Override
	public String getSQLCommand() {
		return sqlCommand;
	}
	
	/**
	 * Returns a team using the data at a specific row of "resources/databases/firstdb.db".
	 */
	@Override
	public Object FromSQL() {
		try {
			return new Team(super.getCurrRS().getInt("TeamID"), super.getCurrRS().getString("TeamName"), super.getCurrRS().getInt("Wins"), super.getCurrRS().getInt("Losses"));
		} 
		
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
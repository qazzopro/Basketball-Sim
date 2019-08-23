package javaFiles;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamsGrabber extends GrabDataFromDatabase {
	private Connection conn;
	private List<Team> teams = new ArrayList<>();
	private String sqlCommand = "SELECT TeamID, TeamName, WINS, Losses, [W/L%] FROM Teams";
	
	public TeamsGrabber(Connection conn) {
		super();
		this.conn = conn;
	}
	
	@Override
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	@Override
	public <E> void addData(E e) {
		teams.add((Team)e);
	}
	
	@Override
	public <E> void deleteData(E e) {
		teams.remove((Team)e);
	}
	
	@Override
	public List<Team> getDataList() {
		return teams;
	}

	@Override
	public String getSQLCommand() {
		return sqlCommand;
	}

	@Override
	public Object FromSQL() {
		try {
			return (new Team(super.getCurrRS().getInt("TeamID"), super.getCurrRS().getString("TeamName"), super.getCurrRS().getInt("Wins"), super.getCurrRS().getInt("Losses")));
		} 
		
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
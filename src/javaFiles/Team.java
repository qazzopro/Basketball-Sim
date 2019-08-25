package javaFiles;

import java.util.*;

/**
 * Team class for storing data about a basketball team.
 * @author Dean Jariv
 * @since 24 Aug 2019
 */
public class Team {
	
	private List<Player> players = new ArrayList<>();
	private int TeamID;
	private String TeamName;
	private int wins;
	private int losses;
	private float WinLossRatio;
	
	public Team(int teamID, String TeamName, int wins, int losses) {
		super();
		this.TeamID = teamID;
		this.TeamName = TeamName;
		this.wins = wins;
		this.losses = losses;
		updateRatio();
	}
	
	/**
	 * Adds Player to the list of Players in the Team object.
	 * @param p Player to add. 
	 */
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	/**
	 * Removes a Player from the list of Players in Team object.
	 * @param p Player to remove.
	 */
	public void removePlayer(Player p) {
		players.remove(p);
	}
	
	/**
	 * Increment object's wins.
	 */
	public void incrementWins() {
		wins++;
		updateRatio();
	}
	
	/**
	 * Increments object's losses.
	 */
	public void incrementLosses() {
		losses++;
		updateRatio();
	}
	
	/**
	 * Updates the object's winLossRatio by running checks on wins and losses.
	 * <p>
	 * If wins is 0, winLossRatio = 0.
	 * If wins is not 0, but losses is 0, winLossRatio = 1.
	 * @pre wins &gt;= 0
	 * @pre losses &gt;= 0
	 * @post winLossRatio = win/losses
	 */
	public void updateRatio() {
		if (getWins() == 0) this.setWinLossRatio(0);
		else if (getLosses() == 0) this.setWinLossRatio(1);
		else this.setWinLossRatio(getWins() / getLosses());
	}

	/**
	 * A getter for players.
	 * @return players.
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * A setter for players.
	 * @param players to set.
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	/**
	 * A getter for teamID.
	 * @return teamID.
	 */
	public int getTeamID() {
		return TeamID;
	}

	/**
	 * A setter for teamID.
	 * @param teamID to set.
	 */
	public void setTeamID(int teamID) {
		TeamID = teamID;
	}

	/**
	 * A getter for teamName.
	 * @return teamName.
	 */
	public String getTeamName() {
		return TeamName;
	}

	/**
	 * A setter for teamName.
	 * @param teamName to set.
	 */
	public void setTeamName(String teamName) {
		TeamName = teamName;
	}

	/**
	 * A getter for wins.
	 * @return wins.
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * A setter for wins.
	 * @param wins to set.
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}

	/**
	 * A getter for losses.
	 * @return losses.
	 */
	public int getLosses() {
		return losses;
	}

	/**
	 * A setter for losses.
	 * @param losses to set.
	 */
	public void setLosses(int losses) {
		this.losses = losses;
	}

	/**
	 * A getter for winLossRatio.
	 * @return winLossRatio.
	 */
	public float getWinLossRatio() {
		return WinLossRatio;
	}

	/**
	 * A setter for winLossRatio.
	 * @param winLossRatio to set.
	 */
	public void setWinLossRatio(float winLossRatio) {
		WinLossRatio = winLossRatio;
	}
	
	
}
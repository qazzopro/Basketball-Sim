package javaFiles;

import java.util.*;

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
	
	public void addPlayer(Player p) {
		players.add(p);
	}
	
	public void removePlayer(Player p) {
		players.remove(p);
	}
	
	public void incrementWins() {
		wins++;
		updateRatio();
	}
	
	/**
	 * Increments Losses
	 */
	public void incrementLosses() {
		losses++;
		updateRatio();
	}
	
	
	/**
	 * Updates winLossRatio by running checks on wins and losses
	 * @pre (wins >= 0)
	 * @param wins
	 * @pre (losses >= 0)
	 * @param losses
	 * @post winLossRatio = wins/losses
	 */
	public void updateRatio() {
		if (wins == 0) this.setWinLossRatio(0);
		else if (losses == 0) this.setWinLossRatio(1);
		else this.setWinLossRatio(wins / losses);
	}
	
	// Getters and Setters below
	public int getTeamID() {
		return TeamID;
	}

	public void setTeamID(int teamID) {
		TeamID = teamID;
	}

	public String getTeamName() {
		return TeamName;
	}

	public void setTeamName(String teamName) {
		TeamName = teamName;
	}

	public List<Player> getPlayers() {
		return players;
	}
	
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	public int getWins() {
		return wins;
	}
	
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	public int getLosses() {
		return losses;
	}
	
	public void setLosses(int losses) {
		this.losses = losses;
	}
	
	public float getWinLossRatio() {
		return WinLossRatio;
	}
	
	public void setWinLossRatio(float winLossRatio) {
		WinLossRatio = winLossRatio;
	}
}
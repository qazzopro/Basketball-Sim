package javaFiles;

import java.util.ArrayList;
import java.util.List;

public class PlayerAssignment {
	private List<Player> players = new ArrayList<>();
	private List<Team> teams = new ArrayList<>();
	
	public PlayerAssignment(List<Team> teams, List<Player> players) {
		super();
		this.players = players;
		this.teams = teams;
	}
	
	public void assignPlayers() {
		for (Player p : players) {
			for (Team t : teams) {
				if (p.getTeamID() == t.getTeamID()) {
					t.addPlayer(p);
					break;
				}
			}
		}
	}
	
	
}
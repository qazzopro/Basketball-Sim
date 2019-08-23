package javaFiles;

import java.util.ArrayList;
import java.util.List;

public class PlayerAssignment {
	private List<Player> players;
	private List<Team> teams;
	
	public PlayerAssignment(List<Team> teams, List<Player> players) {
		super();
		this.teams = teams;
		this.players = players;
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
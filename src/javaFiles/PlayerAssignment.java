package javaFiles;

import java.util.ArrayList;
import java.util.List;

public class PlayerAssignment {
	private List<?> players;
	private List<?> teams;
	
	public PlayerAssignment(List<?> teams, List<?> players) {
		super();
		this.teams = teams;
		this.players = players;
	}
	
	public void assignPlayers() {
		/*for (<?> p : players) {
			for (<?> t : teams) {
				if (p.getTeamID() == t.getTeamID()) {
					t.addPlayer(p);
					break;
				}
			}
		}*/
		
		for (int i = 0; i < players.size(); i++) {
			for (int j = 0; j < teams.size(); j++) {
				if (((Player) players.get(i)).getTeamID() == ((Team) teams.get(j)).getTeamID()) {
					((Team) teams.get(j)).addPlayer((Player)players.get(i));
					break;
				}
			}
		}
	}
	
	
}
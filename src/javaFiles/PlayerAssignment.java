package javaFiles;

import java.util.List;

/**
 * Assigns players to teams.
 * @author Dean Jariv
 * @since 26 Aug 2019
 */
public class PlayerAssignment {
	private List<Player> players;
	private List<Team> teams;
	
	/**
	 * Constructor for creating a PlayerAssignment object.
	 * @param teams The teams to assign the players.
	 * @param players The players being assigned to the teams.
	 */
	public PlayerAssignment(List<Team> teams, List<Player> players) {
		super();
		this.teams = teams;
		this.players = players;
	}
	
	/**
	 * Assigns players to teams, by checking if the player's teamID is equal to the team's teamID.
	 * TeamID is a foreign key in the database schema, so there must every player must belong to a team.
	 */
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
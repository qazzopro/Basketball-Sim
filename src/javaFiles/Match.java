package javaFiles;

import java.util.Random;

/**
 * Creates matches for teams to compete with one another.
 * @author Dean Jariv
 * @since 26 Aug 2019
 */
public class Match {
	private Team team1;
	private Team team2;
	
	/**
	 * Creates a match with two teams.
	 * @param team1 First team in match.
	 * @param team2 Second team in match.
	 */
	public Match(Team team1, Team team2) {
		super();
		this.team1 = team1;
		this.team2 = team2;
	}
	
	/**
	 * "Plays" a match.
	 * Winning team determined by the sum of skill attribute of all the players.
	 * Returns the winning team.
	 * @return Winning team as String. 
	 */
	public String playMatch() {
		int x = 0;
		int y = 0;
		
		for (Player p : team1.getPlayers()) {
			x += p.getSkill();
		}
		
		for (Player p : team2.getPlayers()) {
			y += p.getSkill();
		}
		
		// Change wins/losses after match
		if (x > y) {
			team1Wins();
			return team1Wins();
		}
		
		if (y > x) {
			team2Wins();
			return team2Wins();
		}
		
		else {
			Random rand = new Random();
			int n = rand.nextInt(2);
			
			if (n % 2 == 0) {
				return team1Wins();
			}			
			else {
				return team2Wins();
			}
		}
	}
	
	/**
	 * Changes the win/loss of the appropriate teams.
	 * @return String: Winning team's name.
	 */
	public String team1Wins() {
		team1.incrementWins();
		team2.incrementLosses();

		return team1.getTeamName();
	}
	
	/**
	 * Changes the win/loss of the appropriate teams.
	 * @return String: Winning team's name.
	 */
	public String team2Wins() {
		team1.incrementLosses();
		team2.incrementWins();
		
		return team2.getTeamName();
	}

	/**
	 * A getter for team1.
	 * @return team1.
	 */
	public Team getTeam1() {
		return team1;
	}

	/**
	 * A setter for team1.
	 * @param team1 to set.
	 */
	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	/**
	 * A getter for team2.
	 * @return team2.
	 */
	public Team getTeam2() {
		return team2;
	}

	/**
	 * A setter for team2.
	 * @param team2 to set.
	 */
	public void setTeam2(Team team2) {
		this.team2 = team2;
	}
	
}
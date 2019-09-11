package javaFiles;

import java.util.List;
import java.util.Random;

/**
 * Creates matches for teams to compete with one another.
 * @author Dean Jariv
 * @since 5 Sep 2019
 */
public class Match {
	private Team team1;
	private Team team2;
	private int x; 
	private int y;
	private List<Player> starters1, starters2;
	private String winner;
	
	/**
	 * Creates a match with two teams.
	 * @param team1 First team in match.
	 * @param team2 Second team in match.
	 * @param starters1 First team's starters.
	 * @param starters2 Second team's starters.
	 */
	public Match(Team team1, Team team2, List<Player> starters1, List<Player> starters2) {
		super();
		this.team1 = team1;
		this.team2 = team2;
		this.starters1 = starters1;
		this.starters2 = starters2;
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * "Plays" a match.
	 * Winning team determined by the sum of skill attribute of the respective starters.
	 * Sets winner appropriately.
	 */
	public void playMatch() {			
		 
		for (Player p : starters1) { 
			setX(getX() + p.getSkill());
		}
		for (Player p : starters2) {
			setY(getY() + p.getSkill());
		}
		
		// Change wins/losses after match
		if (getX() > getY()) {
			team1Wins();
		}
		
		else if (getY() > getX()) {
			team2Wins();
		}
		
		else {
			Random rand = new Random();
			int n = rand.nextInt(2);
			
			if (n % 2 == 0) {
				team1Wins();
			}			
			else {
				team2Wins();
			}
		}
	}

	/**
	 * Changes the win/loss of the appropriate teams.
	 * Also sets winner appropriately.
	 */
	public void team1Wins() {
		team1.incrementWins();
		team2.incrementLosses();

		setWinner(team1.getTeamName());
	}
	
	/**
	 * Changes the win/loss of the appropriate teams.
	 * Also sets winner appropriately.
	 */
	public void team2Wins() {
		team1.incrementLosses();
		team2.incrementWins();
		
		setWinner(team2.getTeamName());
	}
	
	/**
	 * A getter for x.
	 * X is the algorithm's evaluation of first team's skill.
	 * @return x.
	 */
	public int getX() {
		return x;
	}

	/**
	 * A setter for x.
	 * X is the algorithm's evaluation of first team's skill.
	 * @param x x to set.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * A getter for y.
	 * Y is the algorithm's evaluation of second team's skill.
	 * @return y.
	 */
	public int getY() {
		return y;
	}

	/**
	 * A setter for y.
	 * Y is the algorithm's evaluation of second team's skill.
	 * @param y y to set.
	 */
	public void setY(int y) {
		this.y = y;
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

	/**
	 * A getter for winner.
	 * Winner is the winning team's name.
	 * @return winner.
	 */
	public String getWinner() {
		return winner;
	}

	/**
	 * A setter for winner.
	 * Winner is the winning team's name.
	 * @param result to set.
	 */
	public void setWinner(String result) {
		this.winner = result;
	}

	/**
	 * A getter for starters1.
	 * @return starters1.
	 */
	public List<Player> getStarters1() {
		return starters1;
	}

	/**
	 * A getter for starters2.
	 * @return starters2.
	 */
	public List<Player> getStarters2() {
		return starters2;
	}
	
}
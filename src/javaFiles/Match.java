package javaFiles;

import java.util.Random;

public class Match {
	Team team1;
	Team team2;
	
	public Match(Team team1, Team team2) {
		super();
		this.team1 = team1;
		this.team2 = team2;
	}
	
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
	
	public String team1Wins() {
		team1.incrementWins();
		team2.incrementLosses();

		return team1.getTeamName();
	}
	
	public String team2Wins() {
		team1.incrementLosses();
		team2.incrementWins();
		
		return team2.getTeamName();
	}
	
}
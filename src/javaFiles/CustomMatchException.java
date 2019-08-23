package javaFiles;

@SuppressWarnings("serial")
public class CustomMatchException extends Exception {
	Team team1;
	Team team2;
	
	public CustomMatchException(Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
	}
	
	public String toString() {
		return "Team 1 must be different than Team 2.";
	}
}
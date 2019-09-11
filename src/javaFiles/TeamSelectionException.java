package javaFiles;

/**
 * Custom Exception for selecting teams
 * @author Dean Jariv
 * @since 9 Sep 2019
 */

public class TeamSelectionException extends Exception {
	
	private Team team1;
	private Team team2;
	
	/**
	 * ID for the error (?)
	 */
	private static final long serialVersionUID = 1L;
	
	public TeamSelectionException (Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
	}
	
	public String toString() {
		if (team1 == null || team2 == null)
			return "Please select both teams.";
		
		if (team1.getTeamName() == team2.getTeamName()) 
			return "Please select different teams.";
		
		return "Unknown Error.";
	}
}
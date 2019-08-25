package javaFiles;

/**
 * A custom exception class when creating 'Match'.
 * @author Dean Jariv
 * @since 24 Aug 2019
 */

@SuppressWarnings("serial")
public class CustomMatchException extends Exception {
	private Team team1;
	private Team team2;
	
	public CustomMatchException(Team team1, Team team2) {
		this.setTeam1(team1);
		this.setTeam2(team2);
	}
	
	/**
	 * Returns a message for the specific error.
	 */
	@Override
	public String toString() {
		String errMsg = null;
		
		if (team1.getTeamName() == team2.getTeamName())
			errMsg = "Team 1 must be different than Team 2.";			
		
		if (errMsg == null)
			errMsg = "Unknown Error.";
		
		return errMsg;
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
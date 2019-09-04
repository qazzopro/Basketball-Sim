package javaFiles;

/**
 * Custom Exception for 'Match'.
 * @author Dean Jariv
 * @since 26 Aug 2019
 */

@SuppressWarnings("serial")
public class CustomMatchException extends Exception {
	private Match match;
	
	/**
	 * Constructor for creating a CustomMatchException object.
	 * @param match The match that triggered the exception.
	 */
	public CustomMatchException(Match match) {
		this.match = match;
	}
	
	/**
	 * Returns a message for the specific error.
	 */
	@Override
	public String toString() {
		String errMsg;
		
		if (match.getTeam1().getTeamName() == match.getTeam2().getTeamName())
			errMsg = "Team 1 must be different than Team 2.";			
		
		else if (match.getStarters1().size() != 5 || match.getStarters2().size() != 5)
			errMsg = "You must select 5 starters for both teams.";
		
		else
			errMsg = "Unknown Error.";
		
		return errMsg;
	}
}
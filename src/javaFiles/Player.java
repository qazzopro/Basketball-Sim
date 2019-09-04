  package javaFiles;

/**
 * Stores player data.
 * @author Dean Jariv
 * @since 26 Aug 2019
 */
public class Player {
	private int playerID;
	private String PlayerName;
	private int teamID;
	private int skill;
	

	/**
	 * Constructor for creating a Player object.
	 * @param playerID Unique key.
	 * @param playerName Name of player.
	 * @param teamID Team that player belongs to.
	 * @param skill Skill level of player.
	 */
	public Player(int playerID, String playerName, int teamID, int skill) {
		super();
		this.skill = skill;
		this.playerID = playerID;
		this.teamID = teamID;
		this.PlayerName = playerName;
	}

	/**
	 * A getter for playerID.
	 * @return playerID.
	 */
	public int getPlayerID() {
		return playerID;
	}

	/**
	 * A setter for playerID.
	 * @param playerID to set.
	 */
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	/**
	 * A getter for playerName.
	 * @return playerName.
	 */
	public String getPlayerName() {
		return PlayerName;
	}

	/**
	 * A setter for playerName.
	 * @param playerName to set.
	 */
	public void setPlayerName(String playerName) {
		PlayerName = playerName;
	}

	/**
	 * A getter for teamID.
	 * @return teamID.
	 */
	public int getTeamID() {
		return teamID;
	}

	/**
	 * A setter for teamID.
	 * @param teamID to set.
	 */
	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	/**
	 * A getter for skill.
	 * @return skill.
	 */
	public int getSkill() {
		return skill;
	}

	/**
	 * A setter for skill.
	 * @param skill to set.
	 */
	public void setSkill(int skill) {
		this.skill = skill;
	}

	/*@Override
	public String toString() {
		return getPlayerName();
	}*/

	
}

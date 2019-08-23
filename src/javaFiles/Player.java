package javaFiles;

public class Player {
	private int playerID;
	private String PlayerName;
	private int teamID;
	private int skill;
	
	public Player(int playerID, String playerName, int teamID, int skill) {
		super();
		this.skill = skill;
		this.playerID = playerID;
		this.teamID = teamID;
		this.PlayerName = playerName;
	}

	public int getSkill() {
		return skill;
	}

	public void setSkill(int skill) {
		this.skill = skill;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}

	public String getPlayerName() {
		return PlayerName;
	}

	public void setPlayerName(String playerName) {
		this.PlayerName = playerName;
	}
}

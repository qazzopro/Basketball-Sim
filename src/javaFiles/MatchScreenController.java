package javaFiles;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 * Controller for showing the match screen.
 * @author Dean Jariv
 * @since 26 Aug 2019
 */
public class MatchScreenController implements Initializable {

	@FXML
	private ChoiceBox<String> selectTeam1;
	
	@FXML
	private ChoiceBox<String> selectTeam2;
	
	@FXML
	private Label result;
	
	@FXML
	private Button playButton;
	
	@FXML
	private CheckComboBox<String> tmpStarters1;
	
	@FXML
	private CheckComboBox<String> tmpStarters2;

	private GrabDataFromDatabase teams;
	private GrabDataFromDatabase players; 	
	
	/** 
	 * Initialize the controller (after constructor and FXML).
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Get Connection from SQLDatabase - could do in a method?
		ConnectionInterface db = new ConnectionFromFirstDatabase();
		Connection conn = db.getConnectionFromDatabase();
		
		// set teams - could do in method with above
		this.setTeams(new TeamsGrabber(conn));
		this.setPlayers(new PlayersGrabber(conn));
		
		// ----------------------------------------------
		
		// set the data list
		this.getTeams().grabFromDatabase();
		this.getPlayers().grabFromDatabase();
		
		// User pa to assign players to teams
		@SuppressWarnings("unchecked")
		PlayerAssignment pA = new PlayerAssignment((List<Team>)(getTeams().getDataList()), ((List<Player>)getPlayers().getDataList()));
		pA.assignPlayers();
		
		
		// ------------------------------------------------
		
		// Add teams names to choice boxes
		fillTeamSelection(selectTeam1);
		fillTeamSelection(selectTeam2);
		
		// Add Listeners to handle changing teams
		addStartersListener(selectTeam1, tmpStarters1);
		addStartersListener(selectTeam2, tmpStarters2);
	}
	
	/**
	 * Populates a "Select Team" ChoiceBox with all the NBA team names.
	 * @param choiceBox The ChoiceBox to populate.
	 */
	private void fillTeamSelection(ChoiceBox<String> choiceBox) {
		ObservableList<String> obList = FXCollections.observableArrayList();
		
		obList.add("Select Team");

		for (int i = 0; i < getTeams().getDataList().size(); i++) {
			obList.add(((Team) (getTeams().getDataList().get(i))).getTeamName());
		}
		
		// Add teams to choice boxes
		obList.remove(32);
		obList.remove(31);
		
		choiceBox.setItems(obList);
		choiceBox.setValue("Select Team");
	}
	
	/**
	 * Populates the players in CheckComboBox according to the selected team from the ChoiceBox.
	 * @param choiceBox The team selected in ChoiceBox (as String).
	 * @param checkComboBox The CheckComboBox that is populated with all the players in selected team. 
	 */
	private void addStartersListener(ChoiceBox<String> choiceBox, CheckComboBox<String> checkComboBox) {
		choiceBox.
		getSelectionModel().
		selectedItemProperty().
		addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> 
		{
			checkComboBox.getItems().clear();
			ObservableList<String> obList = FXCollections.observableArrayList();
									
			try {
				for (Player p : teamObjectFromString(newValue).getPlayers())
					obList.add(p.getPlayerName());
				
			}	
			
			catch (Exception e) {
				
			}
			
			checkComboBox.getItems().addAll(obList);
		}
		);
	}
	
	/**
	 * Returns a Team object that matches the name given.
	 * @param name A team's name.
	 * @return A team object with identical name.
	 * TODO: Change return criteria from name to ID. (Identical team name).
	 */
	@SuppressWarnings("unchecked")
	private Team teamObjectFromString(String name) {
		for (Team t : (List<Team>)getTeams().getDataList()) {
			
			if (t.getTeamName() == name) {
				return t;
			}					
		}
		
		return null;
	}
	
	/**
	 * Returns a Player object that matches the name given.
	 * @param name A player's name.
	 * @return A player object with identical name. 
	 * TODO: Change return criteria from name to ID. (Identical player name).
	 */
	@SuppressWarnings("unchecked")
	private Player playerNameFromString(String name) {
		
		for (Player p : (List<Player>)getPlayers().getDataList()) {
			if (p.getPlayerName() == name)
				return p;
		}
		
		return null;
	}
	
	/**
	 * Button press on scene
	 * @throws Exception - An exception
	 */
	@FXML
	public void buttonPress() throws Exception {	
		
		// Create a match
		
		// First, list the starters.
		List<Player> starters1 = new ArrayList<>();
		List<Player> starters2 = new ArrayList<>();
		
		for (String name : tmpStarters1.getCheckModel().getCheckedItems()) {
			starters1.add(playerNameFromString(name));
		}
			
		for (String name : tmpStarters2.getCheckModel().getCheckedItems())
			starters2.add(playerNameFromString(name));
		
		// Add starters to match
		try {
			displayMatchResult(new Match(teamObjectFromString(selectTeam1.getValue()), teamObjectFromString(selectTeam2.getValue()), starters1, starters2), result);		
		}
		
		catch (CustomMatchException e) {
			result.setText(e.toString());
		}
		
		catch (Exception e) {
			result.setText("Please Select Both Teams.");
		}
		
		finally {
			result.setVisible(true);
		}
	}
	
	/**
	 * Displays the match result
	 * @param match The Match object.
	 * @param result The label to show the result to player.
	 * @throws CustomMatchException A custom exception if the two teams are the same or 5 players are not selected for each side.
	 */
	public void displayMatchResult(Match match, Label result) throws CustomMatchException {
		
		if (match.getTeam1().getTeamID() == match.getTeam2().getTeamID()) {
			throw new CustomMatchException(match);
		}
		
		else if (match.getStarters1().size() != 5 || match.getStarters2().size() != 5) {
			throw new CustomMatchException(match);
		}
		
		match.playMatch();
		
		result.setText(match.getWinner() + " wins!");
	}

	/**
	 * A getter for teams.
	 * @return teams.
	 */
	private GrabDataFromDatabase getTeams() {
		return teams;
	}

	/**
	 * A setter for teams.
	 * @param teams to set.
	 */
	private void setTeams(GrabDataFromDatabase teams) {
		this.teams = teams;
	}

	/**
	 * A getter for players.
	 * @return players.
	 */
	private GrabDataFromDatabase getPlayers() {
		return players;
	}

	/**
	 * A setter for players.
	 * @param players to set.
	 */
	private void setPlayers(GrabDataFromDatabase players) {
		this.players = players;
	}
	
}
package javaFiles;

import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

import javafx.beans.value.ChangeListener;
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
	private CheckComboBox<Player> starters1;
	
	@FXML
	private CheckComboBox<Player> starters2;

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
		
		fillTeamSelection(selectTeam1);
		fillTeamSelection(selectTeam2);
		
		// Add listener
		selectTeam1.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { 
			
			// If change team in choice box
			public void changed(ObservableValue ov, Number value, Number new_value)
			{
				Team chosen1 = null;
				Team chosen2 = null;
				
				for (int i = 0; i < getTeams().getDataList().size(); i++) {
					Team t = (Team) getTeams().getDataList().get(i);
					if (t.getTeamName() == selectTeam1.getValue()) {
						chosen1 = t;
					}
					
					if (t.getTeamName() == selectTeam2.getValue()) {
						chosen2 = t;
					}
				}
				
				for (Player p : chosen1.getPlayers()) 
				System.exit(0);
			}
		});
			
	}
		
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
	 * Button press on scene
	 * @throws Exception - An exception
	 */
	@FXML
	public void buttonPress() throws Exception {	
		
		// Create a match
		Team chosen1 = null;
		Team chosen2 = null;
		
		for (int i = 0; i < getTeams().getDataList().size(); i++) {
			Team t = (Team) getTeams().getDataList().get(i);
			if (t.getTeamName() == selectTeam1.getValue()) {
				chosen1 = t;
			}
			
			if (t.getTeamName() == selectTeam2.getValue()) {
				chosen2 = t;
			}
		}
		
		
		try {
			displayMatchResult(new Match(chosen1, chosen2), result);		
		}
		
		catch (CustomMatchException e) {
			result.setText(e.toString());
		}
		
		catch (Exception e) {
			result.setText("Please Select Both Teams.");
		}
		
		result.setVisible(true);
	}
	
	/**
	 * Displays the match result
	 * @param match The Match object.
	 * @param result The label to show the result to player.
	 * @throws CustomMatchException A custom exception if the two teams are the same.
	 */
	public void displayMatchResult(Match match, Label result) throws CustomMatchException {
		
		if (match.getTeam1().getTeamID() == match.getTeam2().getTeamID()) {
			throw new CustomMatchException(match.getTeam1(), match.getTeam2());
		}
		
		result.setText(match.playMatch() + " wins!");
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
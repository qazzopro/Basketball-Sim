package javaFiles;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller class for showing the match screen scene.
 * @author Dean Jariv
 * @since 24 Aug 2019
 */
public class MatchScreenController implements Initializable {

	@FXML
	private ChoiceBox<String> team1;
	
	@FXML
	private ChoiceBox<String> team2;
	
	@FXML
	private Label result;
	
	@FXML
	private Button playButton;
	
	
	
	
	// Teams
	private GrabDataFromDatabase teams;
	private GrabDataFromDatabase players; 	
	@SuppressWarnings("unchecked")
	PlayerAssignment pA = new PlayerAssignment((List<Team>)(teams.getDataList()), ((List<Player>)players.getDataList()));
	
	/** 
	 * Initialise the controller (after constructor and FXML).
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Get Connection from SQLDatabase - could do in a method?
		ConnectionInterface db = new ConnectionFromFirstDatabase();
		Connection conn = db.getConnectionFromDatabase();
		
		// set teams - could do in method with above
		teams = new TeamsGrabber(conn);
		players = new PlayersGrabber(conn);
		
		// set the datalist to sets
		teams.grabFromDatabase();
		players.grabFromDatabase();
		
		// User pa to assign players to teams
		pA.assignPlayers();
		
		ObservableList<String> obList = FXCollections.observableArrayList();
		
		obList.add("Select Team");

		for (int i = 0; i < teams.getDataList().size(); i++) {
			obList.add(((Team) (teams.getDataList().get(i))).getTeamName());
		}
		
		obList.remove(32);
		obList.remove(31);
		team1.setItems(obList);
		team2.setItems(obList);
		team1.setValue("Select Team");
		team2.setValue("Select Team");
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
		
		for (int i = 0; i < teams.getDataList().size(); i++) {
			Team t = (Team) teams.getDataList().get(i);
			if (t.getTeamName() == team1.getValue()) {
				chosen1 = t;
			}
			
			if (t.getTeamName() == team2.getValue()) {
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
	 * @param match
	 * @param result
	 * @throws CustomMatchException
	 */
	public void displayMatchResult(Match match, Label result) throws CustomMatchException {
		
		if (match.getTeam1().getTeamID() == match.getTeam2().getTeamID()) {
			throw new CustomMatchException(match.getTeam1(), match.getTeam2());
		}
		
		result.setText(match.playMatch() + " wins!");
	}
	
}
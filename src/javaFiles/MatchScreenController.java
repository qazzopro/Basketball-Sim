package javaFiles;

import java.net.URL;
import java.sql.Connection;
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

public class MatchScreenController implements Initializable {

	@FXML
	private ChoiceBox<String> team1;
	
	@FXML
	private ChoiceBox<String> team2;
	
	@FXML
	private Label result;
	
	@FXML
	private Button playButton;
	
	
	// Get Connection from SQLDatabase
	ConnectionInterface db = new ConnectionFromFirstDatabase();
	Connection conn = db.getConnectionFromDatabase();
	
	// Teams
	TeamsGrabber teams = new TeamsGrabber(conn);
	PlayersGrabber players = new PlayersGrabber(conn);
	PlayerAssignment pA = new PlayerAssignment(teams.getDataList(), players.getDataList());
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		teams.grabFromDatabase();
		players.grabFromDatabase();
		pA.assignPlayers();
		
		ObservableList<String> obList = FXCollections.observableArrayList();
		
		obList.add("Select Team");

		for (int i = 0; i < teams.getDataList().size(); i++) {
			obList.add(teams.getDataList().get(i).getTeamName());
		}
		
		obList.remove(32);
		obList.remove(31);
		team1.setItems(obList);
		team2.setItems(obList);
		team1.setValue("Select Team");
		team2.setValue("Select Team");
	}
	
	@FXML
	public void buttonPress() throws Exception {	
		
		// Create a match
		Team chosen1 = null;
		Team chosen2 = null;
		
		for (Team t : teams.getDataList()) {
			if (t.getTeamName() == team1.getValue()) {
				chosen1 = t;
			}
			
			if (t.getTeamName() == team2.getValue()) {
				chosen2 = t;
			}
		}
		
		try {
			Match match = new Match(chosen1, chosen2);
			result.setText(match.playMatch() + " wins!");
		}
		
		catch (Exception e) {
			result.setText("Please Select Both Teams.");
		}
		
		result.setVisible(true);
	}
	
}
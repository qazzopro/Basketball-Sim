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
	GrabDataFromDatabase teams = new TeamsGrabber(conn);
	GrabDataFromDatabase players = new PlayersGrabber(conn);	
	@SuppressWarnings("unchecked")
	PlayerAssignment pA = new PlayerAssignment((List<Team>)(teams.getDataList()), ((List<Player>)players.getDataList()));

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		teams.grabFromDatabase();
		players.grabFromDatabase();
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
	
	public void displayMatchResult(Match match, Label result) throws CustomMatchException {
		
		if (match.getTeam1().getTeamID() == match.getTeam2().getTeamID()) {
			throw new CustomMatchException(match.getTeam1(), match.getTeam2());
		}
		
		result.setText(match.playMatch() + " wins!");
	}
	
}
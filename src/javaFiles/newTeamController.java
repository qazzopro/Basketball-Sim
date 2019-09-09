package javaFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class newTeamController implements Initializable {
	
	@FXML
	private ChoiceBox<String> selectTeam1;
	
	@FXML
	private ChoiceBox<String> selectTeam2;
	
	@FXML
	private Button button;
	
	@FXML
	private ImageView jersey1;
	
	@FXML
	private ImageView jersey2;
	
	
	private GrabDataFromDatabase teams;
	private GrabDataFromDatabase players;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// Get Connection from SQLDatabase - could do in a method?
		ConnectionInterface db = new ConnectionFromFirstDatabase();
		Connection conn = db.getConnectionFromDatabase();
		
		// set teams - could do in method with above
		this.setTeams(new TeamsGrabber(conn));
		this.setPlayers(new PlayersGrabber(conn));
		
		// ----------------------------------------------
		
		// set the data lists
		this.getTeams().grabFromDatabase();
		this.getPlayers().grabFromDatabase();
		
		// ----------------------------------------------
		
		// Add teams names to choice boxes for choosing teams
		fillTeamSelection(selectTeam1);
		fillTeamSelection(selectTeam2);
		
		// Add Listeners to handle changing teams
		addJerseyListener(selectTeam1, jersey1);
		addJerseyListener(selectTeam2, jersey2);
	}
	
	private void addJerseyListener(ChoiceBox<String> choiceBox, ImageView image) {
		choiceBox.
		getSelectionModel().
		selectedItemProperty().
		addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> 
		{
			// Change Jersey Image for the selected team
			
			// Find correct file 
			File f = new File("");
			
			try {
				Image newJersey = new Image(new FileInputStream(f.getAbsolutePath() + "/src/resources/images/jerseys/" + newValue + ".jpg"));
				image.setImage(newJersey);
			} 
			
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		);
	}
	
	/**
	 * Button press to go to player selection
	 * @throws Exception - An exception
	 */
	@FXML
	public void buttonPress() throws Exception {	
		
		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("resources/view/PlayerSelection.fxml"));
			Scene scene = new Scene(root);
			Stage stage = new Stage();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
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
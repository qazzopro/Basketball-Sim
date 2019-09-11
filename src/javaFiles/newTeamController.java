package javaFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
	
	@FXML
	private Label errMsg;
	
	
	private GrabDataFromDatabase teams;
	private GrabDataFromDatabase players;
	
	private Team team1 = null;
	private Team team2 = null;

	String tmp = null;
	
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
	
	/**
	 * Add Listener to the ChoiceBox for selecting teams.
	 * Allows changing of teams for the image and players for next screen
	 * @param team The team that has changed
	 * @param choiceBox The box user is selecting from
	 * @param image The iamge that is changing
	 */
	private void addJerseyListener(ChoiceBox<String> choiceBox, ImageView image) {
		
		choiceBox.
		getSelectionModel().
		selectedItemProperty().
		addListener( (ObservableValue<? extends String> observable, String oldValue, String newValue) -> 
		{
			// Find correct file 
			File f = new File("");
			tmp = newValue;
			
			try {
				Image newJersey = new Image(new FileInputStream(f.getAbsolutePath() + "/src/resources/images/jerseys/" + newValue + ".jpg"));
				image.setImage(newJersey);
			} 
			
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			finally {
				String fix = choiceBox.getId();
				if (fix.equals("selectTeam1")) {
					setTeam1(teamObjectFromString(tmp));
				}
				
				else if (fix.equals("selectTeam2")) {
					setTeam2(teamObjectFromString(tmp));
				}
				
				else {
					System.out.println("YIKES");
				}
			}
		}	
		);
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
	 * Button press to go to player selection
	 * @throws Exception - An exception
	 */
	@FXML
	public void buttonPress(MouseEvent event) throws Exception {	
		
		try {
			
			if (team1 == null || team2 == null)
				throw new TeamSelectionException(team1, team2);
			
			else if (team1 == team2)
				throw new TeamSelectionException(team1, team2);
			
			else {
				Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("resources/view/PlayerSelection.fxml"));			
				Scene scene = new Scene(root);
				stage.setTitle("Select Players");
				stage.setScene(scene);
				stage.show();
				
			}
		}
		
		catch (TeamSelectionException e) {
			errMsg.setText(e.toString());
			errMsg.setVisible(true);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
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
		
		System.out.println("Fuck");
		return null;
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

	/**
	 * A getter for team1.
	 * @return team1.
	 */
	public Team getTeam1() {
		return team1;
	}

	/**
	 * A setter for team1.
	 * @param team1 The team1 to set.
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
	 * @param team2 The team2 to set.
	 */
	public void setTeam2(Team team2) {
		this.team2 = team2;
	}
}
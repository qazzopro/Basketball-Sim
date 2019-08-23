package javaFiles;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

public class FirstUIController implements Initializable {

	@FXML
	public void playButtonClicked(ActionEvent event) throws Exception {
		Parent second = FXMLLoader.load(getClass().getResource("SecondUI.fxml"));
		
		Scene newScene = new Scene(second);
		
		Stage newPage =  (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		newPage.setScene(newScene);
		
		newPage.show();
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	
	
}
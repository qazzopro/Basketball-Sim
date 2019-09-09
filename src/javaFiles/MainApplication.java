package javaFiles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Application for running the program.
 * @author Dean Jariv
 * @since 6 Sep 2019
 */
public class MainApplication extends Application { 
	
	/**
	 * "Loads TeamSelectionScreen.fxml" in a titled window "Select Team" and displays the window.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("resources/view/TeamSelection.fxml"));
			primaryStage.setTitle("Select Team");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Launches the program.
	 * @param args for main.
	 */
	public static void main (String args[]) {
		launch(args);
	}
	
	
}
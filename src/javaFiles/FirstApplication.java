package javaFiles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Application for running the program
 * @author Dean Jariv
 * @since 24 Aug 2019
 */
public class FirstApplication extends Application { 
	
	/**
	 * "Loads MatchScreen.fxml" in a titled window "Play Match" and displays the window.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MatchScreen.fxml"));
		primaryStage.setTitle("Play Match");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	/**
	 * Launches the program
	 * @param args for main
	 */
	public static void main (String args[]) {
		launch(args);
	}
}
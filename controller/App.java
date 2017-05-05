package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/view/fxml/Entrar.fxml"));
		
		Scene scene = new Scene(root);
		
		stage.getIcons().add(new Image("/view/img/Logo.png"));
		stage.setScene(scene);
		stage.setTitle("RENDI - Soluções Rentáveis.");
		stage.setMaximized(true);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
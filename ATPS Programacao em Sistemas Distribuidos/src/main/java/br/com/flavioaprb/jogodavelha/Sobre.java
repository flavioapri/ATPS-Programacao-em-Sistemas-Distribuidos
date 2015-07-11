package br.com.flavioaprb.jogodavelha;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Contém informações gerais sobre a aplicação.
 * 
 * @author Flávio Aparecido Ribeiro
 *
 */
public class Sobre extends Application {

	private static Stage stage;

	public static void main(String[] args) {

		launch(args);
	}

	public static Stage getStage() {
		return stage;
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource(
				"/fxml/Sobre.fxml"));
		Sobre.stage = stage;
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Sobre");
		stage.setResizable(false);
		stage.show();
	}
}

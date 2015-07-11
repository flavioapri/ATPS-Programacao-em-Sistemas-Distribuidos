package br.com.flavioaprb.jogodavelha;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * GUI de conexão ao servidor. Possui as opções para se conectar a um novo
 * servidor de jogo.
 * 
 * @author Flávio Aparecido Ribeiro
 *
 */
public class Main extends Application {

	private static Stage stage;

	public static Stage getStage() {
		return stage;
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource(
				"/fxml/Main.fxml"));
		Main.stage = stage;
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Acesso ao Servidor");
		stage.setResizable(false);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

package br.com.flavioaprb.jogodavelha;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe principal da aplicação. Executa o jogo.
 * 
 * @author Flávio Aparecido Ribeiro
 *
 */
public class Jogo extends Application {

	private static Stage stage;

	private static JogoInterface jogo;

	public Jogo(String endereco, JogoInterface jogo) {
		this.jogo = jogo;
	}

	public static JogoInterface getJogo() {
		return jogo;
	}

	public static Stage getStage() {
		return stage;
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource(
				"/fxml/JogoCliente.fxml"));
		Jogo.stage = stage;
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("JogoInterface Da Velha 1.0");
		stage.setResizable(false);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

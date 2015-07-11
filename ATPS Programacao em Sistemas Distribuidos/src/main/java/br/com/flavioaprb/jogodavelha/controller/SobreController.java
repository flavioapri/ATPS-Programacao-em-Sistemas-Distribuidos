package br.com.flavioaprb.jogodavelha.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import br.com.flavioaprb.jogodavelha.Sobre;

/**
 * GUI da classe sobre.
 * 
 * @author Flávio Aparecido Ribeiro
 *
 */
public class SobreController implements Initializable {

	@FXML
	private Button btnContinuar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnContinuar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				Sobre.getStage().close();
			}
		});
	}
}

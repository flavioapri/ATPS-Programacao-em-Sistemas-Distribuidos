package br.com.flavioaprb.jogodavelha.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import br.com.flavioaprb.jogodavelha.Main;
import br.com.flavioaprb.jogodavelha.Jogo;
import br.com.flavioaprb.jogodavelha.JogoInterface;
import br.com.flavioaprb.jogodavelha.JogoServidor;

/**
 * Controller da GUI de conexão ao servidor. Contém toda a lógica da GUI também.
 * 
 * @author Flávio Aparecido Ribeiro
 *
 */
public class HomeController implements Initializable {

	@FXML
	private RadioButton rbLocal;

	@FXML
	private ToggleGroup servidor;

	@FXML
	private RadioButton rbRemoto;

	@FXML
	private TextField txtIp;

	@FXML
	private Button btnConectar;

	@FXML
	private Label lblIp;

	@FXML
	private CheckBox cbCriarServidor;

	private String endereco;

	private JogoServidor servidorDeJogo;

	private JogoInterface jogo;

	protected Alert janelaDeAviso;

	protected Jogo cliente;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rbRemoto.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				lblIp.setDisable(false);
				txtIp.setDisable(false);
				cbCriarServidor.setDisable(true);
				btnConectar.setDisable(false);
			}
		});

		rbLocal.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				lblIp.setDisable(true);
				txtIp.setDisable(true);
				cbCriarServidor.setDisable(false);
				btnConectar.setDisable(false);
			}
		});

		btnConectar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (rbLocal.isSelected()) {
					endereco = "localhost";
					if (cbCriarServidor.isSelected()) {
						try {
							servidorDeJogo = new JogoServidor();
							JogoServidor.main(null);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					try {
						conectarServidor(endereco);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (rbRemoto.isSelected()) {
					endereco = txtIp.getText();
					try {
						conectarServidor(endereco);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Main.getStage().close();
			}
		});
	}

	/**
	 * Conecta o cliente a um servidor de jogo.
	 *
	 * @author Flávio Aparecido Ribeiro
	 * @param endereco
	 *            endereço do servidor
	 * @throws Exception
	 *             endereco de conexao seja inválido ou não exista
	 */
	private void conectarServidor(String endereco) throws Exception {
		try {
			jogo = (JogoInterface) Naming
					.lookup("//" + endereco + "/RmiServer");
			jogo.incrementarCliente();
			if (jogo.getClientesConectados() > 2) {
				mostrarAlerta(
						AlertType.INFORMATION,
						"Já existem dois jogadores disputando uma partida neste servidor.",
						"Servidor cheio");
				System.exit(0);
			} else {
				new Jogo(endereco, jogo).start(new Stage());
			}
			while (jogo.getClientesConectados() == 1) {
				mostrarAlerta(AlertType.INFORMATION,
						"Aguarde o outro jogador.", "Aguardando jogador");
			}

		} catch (MalformedURLException | RemoteException | NotBoundException e1) {
			mostrarAlerta(AlertType.ERROR, "IP inválido ou inexistente",
					"Erro de IP");
			System.exit(0);
		}
	}

	/**
	 * Mostra janela com os alertas da GUI.
	 *
	 * @author Flávio Aparecido Ribeiro
	 * @param tipoAviso
	 *            tipo do alerta a ser mostrado
	 * @param mensagem
	 *            mensagem do alerta
	 * @param titulo
	 *            titulo do alerta
	 */
	private void mostrarAlerta(AlertType tipoAviso, String mensagem,
			String titulo) {
		janelaDeAviso = new Alert(tipoAviso, mensagem, ButtonType.OK);
		janelaDeAviso.setTitle(titulo);
		janelaDeAviso.setHeaderText(null);
		janelaDeAviso.showAndWait();
	}

}

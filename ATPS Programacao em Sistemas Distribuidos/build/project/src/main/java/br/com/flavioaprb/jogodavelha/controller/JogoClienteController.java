package br.com.flavioaprb.jogodavelha.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import br.com.flavioaprb.jogodavelha.Jogo;
import br.com.flavioaprb.jogodavelha.JogoInterface;
import br.com.flavioaprb.jogodavelha.Sessao;
import br.com.flavioaprb.jogodavelha.Sobre;

/**
 * Toda a interface do jogo.
 * 
 * @author Flávio Aparecido Ribeiro
 * @version 1.0
 *
 */
public class JogoClienteController implements Initializable {

	@FXML
	private Button btn8;

	@FXML
	private Button btn6;

	@FXML
	private Button btn7;

	@FXML
	private Button btn3;

	@FXML
	private Button btn4;

	@FXML
	private Button btn1;

	@FXML
	private Button btn2;

	@FXML
	private Button btn9;

	@FXML
	private Button btn5;

	@FXML
	private Button btnNovoJogo;

	@FXML
	private Button btnSobre;

	@FXML
	private Button btnSair;

	@FXML
	private TitledPane pnlInformacoesDoJogo;

	@FXML
	private Label lblJogador1;

	@FXML
	private Label lblSimbolo1;

	@FXML
	private Label lblVitoriasJogador1;

	@FXML
	private Label lblRodadasGanhasJogador1;

	@FXML
	private Label lblJogador2;

	@FXML
	private Label lblSimboloJogador1;

	@FXML
	private Label lblVitoriasJogador2;

	@FXML
	private Label lblRodadasGanhasJogador2;

	@FXML
	private Label lblEmpates;

	private JogoInterface jogo;

	private String simbolo;

	private List<Button> listaBotoes;

	private JogoClienteLogica logicaCliente;

	private List<Label> listaRotulos;

	@Override
	public void initialize(URL url, ResourceBundle bundle) {

		logicaCliente = new JogoClienteLogica();

		jogo = Jogo.getJogo();

		listaBotoes = logicaCliente.criarArrayBotoes(btn1, btn2, btn3, btn4,
				btn5, btn6, btn7, btn8, btn9);

		listaRotulos = logicaCliente.criarArrayRotulos(lblVitoriasJogador1,
				lblRodadasGanhasJogador1, lblVitoriasJogador2,
				lblRodadasGanhasJogador2, lblEmpates);

		try {
			simbolo = jogo.getSimbolo();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		logicaCliente.atualizarGUI(listaBotoes, jogo, listaRotulos);

		btn1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				logicaCliente.executarAcaoDoBotao(btn1, 0, 0, jogo, simbolo,
						listaBotoes);
			}
		});

		btn2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				logicaCliente.executarAcaoDoBotao(btn2, 0, 1, jogo, simbolo,
						listaBotoes);
			}
		});

		btn3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				logicaCliente.executarAcaoDoBotao(btn3, 0, 2, jogo, simbolo,
						listaBotoes);
			}
		});

		btn4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				logicaCliente.executarAcaoDoBotao(btn4, 1, 0, jogo, simbolo,
						listaBotoes);
			}
		});

		btn5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				logicaCliente.executarAcaoDoBotao(btn5, 1, 1, jogo, simbolo,
						listaBotoes);
			}
		});

		btn6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				logicaCliente.executarAcaoDoBotao(btn6, 1, 2, jogo, simbolo,
						listaBotoes);
			}
		});

		btn7.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				logicaCliente.executarAcaoDoBotao(btn7, 2, 0, jogo, simbolo,
						listaBotoes);
			}
		});

		btn8.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				logicaCliente.executarAcaoDoBotao(btn8, 2, 1, jogo, simbolo,
						listaBotoes);
			}
		});

		btn9.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				logicaCliente.executarAcaoDoBotao(btn9, 2, 2, jogo, simbolo,
						listaBotoes);
			}
		});

		btnNovoJogo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					jogo.setSessao(new Sessao());
					logicaCliente.limparCampos(listaBotoes, jogo);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});

		btnSobre.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					new Sobre().start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		btnSair.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				logicaCliente.limparCampos(listaBotoes, jogo);
				try {
					jogo.desconectarCliente();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}
		});
	}
}
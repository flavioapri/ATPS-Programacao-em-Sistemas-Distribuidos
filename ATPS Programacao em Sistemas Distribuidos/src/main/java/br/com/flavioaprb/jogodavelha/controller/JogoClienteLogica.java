package br.com.flavioaprb.jogodavelha.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import br.com.flavioaprb.jogodavelha.JogoInterface;

/**
 * Contém toda a lógica da GUI do cliente.
 * 
 * @author Flávio Aparecido Ribeiro
 *
 */
public class JogoClienteLogica {

	/**
	 * Cria um Array List com todos os botões do painel para utilizar nos
	 * métodos para simpleficação do código,
	 *
	 * @author Flávio Aparecido Ribeiro
	 * @return List<Button> - array list com os botões do painel
	 */
	public ArrayList<Button> criarArrayBotoes(Button... botoes) {
		ArrayList<Button> listaBotoes = new ArrayList<Button>();
		for (Button botao : botoes) {
			listaBotoes.add(botao);
		}
		return listaBotoes;
	}

	/**
	 * Cria um Array List com todos os rótulos da GUI para utilizar nos métodos
	 * para simpleficação do código,
	 *
	 * @author Flávio Aparecido Ribeiro
	 * @return List<Button> - array list com os rótulos da GUI
	 */
	public ArrayList<Label> criarArrayRotulos(Label... rotulos) {
		ArrayList<Label> listaRotulos = new ArrayList<Label>();
		for (Label rotulo : rotulos) {
			listaRotulos.add(rotulo);
		}
		return listaRotulos;
	}

	/**
	 * Limpa todos os campos do jogo e reseta os dados contidos no painel.
	 * 
	 * @author Flávio Aparecido Ribeiro
	 */
	public void limparCampos(List<Button> listaBotoes, JogoInterface jogo) {
		for (Button botao : listaBotoes) {
			botao.setText("");
		}
		try {
			jogo.resetarPainel();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Executa a ação do botão e verifica se a partida encerrou para que possa
	 * ser resetado o painel.
	 *
	 * @author Flávio Aparecido Ribeiro
	 * @param botao
	 *            - botão clicado
	 * @param x
	 *            - coordenada do botão na horizontal.
	 * @param y
	 *            - coordenada do botão na vertical.
	 */
	public void executarJogada(Button botao, int x, int y,
			JogoInterface jogo, String simbolo, List<Button> listaBotoes) {
		if (botao.getText().equals("")) { // Se a posição ainda esta vazia...
			try {
				// Se o valor de jogadorAtivo for o mesmo desta instancia
				// permite a jogada
				if (jogo.getJogadorAtivo().equals(simbolo)) {
					if (simbolo.equals("O")) {
						botao.setTextFill(Color.RED);
					} else {
						botao.setTextFill(Color.BLUE);
					}
					botao.setText(simbolo); // Atribuí o simbolo ao botao
					jogo.setMatriz(x, y, simbolo);
					jogo.setJogadorAtivo(); // Alterna o jogador ativo
					jogo.verificarVencedor(simbolo);
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			try { // Se houve vencedor ou empate limpa os campos
				if (jogo.getVencedor() != 0) {
					limparCampos(listaBotoes, jogo);
				} else
					try {
						if (jogo.verificarEmpate()) {
							limparCampos(listaBotoes, jogo);
						}
					} catch (RemoteException e) {
						e.printStackTrace();
					}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Atualiza automaticamente os elementos da GUI ao intervalo de 1 segundo.
	 *
	 * @author Flávio Aparecido Ribeiro
	 * @param listaBotoes
	 */
	public void atualizarGUI(List<Button> listaBotoes, JogoInterface jogo,
			List<Label> rotulos) {
		Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1),
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						// Atribuí ao botão o símbolo que está na respectiva
						// posição na matriz
						for (int i = 0; i < 9; i++) {
							for (int j = 0; j < 3; j++) {
								for (int k = 0; k < 3; k++) {
									try {
										listaBotoes.get(i).setText(
												jogo.getMatriz(j, k));
									} catch (RemoteException e) {
										// Quando o servidor ficar fora do ar a
										// primeira exceção ocorre aqui, encerra
										// a aplicação
										e.printStackTrace();
										System.exit(0);
									}
									// Muda a cor do botão de acordo com o
									// símbolo
									if (listaBotoes.get(i).getText()
											.equals("X")) {
										listaBotoes.get(i).setTextFill(
												Color.BLUE);
									} else {
										listaBotoes.get(i).setTextFill(
												Color.RED);
									}
									i++;
								}
							}
						}
						try {
							rotulos.get(0).setText(
									"Vitórias na Rodada: "
											+ jogo.getSessao()
													.getVitoriasJogador1());
							rotulos.get(1)
									.setText(
											"Rodadas Ganhas: "
													+ jogo.getSessao()
															.getRodadasGanhasJogador1());
							rotulos.get(2).setText(
									"Vitórias na Rodada: "
											+ jogo.getSessao()
													.getVitoriasJogador2());
							rotulos.get(3)
									.setText(
											"Rodadas Ganhas: "
													+ jogo.getSessao()
															.getRodadasGanhasJogador2());
							rotulos.get(4)
									.setText(
											"Empates: "
													+ jogo.getSessao()
															.getEmpates());
						} catch (RemoteException e) {
							e.printStackTrace();
						}

					}
				}));
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
	}
}

package br.com.flavioaprb.jogodavelha;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Contém toda a lógica do jogo.
 * 
 * @author Flávio Aparecido Ribeiro
 *
 */
public class JogoServidor extends UnicastRemoteObject implements JogoInterface {

	private static ServidorGui gui;
	private String jogadorAtivo;
	private int vencedor;
	private int clientesConectados;
	private Sessao sessao;
	private String[][] matriz;

	public JogoServidor() throws RemoteException {
		this.gui = new ServidorGui();
		this.clientesConectados = 0;
		this.sessao = new Sessao();
		this.jogadorAtivo = "X";
		this.matriz = new String[3][3];
		// Inicializa a matriz com um valor de string representando que esta
		// vazia.
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = "";
			}
		}
	}

	/**
	 * Alterna entre os jogadores.
	 * 
	 * @author Flavio
	 */
	@Override
	public void setJogadorAtivo() {
		if (jogadorAtivo == "X") {
			jogadorAtivo = "O";
		} else if (jogadorAtivo == "O") {
			jogadorAtivo = "X";
		}
	}

	@Override
	public String getJogadorAtivo() {
		return jogadorAtivo;
	}

	@Override
	public int getVencedor() {
		return vencedor;
	}

	@Override
	public void setMatriz(int x, int y, String simbolo) {
		matriz[x][y] = simbolo;
	}

	@Override
	public String getMatriz(int x, int y) {
		return matriz[x][y];
	}

	@Override
	public Sessao getSessao() {
		return sessao;
	}

	@Override
	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	@Override
	public int getId() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void desconectarCliente() {
		clientesConectados--;
		gui.imprimirMensagem("Jogador se desconectou");
	}

	@Override
	public void incrementarCliente() {
		clientesConectados++;
		gui.imprimirMensagem("Jogador " + clientesConectados + " conectado");
	}

	@Override
	public int getClientesConectados() {
		return clientesConectados;
	}

	@Override
	public String getSimbolo() {
		if (clientesConectados == 1) {
			return "X";
		} else if (clientesConectados == 2) {
			return "O";
		}
		return null;
	}

	/**
	 * Verifica se há vencedor na partida. O método possuí todas as verificações
	 * de combinações possíveis para uma vitória que são 3 linhas, 3 colunas e 2
	 * diagonais.
	 * 
	 * @author Flavio
	 * @param jogador
	 *            Contém o símbolo do jogador
	 */
	@Override
	public void verificarVencedor(String jogador) {
		if (matriz[0][0].equals(jogador) && matriz[0][1].equals(jogador)
				&& matriz[0][2].equals(jogador)) {
			definirVencedor(jogador);
		} else if (matriz[1][0].equals(jogador) && matriz[1][1].equals(jogador)
				&& matriz[1][2].equals(jogador)) {
			definirVencedor(jogador);
		} else if (matriz[2][0].equals(jogador) && matriz[2][1].equals(jogador)
				&& matriz[2][2].equals(jogador)) {
			definirVencedor(jogador);
		} else if (matriz[0][0].equals(jogador) && matriz[1][0].equals(jogador)
				&& matriz[2][0].equals(jogador)) {
			definirVencedor(jogador);
		} else if (matriz[0][1].equals(jogador) && matriz[1][1].equals(jogador)
				&& matriz[2][1].equals(jogador)) {
			definirVencedor(jogador);
		} else if (matriz[0][2].equals(jogador) && matriz[1][2].equals(jogador)
				&& matriz[2][2].equals(jogador)) {
			definirVencedor(jogador);
		} else if (matriz[0][0].equals(jogador) && matriz[1][1].equals(jogador)
				&& matriz[2][2].equals(jogador)) {
			definirVencedor(jogador);
		} else if (matriz[2][0].equals(jogador) && matriz[1][1].equals(jogador)
				&& matriz[0][2].equals(jogador)) {
			definirVencedor(jogador);
		}
	}

	/**
	 * Verifica se houve empate na partida. Se todos as posições da matriz
	 * estiverem preenchidas com um valor que não seja o valor utilizado na
	 * inicialização destas, e ainda não tiverem vencedores na partida, um
	 * empate é computado.
	 * 
	 * @author Flávio Aparecido Ribeiro
	 * @return Se houve empate ou não.
	 */
	@Override
	public boolean verificarEmpate() {
		int vazia = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j] != "") {
					vazia++;
				}
			}
		}
		if (vazia == 9 && vencedor == 0) {
			sessao.somarEmpates();
			gui.imprimirMensagem("Empatou.");
			return true;
		}
		return false;
	}

	/**
	 * Define o vencedor da partida e computa os dados.
	 * 
	 * @author Flávio Aparecido Ribeiro
	 * @param jogador
	 */
	private void definirVencedor(String jogador) {
		if (jogador.equals("X")) {
			gui.imprimirMensagem("Jogador 1 venceu!");
			vencedor = 1;
			sessao.somarVitoriasJogador1();
			if (sessao.getVitoriasJogador1() == 3) {
				sessao.setVitoriasJogador1(0);
				sessao.setVitoriasJogador2(0);
				sessao.somarRodadasGanhasJogador1();
			}
		} else {
			vencedor = 2;
			gui.imprimirMensagem("Jogador 2 venceu!");
			sessao.somarVitoriasJogador2();
			if (sessao.getVitoriasJogador2() == 3) {
				sessao.setVitoriasJogador1(0);
				sessao.setVitoriasJogador2(0);
				sessao.somarRodadasGanhasJogador2();
			}
		}
	}

	/**
	 * Reseta a matriz e os dados do jogo.
	 * 
	 * @author Flávio Aparecido Ribeiro
	 */
	@Override
	public void resetarPainel() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				matriz[i][j] = "";
			}
		}
		vencedor = 0;
		jogadorAtivo = "X";
		gui.imprimirMensagem("Painel resetado.");
	}

	/**
	 * Inicia a aplicação servidor.
	 * 
	 * @author Flávio Aparecido Ribeiro
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("Registro do servidor criado.");
		} catch (RemoteException e) {
			System.out.println("Registro do servidor já existente.");
			e.printStackTrace();
		}
		JogoServidor servidor = new JogoServidor();
		Naming.rebind("//localhost/RmiServer", servidor);
		gui.setVisible(true);
		gui.imprimirMensagem("RmiServer registrado.");
		gui.imprimirMensagem("Servidor de JogoInterface Iniciado.");
	}
}

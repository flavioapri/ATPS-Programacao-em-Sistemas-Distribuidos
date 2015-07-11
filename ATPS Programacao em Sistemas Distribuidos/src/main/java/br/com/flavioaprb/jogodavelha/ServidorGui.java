package br.com.flavioaprb.jogodavelha;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Exibe uma GUI com as informa��es do servidor.
 * 
 * @author Fl�vio Aparecido Ribeiro
 *
 */
public class ServidorGui extends Frame {
	private TextArea textArea = new TextArea();

	public ServidorGui() {
		super("Servidor JogoInterface Da Velha 1.0");
		setSize(400, 500);
		add(textArea);
		textArea.setEditable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}

	/**
	 * Imprime mensagens do servidor na GUI
	 *
	 * @author Fl�vio Aparecido Ribeiro
	 * @param mensagem
	 */
	public void imprimirMensagem(String mensagem) {
		synchronized (this) {
			textArea.append(mensagem + "\r\n");
		}
	}
}

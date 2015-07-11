package br.com.flavioaprb.jogodavelha;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

	public void imprimirMensagem(String mensagem) {
		synchronized (this) {
			textArea.append(mensagem + "\r\n");
		}
	}
}

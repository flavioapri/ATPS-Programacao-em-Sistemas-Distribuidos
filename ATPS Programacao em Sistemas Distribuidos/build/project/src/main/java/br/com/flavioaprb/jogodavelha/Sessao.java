package br.com.flavioaprb.jogodavelha;

import java.io.Serializable;

/**
 * Contém os dados do jogo.
 * 
 * @author Flávio Aparecido Ribeiro
 *
 */
public class Sessao implements Serializable {

	private int rodadasGanhasJogador1;
	private int rodadasGanhasJogador2;
	private int vitoriasJogador1;
	private int vitoriasJogador2;
	private int empates;

	public int getRodadasGanhasJogador1() {
		return rodadasGanhasJogador1;
	}

	public void somarRodadasGanhasJogador1() {
		this.rodadasGanhasJogador1++;
	}

	public int getRodadasGanhasJogador2() {
		return rodadasGanhasJogador2;
	}

	public void somarRodadasGanhasJogador2() {
		this.rodadasGanhasJogador2++;
	}

	public int getVitoriasJogador1() {
		return vitoriasJogador1;
	}

	public void somarVitoriasJogador1() {
		this.vitoriasJogador1++;
	}

	public int getVitoriasJogador2() {
		return vitoriasJogador2;
	}

	public void somarVitoriasJogador2() {
		this.vitoriasJogador2++;
	}

	public void setRodadasGanhasJogador1(int rodadasGanhasJogador1) {
		this.rodadasGanhasJogador1 = rodadasGanhasJogador1;
	}

	public void setRodadasGanhasJogador2(int rodadasGanhasJogador2) {
		this.rodadasGanhasJogador2 = rodadasGanhasJogador2;
	}

	public void setVitoriasJogador1(int vitoriasJogador1) {
		this.vitoriasJogador1 = vitoriasJogador1;
	}

	public void setVitoriasJogador2(int vitoriasJogador2) {
		this.vitoriasJogador2 = vitoriasJogador2;
	}

	public int getEmpates() {
		return empates;
	}

	public void somarEmpates() {
		this.empates++;
	}

	public void setEmpates(int empates) {
		this.empates = empates;
	}

}

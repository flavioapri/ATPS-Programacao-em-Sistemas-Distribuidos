package br.com.flavioaprb.jogodavelha;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface JogoInterface extends Remote {

	public void setJogadorAtivo() throws RemoteException;

	public String getJogadorAtivo() throws RemoteException;

	public void verificarVencedor(String jogador) throws RemoteException;

	public boolean verificarEmpate() throws RemoteException;

	public void setMatriz(int x, int y, String simbolo) throws RemoteException;

	public String getMatriz(int x, int y) throws RemoteException;

	public void resetarPainel() throws RemoteException;

	public int getId() throws RemoteException;

	public void desconectarCliente() throws RemoteException;

	public void setSessao(Sessao sessao) throws RemoteException;

	public Sessao getSessao() throws RemoteException;

	public int getVencedor() throws RemoteException;

	public void incrementarCliente() throws RemoteException;

	public int getClientesConectados() throws RemoteException;

	public String getSimbolo() throws RemoteException;

}

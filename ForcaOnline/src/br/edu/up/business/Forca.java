package br.edu.up.business;

/**
 * Interface que determina um comportamento para o jogo de forca.
 * @author Daniel Gorski
 */
public interface Forca {
	
	/**
	 * Sorteia uma palavra.
	 * @param file
	 */
	void sortearpalavra(String file);
	
	/**
	 * Mostra a dica para revelar a palavra.
	 * @return
	 */
	String getDica();
	
	/**
	 * Tenta uma letra.
	 * @param letra 
	 * @return codigo do erro.
	 */
	String tentarletra(String letra);
	
	/**
	 * Revela o quando da palavra ja foi advinhada.
	 * @param mostraToda
	 * @return
	 */
	String revelarPalavra(boolean mostraToda); 
	
	/**
	 * Indica se o jogador perdeu.
	 * @return true se perdeu
	 */
	boolean isPerdeu();
	
	/**
	 * Indica se o jogador ganhou 
	 * @return true se ganhou.
	 */
	boolean isVenceu();
}

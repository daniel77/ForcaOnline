package br.edu.up.util;

/**
 * Excecao padrao do Forca Online. 
 * @author Daniel Gorski
 */
public class ForcaException extends Exception {

	private static final long serialVersionUID = 1L;

	public ForcaException(String msg, Class<?> c)
	{
		super("[ERRO] em : " + c.getName() + " - Mensage :"+ msg);
		System.out.println("[ERRO] em : " + c.getName() + " - Mensage :"+ msg);
	}
}

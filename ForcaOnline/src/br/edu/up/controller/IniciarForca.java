package br.edu.up.controller;

import java.io.IOException;

import br.edu.up.business.Forca;
import br.edu.up.business.ForcaImpl;
import br.edu.up.controller.base.BaseControllerServelet;
import br.edu.up.util.ForcaException;

/**
 * Controlador que inicia um jogo de forca e coloca na seção do usuario.
 * 
 * @author Daniel Gorski
 * 
 */
public class IniciarForca extends BaseControllerServelet
{
	private static final long serialVersionUID = 1L;

	private Forca jogo;

	protected void efetuarAcao()throws ForcaException
	{
		try
		{
			jogo = new ForcaImpl();
			//jogo.sortearpalavra(getServletContext().getRealPath("/WEB-INF/palavras.txt"));
			jogo.sortearpalavra();
			String dica = jogo.getDica();
			getRequest().getSession().setAttribute("forca", jogo);

			getResponse().getWriter().print("{ \"message\" : \"" + dica + "\" } ");
			
			System.out.println("[LOG]Iniciando um jogo vindo de : " + getRequest().getRemoteAddr() + " - Usando : " +  getRequest().getHeader("user-agent"));
		} catch (IOException e)
		{
			throw new ForcaException(e.getMessage(), IniciarForca.class);
		}
	}
}

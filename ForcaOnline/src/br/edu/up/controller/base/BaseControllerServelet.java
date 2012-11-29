package br.edu.up.controller.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.up.util.ForcaException;

/**
 * Controlador Base. Integração Server - Controle
 * @author Daniel Gorski
 *
 */
public abstract class BaseControllerServelet extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	/**
	 * Método que implementa a ação. 
	 * @throws ForcaException
	 */
	protected abstract void efetuarAcao() throws ForcaException;

	private HttpServletRequest request;

	private HttpServletResponse response;

	public HttpServletRequest getRequest()
	{
		return request;
	}

	public HttpServletResponse getResponse()
	{
		return response;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.request = request;
		this.response = response;
		try
		{
			efetuarAcao();
		} catch (ForcaException e)
		{
			getResponse().getWriter().print("{ \"message\" : \" erro: " + e.getMessage() + "\" } ");
		}
	}

}

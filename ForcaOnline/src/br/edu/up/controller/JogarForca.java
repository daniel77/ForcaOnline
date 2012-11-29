package br.edu.up.controller;

import static br.edu.up.util.Util.getStringFromJasonObj;

import java.io.BufferedReader;
import java.io.IOException;

import br.edu.up.business.Forca;
import br.edu.up.controller.base.BaseControllerServelet;
import br.edu.up.util.ForcaException;

/**
 * Controlador reponsavel pelas enviar as jogadas para a implemtação da forca.
 * 
 * @author Daniel Gorski
 * 
 */
public class JogarForca extends BaseControllerServelet
{
	private static final long serialVersionUID = 1L;

	protected void efetuarAcao()throws ForcaException
	{

		try
		{
			StringBuilder sb = new StringBuilder();
			BufferedReader br = getRequest().getReader();
			String str;

			while ((str = br.readLine()) != null)
			{
				sb.append(str);
			}
			;

			String content = getStringFromJasonObj(sb.toString(), "letra");

			Forca jogo = (Forca) getRequest().getSession().getAttribute("forca");

			String result = jogo.tentarletra(content);

			if (jogo.isPerdeu())
			{
				getResponse().getWriter().print("{ \"message\" : \" PERDEU: A PALAVRA ERA : " + jogo.revelarPalavra(true) + "\", \"result\" : \"" + result + "\" } ");
				return;
			}

			if (jogo.isVenceu())
			{
				getResponse().getWriter().print("{ \"message\" : \" VENCEU: A PALAVRA ERA : " + jogo.revelarPalavra(true) + "\" } ");
				return;
			}

			getResponse().getWriter().print("{ \"message\" : \"" + jogo.revelarPalavra(false) + "\", \"result\" : \"" + result + "\" } ");

		} catch (IOException e)
		{
			throw new ForcaException(e.getMessage(), JogarForca.class);
		}
	}

}

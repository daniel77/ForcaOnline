package br.edu.up.controller;

import static br.edu.up.util.Util.getStringFromJasonObj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.edu.up.business.PalavraForca;
import br.edu.up.business.PalavraForcaImpl;
import br.edu.up.business.bo.Palavra;
import br.edu.up.controller.base.BaseControllerServelet;
import br.edu.up.util.ForcaException;

/**
 * Controlador para cadastro de palavra
 * 
 * @author Daniel Gorski 
 * 
 */
public class CadastrarPalavra extends BaseControllerServelet
{

	private static final long serialVersionUID = 2003858803921255456L;

	protected void efetuarAcao() throws ForcaException
	{
		BufferedWriter arquivo;
		String valores = "";
		try
		{

			StringBuilder sb = new StringBuilder();
			BufferedReader br = getRequest().getReader();
			String str;
			while ((str = br.readLine()) != null)
			{
				sb.append(str);
			};

			valores = sb.toString();

			String[] palavrasDicas = valores.split(",");

			String palavra = getStringFromJasonObj(palavrasDicas[0], "palavra");

			if ("".equals(palavra) || palavra == null)
			{
				getResponse().getWriter().print("{ \"message\" : \" erro: a palavra nao pode ser em branco \" } ");
				return;
			}

			String dica = getStringFromJasonObj(palavrasDicas[1], "dica");

			if ("".equals(dica) || dica == null)
			{
				getResponse().getWriter().print("{ \"message\" : \" erro: dica nao pode ser em branco \" } ");
				return;
			}

			String senha = getStringFromJasonObj(palavrasDicas[2], "senha");

			if (!"positivo".equals(senha))
			{
				getResponse().getWriter().print("{ \"message\" : \" erro: senha invalida \" } ");
				return;
			}

			valores = palavra + "!" + dica;
			
			Palavra p = new Palavra();
			p.setDica(dica);
			p.setPalavra(palavra);
			
			PalavraForca pal = new PalavraForcaImpl();
			
			pal.cadastrarPalavra(p);
			
			/* File f = new File(getServletContext().getRealPath("/WEB-INF/palavras.txt"));
			arquivo = new BufferedWriter(new FileWriter(f, true));
			arquivo.write(valores);
			arquivo.newLine();
			arquivo.flush();
			arquivo.close();
			*/
			
			getResponse().getWriter().print("{ \"message\" : \" Ok. Palavra Cadastrada com sucesso. \" } ");
		} catch (IOException erro)
		{
			throw new ForcaException(erro.getMessage(), CadastrarPalavra.class);
		} catch (Exception e)
		{
			throw new ForcaException(e.getMessage(), CadastrarPalavra.class);

		}

	}

}

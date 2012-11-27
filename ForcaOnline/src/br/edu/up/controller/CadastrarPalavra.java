package br.edu.up.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastrarPalavra extends HttpServlet {

	private static final long serialVersionUID = 2003858803921255456L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BufferedWriter arquivo;
		String valores = "";
		try {

			StringBuilder sb = new StringBuilder();
			BufferedReader br = request.getReader();
			String str;
			while ((str = br.readLine()) != null) {
				sb.append(str);
			};

			valores = sb.toString();

			String[] palavrasDicas = valores.split(",");

			String palavra = palavrasDicas[0].toString().replace("\"", "");
			palavra = palavra.replace("{", "");
			palavra = palavra.replace(" }", "");
			palavra = palavra.replace(" palavra : ", "").trim();

			if("".equals(palavra) || palavra == null)
			{
				response.getWriter().print("{ \"message\" : \" erro: a palavra nao pode ser em branco \" } ");
				return;
			}
			
			String dica = palavrasDicas[1].toString().replace("\"", "");
			dica = dica.replace("{", "");
			dica = dica.replace(" }", "");
			dica = dica.replace(" dica : ", "").trim();
			
			if("".equals(dica) || dica == null)
			{
				response.getWriter().print("{ \"message\" : \" erro: dica nao pode ser em branco \" } ");
				return;
			}

			String senha = palavrasDicas[2].toString().replace("\"", "");
			senha = senha.replace("{", "");
			senha = senha.replace(" }", "");
			senha = senha.replace(" senha : ", "").trim();

			if (!"positivo".equals(senha)) {
				response.getWriter().print("{ \"message\" : \" erro: senha invalida \" } ");
				return;
			}

			valores = palavra + "!" + dica;

			File f = new File(getServletContext().getRealPath("/WEB-INF/palavras.txt"));
			arquivo = new BufferedWriter(new FileWriter(f, true));
			arquivo.write(valores);
			arquivo.newLine();
			arquivo.flush();
			arquivo.close();

		} catch (IOException erro) {
	    	response.getWriter().print("{ \"message\" : \" erro: "+ erro.getMessage() +"\" } ");
	    	return;

		} catch (Exception e) {
			response.getWriter().print("{ \"message\" : \" erro: "+ e.getMessage() +"\" } ");
			return;
		}
		response.getWriter().print("{ \"message\" : \" Ok. Palavra Cadastrada com sucesso. \" } ");
	}

}

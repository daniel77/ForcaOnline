package br.edu.up.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.up.business.Forca;
import br.edu.up.business.ForcaImpl;

public class IniciarForca extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Forca jogo;
	   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        jogo = new ForcaImpl();
        jogo.sortearpalavra(getServletContext().getRealPath( "/WEB-INF/palavras.txt" ));
        String dica = jogo.getDica();
        request.getSession().setAttribute("forca", jogo);
        response.getWriter().print("{ \"message\" : \""+ dica +"\" } ");
	}
  
}

package br.edu.up.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.up.business.Forca;

public class JogarForca extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    StringBuilder sb = new StringBuilder();
	    BufferedReader br = request.getReader();
	    String str;
	    while( (str = br.readLine()) != null ){
	        sb.append(str);
	    };
		
	    String content = sb.toString().replace("\"","");
	    content = content.replace("{","");
	    content = content.replace(" }","");
	    content = content.replace(" letra : ","");
	    
	    Forca jogo = (Forca)request.getSession().getAttribute("forca");
	    
	    String result = jogo.tentarletra(content);
	    

	    if(jogo.isPerdeu())
	    {
	    	 response.getWriter().print("{ \"message\" : \" PERDEU: A PALAVRA ERA : "+ jogo.revelarPalavra(true) +"\", \"result\" : \""+result +"\" } ");
	    	 return;
	    }
	    
	    if(jogo.isVenceu())
	    {
	    	response.getWriter().print("{ \"message\" : \" VENCEU: A PALAVRA ERA : "+ jogo.revelarPalavra(true) +"\" } ");
	    	return;
	    }
	    
	    response.getWriter().print("{ \"message\" : \""+ jogo.revelarPalavra(false) +"\", \"result\" : \""+result +"\" } ");
	}

}

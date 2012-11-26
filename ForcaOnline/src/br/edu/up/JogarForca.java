package br.edu.up;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

@WebServlet("/JogarForca")
public class JogarForca extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public JogarForca() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("JogarForca.doGet()");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("JogarForca.doPost()");
		
		
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
	    
	    ForcaCore jogo = (ForcaCore)request.getSession().getAttribute("forca");
	    
	    jogo.tentarletra(content);
	    
	    System.out.println(content.trim());
	    System.out.println("[PERDEU]" + jogo.isPerdeu());
	    System.out.println("[VENCEU]" + jogo.isVenceu());
	    
	    if(jogo.isPerdeu())
	    {
	    	 response.getWriter().print("{ \"message\" : \" PERDEU: A PALAVRA ERA : "+ jogo.desenharpalavra(true) +"\" } ");
	    	 return;
	    }
	    
	    if(jogo.isVenceu())
	    {
	    	response.getWriter().print("{ \"message\" : \" VENCEU: A PALAVRA ERA : "+ jogo.desenharpalavra(true) +"\" } ");
	    	return;
	    }
	    
	    response.getWriter().print("{ \"message\" : \""+ jogo.desenharpalavra(false) +"\" } ");
	}

}

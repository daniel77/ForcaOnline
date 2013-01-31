package br.edu.up.business.bo;

import java.io.Serializable;

public class Palavra implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String palavra;
	private String dica;
	public String getPalavra() {
		return palavra;
	}
	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}
	public String getDica() {
		return dica;
	}
	public void setDica(String dica) {
		this.dica = dica;
	}
	
	

}

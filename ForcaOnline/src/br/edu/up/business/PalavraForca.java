package br.edu.up.business;

import java.util.List;

import br.edu.up.business.bo.Palavra;

public interface PalavraForca {

	void cadastrarPalavra(Palavra p)throws Exception ;
	List<Palavra> listar()throws Exception ;
	
}

package br.edu.up.business;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.edu.up.business.bo.Palavra;
import br.edu.up.business.database.ForcaConnection;

public class PalavraForcaImpl implements PalavraForca {

	@Override
	public void cadastrarPalavra(Palavra p) throws Exception {
		
		String SQL = "insert into PALAVRA(PALAVRA, DICA) values ('"+
		p.getPalavra()+"' , '"+ p.getDica()+"')";
        
		ForcaConnection.getInstance().executeCommand(SQL);
		ForcaConnection.getInstance().finalize();

	}

	@Override
	public List<Palavra> listar() throws Exception {
		String SQL = "SELECT * FROM PALAVRA";
		
		List<Palavra> lista = new ArrayList<Palavra>();
		
        ResultSet rs = 	ForcaConnection.getInstance().executeQuery(SQL);
        // Iterate through the data in the result set and display it.
        while (rs.next()) {
        	Palavra p = new Palavra();
        	p.setPalavra(rs.getString(2));
        	p.setDica(rs.getString(3));
        	lista.add(p);
           // System.out.println(rs.getString(2) + " " + rs.getString(3));
        }
        ForcaConnection.getInstance().finalize();
        
        
		
		return lista;
	}

}

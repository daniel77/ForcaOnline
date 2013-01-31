package br.edu.up.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.edu.up.business.bo.Palavra;

/**
 * 
 * @author Daniel Gorski
 *
 */
public class ForcaImpl implements Serializable, Forca {

	private static final long serialVersionUID = 7653430318396138287L;
	
	private List<String> dicionarioPalavras = new ArrayList<String>();
	private List<String> dicasPalavras = new ArrayList<String>();
	private List<String> tentativas = new ArrayList<String>();
	
	private List<Palavra> dicionarioPalavrasSQL = new ArrayList<Palavra>();
	
	
	private String palavraSorteada = "";
	private int CodPalavraSorteada;

	private boolean venceu;
	private boolean perdeu;

	int MAXTENTATIVAS = 6;
	int numTentativas = 0;

	public void sortearpalavra() {
		try {
			

			
			dicionarioPalavrasSQL = new PalavraForcaImpl().listar();
					

			int TotalPalavras = dicionarioPalavrasSQL.size();
			
			CodPalavraSorteada = (int) (Math.random() * (TotalPalavras));
			palavraSorteada = ((Palavra) dicionarioPalavrasSQL
					.get(CodPalavraSorteada)).getPalavra();
			
			palavraSorteada = palavraSorteada.toUpperCase();

		} catch (Exception ex) {
			System.out.println("Erro: " + ex.toString());
		}
		;
	}
	
	@Deprecated
	public void sortearpalavra(String file) {
		try {
			File Arquivo = new File(file);

			FileReader leitor = new FileReader(Arquivo);
			BufferedReader leitorBuf = new BufferedReader(leitor);

			String linha = null;

			while ((linha = leitorBuf.readLine()) != null) {
				String colunas[] = linha.split("!");
				dicionarioPalavras.add(colunas[0]);
				dicasPalavras.add(colunas[1]);
			}

			leitorBuf.close();

			int TotalPalavras = dicionarioPalavras.size();
			CodPalavraSorteada = (int) (Math.random() * (TotalPalavras));
			palavraSorteada = (String) dicionarioPalavras
					.get(CodPalavraSorteada);
			palavraSorteada = palavraSorteada.toUpperCase();

		} catch (Exception ex) {
			System.out.println("Erro: " + ex.toString());
		}
		;
	}

	public String getDica() {
		return ((Palavra) dicionarioPalavrasSQL.get(CodPalavraSorteada)).getDica();
	}

	public String tentarletra(String letra) {
		letra = letra.toUpperCase();
		if (tentativas.contains(letra)) {
			// TODO Mostrar mensagem de que já foi tentada esta letra?
		} else {

			tentativas.add(letra);
			if (palavraSorteada.contains(letra)) {
				// Acertou

				revelarPalavra(false);
			} else {
				numTentativas = numTentativas + 1;

				switch (numTentativas) {
				case (1): {
					return "0";
				}
				case (2): {
					return "1";
				}
				case (3): {
					return "2";
				}
				case (4): {
					return "3";
				}
				case (5): {
					return "4";
				}
				case (6): {
					revelarPalavra(true);
					perdeu = true;
					return "5";
				}
				}
			}
		}
		return "";
	}

	public String revelarPalavra(boolean mostraToda) {
		String tempTexto = new String();
		boolean faltauma = false;
		for (int n = 1; n <= palavraSorteada.length(); n++) {
			if ((mostraToda)
					|| (tentativas
							.contains(palavraSorteada.substring(n - 1, n)))) {
				tempTexto = tempTexto + palavraSorteada.substring(n - 1, n);
			} else {
				tempTexto = tempTexto + "_";
				faltauma = true;
			}
			tempTexto = tempTexto + " ";
		}

		if (!faltauma) {
			// FimDoJogo=true;
			if ((numTentativas) < 6) {

				venceu = true;

			}
		}

		return tempTexto;
	}

	@Deprecated
	public List<String> getDicionarioPalavras() {
		return dicionarioPalavras;
	}

	public void setDicionarioPalavras(ArrayList<String> dicionarioPalavras) {
		this.dicionarioPalavras = dicionarioPalavras;
	}

	public void setDicasPalavras(ArrayList<String> dicasPalavras) {
		this.dicasPalavras = dicasPalavras;
	}

	public List<String> getTentativas() {
		return tentativas;
	}

	public void setTentativas(ArrayList<String> tentativas) {
		this.tentativas = tentativas;
	}

	public String getPalavraSorteada() {
		return palavraSorteada;
	}

	public void setPalavraSorteada(String palavraSorteada) {
		this.palavraSorteada = palavraSorteada;
	}

	public boolean isVenceu() {
		return venceu;
	}

	public void setVenceu(boolean venceu) {
		this.venceu = venceu;
	}

	public boolean isPerdeu() {
		return perdeu;
	}

	public void setPerdeu(boolean perdeu) {
		this.perdeu = perdeu;
	}

}

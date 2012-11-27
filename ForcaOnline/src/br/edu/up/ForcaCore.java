package br.edu.up;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ForcaCore {

	private ArrayList<String> dicionarioPalavras = new ArrayList<String>();
	private ArrayList<String> dicasPalavras = new ArrayList<String>();
	private ArrayList<String> tentativas = new ArrayList<String>();
	private String palavraSorteada = "";
	private int CodPalavraSorteada;

	private boolean venceu;
	private boolean perdeu; 
	
	int MAXTENTATIVAS = 6;
	int numTentativas = 0;

	public void sortearpalavra() {
		try {
			File Arquivo = new File("C:\\Users\\dgorski\\workspace5\\ForcaOnline\\src\\br\\edu\\up\\palavras.txt");

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
		return (String) dicasPalavras.get(CodPalavraSorteada);
	}

	public static void main(String[] args) {
		ForcaCore forca = new ForcaCore();
		

		System.out.println(("DICA: " + forca.getDica()));

		Scanner in = new Scanner(System.in);

		while (true) {
			forca.tentarletra(in.nextLine());			
		}
		
		

	}

	public void tentarletra(String letra) {
		letra = letra.toUpperCase();
		if (tentativas.contains(letra)) {
			// Mostrar mensagem de que já foi tentada esta letra
		} else {
			// Armazena a letra nova na lista de tentativas.
			tentativas.add(letra);
			if (palavraSorteada.contains(letra)) {
				// Acertou
				System.out.println("tEM");
				desenharpalavra(false);
			} else {
				numTentativas = numTentativas + 1;// vai adicionando o numero de
													// tentativas; se errar,
													// adiciona o desenho
				switch (numTentativas) {
				case (1): {
					System.out.println("0");
					// LabelImagem.setIcon(icon1);
					break;
				}
				case (2): {
					System.out.println("0-");
					// LabelImagem.setIcon(icon2);
					break;
				}
				case (3): {
					System.out.println("0-/");
					// LabelImagem.setIcon(icon3);
					break;
				}
				case (4): {
					// LabelImagem.setIcon(icon4);
					System.out.println("0-<");
					break;
				}
				case (5): {
					System.out.println("0-<P");
					break;
				}
				case (6): {
					System.out.println("0-<B");
					System.out.println("DIE");
					desenharpalavra(true);
					perdeu = true;
					/*
					 * LabelImagem.setIcon(icon6); FimDoJogo=true;
					 *  FimDoJogo=true; jogo_novo.pack();
					 * jogo_novo.setLocationRelativeTo(null);
					 * jogo_novo.setVisible(true);
					 */
					///System.exit(0);
					break;
				}
				}
			}
		}
	}

	public String desenharpalavra(boolean MostrarTodaAPalavra) {
		String tempTexto = new String();
		boolean faltauma = false;
		for (int n = 1; n <= palavraSorteada.length(); n++) {// pega o tamanho
																// da palavra e
																// cria os
																// tracinhos
			if ((MostrarTodaAPalavra)
					|| (tentativas
							.contains(palavraSorteada.substring(n - 1, n)))) {
				tempTexto = tempTexto + palavraSorteada.substring(n - 1, n);
			} else {
				tempTexto = tempTexto + "_";
				faltauma = true;
			}
			tempTexto = tempTexto + " ";
		}

		//System.out.println(tempTexto);
	
		// PalavraForca.setText(tempTexto); ///TODO

		if (!faltauma) {
			// FimDoJogo=true;
			if ((numTentativas) < 6) {// verifica se o numero de tentativas é
										// menor que 6; se for, o usuario ganhou
				
				venceu = true;
				
				/*
				 * joga_nov.pack();//abre a mensagem que pede se o usuário quer
				 * jogar novamente joga_nov.setLocationRelativeTo(null);
				 * joga_nov.setVisible(true);
				 */
			}

			// Dá pra mostrar uma outra mensagem aqui.
		}
		
		return tempTexto;
	}

	public ArrayList<String> getDicionarioPalavras() {
		return dicionarioPalavras;
	}

	public void setDicionarioPalavras(ArrayList<String> dicionarioPalavras) {
		this.dicionarioPalavras = dicionarioPalavras;
	}

	public ArrayList<String> getDicasPalavras() {
		return dicasPalavras;
	}

	public void setDicasPalavras(ArrayList<String> dicasPalavras) {
		this.dicasPalavras = dicasPalavras;
	}

	public ArrayList<String> getTentativas() {
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

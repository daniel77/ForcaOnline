package br.edu.up.util;

public abstract class Util {

	public static String getStringFromJasonObj(String value, String param) {
		String dica = value.toString().replace("\"", "");
		dica = dica.replace("{", "");
		dica = dica.replace(" }", "");
		dica = dica.replace(" "+ param +" : ", "").trim();
		return dica;
	}
	
}

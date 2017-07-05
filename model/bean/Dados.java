package model.bean;

import java.util.regex.Pattern;

public class Dados {
	public boolean validaCampo(String coluna, String valor) {
		boolean invalido = false;
		Pattern regex = null;
		
		final int ano = 2016;
		final int mes = 12;
		final int dia = 31;
		
		String[] valorSeparado;
		
		switch(coluna) {
			case "Usuário":
				if(valor.length() < 1 || valor.length() > 25) {
					invalido = true;
				} else {
					regex = Pattern.compile("[^a-zA-Z0-9_.]");
				}
			break;
			
			case "Senha":
				if(valor.length() < 6 || valor.length() > 25) {
					invalido = true;
				}
			break;
			
			case "E-mail":
				if(valor.length() < 8) {
					invalido = true;
				}
			break;
			
			case "Nome":
				if(valor.length() < 3 || valor.length() > 30) {
					invalido = true;
				} else {
					regex = Pattern.compile("[^a-zA-Zà-ú .']");
				}
			break;
			
			case "Nome Entidade":
				if(valor.length() < 3 || valor.length() > 45) {
					invalido = true;
				} else {
					regex = Pattern.compile("[^a-zA-Zà-ú .']");
				}
			break;
			
			case "Sobrenome":
				if(valor.length() < 1 || valor.length() > 50) {
					invalido = true;
				} else {
					regex = Pattern.compile("[^a-zA-Zà-ú .']");
				}
			break;
			
			case "RG":
				if(valor.length() < 5 || valor.length() > 20) {
					invalido = true;
				} else {
					regex = Pattern.compile("[^0-9]");
				}
			break;
			
			case "CPF":
				if(valor.length() < 11 || valor.length() > 20) {
					invalido = true;
				} else {
					regex = Pattern.compile("[^0-9]");
				}
			break;
			
			case "Bairro":
				if(valor.length() < 2 || valor.length() > 45) {
					invalido = true;
				}
			break;
			
			case "CEP":
				if(valor.length() != 8) {
					invalido = true;
				} else {
					regex = Pattern.compile("[^0-9]");
				}
			break;
			
			case "Rua":
				if(valor.length() < 2 || valor.length() > 60) {
					invalido = true;
				}
			break;
			
			case "Número":
				if(valor.length() < 1 || valor.length() > 4) {
					invalido = true;
				} else {
					regex = Pattern.compile("[^0-9a-zA-Z]");
				}
			break;
			
			case "Data de Admissão":
				if(valor.length() > 8 || valor.contains("-")) {
					valorSeparado = valor.split("-");
					
					if(valor.length() > 10 || valorSeparado[0].length() != 4 || valorSeparado[1].length() != 2 || valorSeparado[2].length() != 2 || Integer.valueOf(valorSeparado[0]) > ano || Integer.valueOf(valorSeparado[1]) > mes || Integer.valueOf(valorSeparado[2]) > dia) {
						invalido = true;
					}
				} else {
					invalido = true;
				}
			break;
			
			case "Data de Nascimento":
				if(valor.length() > 8 || valor.contains("-")) {
					valorSeparado = valor.split("-");
					
					if(valor.length() > 10 || valorSeparado[0].length() != 4 || valorSeparado[1].length() != 2 || valorSeparado[2].length() != 2 || Integer.valueOf(valorSeparado[0]) > ano || Integer.valueOf(valorSeparado[1]) > mes || Integer.valueOf(valorSeparado[2]) > dia) {
						invalido = true;
					}
				} else {
					invalido = true;
				}
			break;
			
			case "Data de Abertura":
				if(valor.length() > 8 || valor.contains("-")) {
					valorSeparado = valor.split("-");
					
					if(valor.length() > 10 || valorSeparado[0].length() != 4 || valorSeparado[1].length() != 2 || valorSeparado[2].length() != 2 || Integer.valueOf(valorSeparado[0]) > ano || Integer.valueOf(valorSeparado[1]) > mes || Integer.valueOf(valorSeparado[2]) > dia) {
						invalido = true;
					}
				} else {
					invalido = true;
				}
			break;
			
			case "Hora":
				if(valor.length() != 5 || !valor.contains(":")) {
					invalido = true;
				}
			break;
			
			case "CNPJ":
				if(valor.length() != 18) {
					invalido = true;
				}
			break;
			
			case "Salário":
				if(valor.length() < 3) {
					invalido = true;
				}
			break;
			
			case "CRM":
				if(valor.length() < 7 || valor.length() > 13) {
					invalido = true;
				} else {
					regex = Pattern.compile("[^0-9A-Z/]");
				}
			break;
			
			case "Ativo":
				if(Integer.valueOf(valor) != 0 && Integer.valueOf(valor) != 1) {
					invalido = true;
				}
			break;
			
			case "Temperatura Corporal":
				if(valor.length() < 2 || valor.length() > 6) {
					invalido = true;
				}
			break;
			
			case "Pressão":
				if(valor.length() != 4) {
					invalido = true;
				}
			break;
			
			case "Batimentos Cardíacos":
				if(valor.length() < 2 || valor.length() > 3) {
					invalido = true;
				}
			break;
			
			case "Descrição dos Sintomas":
				if(valor.length() < 5) {
					invalido = true;
				}
			break;
		}
		
		if(!invalido && regex != null) {
			return regex.matcher(valor).find();
		}
		
		return invalido;
	}
}
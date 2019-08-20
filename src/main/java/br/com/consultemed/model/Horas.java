package br.com.consultemed.model;

import lombok.Getter;

public enum Horas {
	OITO("08:00"), 
	NOVE("09:00"), 
	DEZ("10:00"), 
	ONZE("11:00"), 
	DOZE("12:00"), 
	TREZE("13:00"), 
	QUATORZE("14:00"), 
	QUINTE("15:00"), 
	DEZESSEIS("16:00"), 
	DEZESSETE("17:00");
	
	@Getter
	private String descricao;
	
	Horas(String descricao) {
		this.descricao = descricao;
	}
}

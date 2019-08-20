package br.com.consultemed.model;

import lombok.Getter;

public enum Dias {
	DOMINGO("Dom"), 
	SEGUNDA("Seg"),
	TERÇA("Ter"),
	QUARTA("Qua"),
	QUINTA("Qui"),
	SEXTA("Sex"),
	SABADO("Sab");
	
	@Getter
	private String descricao;
	
	Dias(String descricao) {
		this.descricao = descricao;
	}

}

package br.com.consultemed.model;

import lombok.Getter;

public enum StatusAgendamento {
	ATIVA("Ativa"),
	CANCELADA("Cancelada"),
	REALIZADA("Realizada");
	
	@Getter
	private String descricao;
	
	StatusAgendamento(String descricao) {
		this.descricao = descricao;
	}
		
}

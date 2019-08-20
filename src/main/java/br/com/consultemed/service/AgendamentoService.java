package br.com.consultemed.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import br.com.consultemed.dao.AgendamentoDao;
import br.com.consultemed.filter.AgendamentoFilter;
import br.com.consultemed.model.Agendamento;
import br.com.consultemed.model.StatusAgendamento;
import br.com.consultemed.utils.Constantes;

public class AgendamentoService implements Serializable { 
	
	private static final long serialVersionUID = 1L;

	@Inject
	private AgendamentoDao dao;
		
	public void salvar(Agendamento agendamento) throws NegocioException {
		Agendamento ageBanco = dao.buscarPorMedicoDataHora(agendamento);		
		
		if(ageBanco != null && !ageBanco.equals(agendamento))
			throw new NegocioException(Constantes.ERRO_AGENDAMENTO_JA_CADASTRADO);		
				
		if(agendamento.getId() != null) {
			ageBanco = dao.buscaPorId(agendamento.getId());
			if(ageBanco != null) {
				dao.atualiza(agendamento);
			}
		} else {
			dao.adiciona(agendamento);
		}		
	}		
		
	public List<Agendamento> listar(){
		return this.dao.listaTodos();
	}	
	
//	ATIVA OU DESATIVA O AGENDAMENTO DE ACORDO COM O STATUS ATUAL
	public void mudarStatusAgendamento(Agendamento agendamento) throws NegocioException {
		if(agendamento.isDataExpirada())
			throw new NegocioException(Constantes.ERRO_AO_CANCELAR);
		
		if(agendamento.getStatus() == StatusAgendamento.ATIVA)			
			this.dao.cancelarAgendamento(agendamento);
		
		if(agendamento.getStatus() == StatusAgendamento.CANCELADA)			
			this.dao.ativarAgendamento(agendamento);
	}
	
	public void remover(Agendamento agendamento) throws NegocioException {
		if(agendamento.isDataExpirada())
			throw new NegocioException(Constantes.ERRO_AO_EXCLUIR);
		
		try {
			this.dao.remove(agendamento);			
		} catch (PersistenceException e) {
			throw new NegocioException(Constantes.ERRO_AO_EXCLUIR);
		}
	}
	
	public List<Agendamento> buscarFiltrados(AgendamentoFilter filter) {
		return dao.buscarFiltrados(filter);				
	}
		
}

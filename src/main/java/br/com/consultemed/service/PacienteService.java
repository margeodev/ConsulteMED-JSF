package br.com.consultemed.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import br.com.consultemed.dao.PacienteDao;
import br.com.consultemed.model.Paciente;
import br.com.consultemed.utils.Constantes;

public class PacienteService implements Serializable { 
	
	private static final long serialVersionUID = 1L;

	@Inject
	private PacienteDao dao;
		
	public void salvar(Paciente paciente) throws NegocioException {		
		if(paciente.getContatos().isEmpty())
			throw new NegocioException(Constantes.ERRO_ALERTA_CONTATOS_VAZIOS);
		
		Paciente pacienteBanco = dao.buscarPorCpf(paciente.getCpf());

		if(pacienteBanco != null && !pacienteBanco.equals(paciente))
			throw new NegocioException(Constantes.ERRO_EMAIL_JA_CADASTRADO);	
		
		if(paciente.getId() != null) {
			pacienteBanco = dao.buscaPorId(paciente.getId());
			if(pacienteBanco != null) {
				dao.atualiza(paciente);
			}
		} else {
			dao.adiciona(paciente);
		}
		
	}	
	
	public List<Paciente> listar(){
		return this.dao.listaTodos();
	}
		
	public void remover(Paciente paciente) throws NegocioException {
		try {
			this.dao.remove(paciente);			
		} catch (PersistenceException e) {
			throw new NegocioException(Constantes.ERRO_AO_EXCLUIR);
		}
	}
	
	public List<Paciente> buscarPorNome(String nome) {
		return this.dao.buscarPorNome(nome);
	}
}

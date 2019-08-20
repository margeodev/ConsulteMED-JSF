package br.com.consultemed.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import br.com.consultemed.dao.MedicoDao;
import br.com.consultemed.model.Medico;
import br.com.consultemed.utils.Constantes;

public class MedicoService implements Serializable { 
	
	private static final long serialVersionUID = 1L;

	@Inject
	private MedicoDao dao;

	public void salvar(Medico medico) throws NegocioException {
		Medico medicoBanco = dao.buscarPorCrm(medico.getCrm());

		if(medicoBanco != null && !medicoBanco.equals(medico))
			throw new NegocioException(Constantes.ERRO_CRM_JA_CADASTRADO);
		
		if(medico.getId() != null) {
			medicoBanco = dao.buscaPorId(medico.getId());
			if(medicoBanco != null) {
				dao.atualiza(medico);
			}
		} else {
			dao.adiciona(medico);
		}	
		
	}
			
	public List<Medico> listar(){
		return this.dao.listaTodos();
	}	
	
	public void remover(Medico medico) throws NegocioException {
		try {
			this.dao.remove(medico);			
		} catch (PersistenceException e) {
			throw new NegocioException(Constantes.ERRO_AO_EXCLUIR);
		}
	}

	public Medico buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}
}

package br.com.consultemed.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.consultemed.model.Medico;
import br.com.consultemed.tx.Transacional;

@SuppressWarnings("serial")
public class MedicoDao implements Serializable {

	@Inject
	private EntityManager manager;
	
	private DAO<Medico> dao;
	
	@PostConstruct
	private void init() {
		this.dao = new DAO<Medico>(this.manager, Medico.class);
	}
	
//	##### CREATE #####
	@Transacional
	public void adiciona(Medico medico) {
		this.dao.adiciona(medico);		
	}
	
//	##### READ #####
	public List<Medico> listaTodos() {
		return this.dao.listaTodos();
	}

//	##### UPDATE #####
	@Transacional
	public void atualiza(Medico medico) {
		this.dao.atualiza(medico);
	}

//	##### DELETE #####
	@Transacional
	public void remove(Medico medico) {
		this.dao.remove(medico);
	}
	
	
	public Medico buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public Medico buscarPorCrm(String crm) {
		try {
			return manager.createQuery("from Medico where crm = :pCrm", Medico.class)
					.setParameter("pCrm", crm)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} 
	}
	
//	public List<Medico> buscarPorNome(String nome) {
//		return manager.createQuery("from Medico where nome like :pNome", Medico.class)
//				.setParameter("pNome", nome + "%").getResultList();
//	}

}

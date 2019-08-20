package br.com.consultemed.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.consultemed.model.Grupo;

@SuppressWarnings("serial")
public class GrupoDao implements Serializable {

	@Inject
	private EntityManager manager;
	
	private DAO<Grupo> dao;
	
	@PostConstruct
	private void init() {
		this.dao = new DAO<Grupo>(this.manager, Grupo.class);
	}

	
//	##### READ #####
	public List<Grupo> listaTodos() {
		return this.dao.listaTodos();
	}

	public Grupo buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}
	
	public Grupo buscarPorNome(String nome) {
		try {
			return manager.createQuery("from Grupo where nome = :pNome", Grupo.class)
					.setParameter("pNome", nome)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} 
	}

}

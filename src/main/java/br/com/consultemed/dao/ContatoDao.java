package br.com.consultemed.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.consultemed.model.Contato;

@SuppressWarnings("serial")
public class ContatoDao implements Serializable {

	@Inject
	private EntityManager manager;
	
	private DAO<Contato> dao;
	
	@PostConstruct
	private void init() {
		this.dao = new DAO<Contato>(this.manager, Contato.class);
	}
	
//	##### CREATE #####
	public void adiciona(Contato contato) {
		this.dao.adiciona(contato);		
	}
	
//	##### READ #####
	public List<Contato> listaTodos() {
		return this.dao.listaTodos();
	}

//	##### UPDATE #####
	public void atualiza(Contato contato) {
		this.dao.atualiza(contato);
	}

//	##### DELETE #####
	public void remove(Contato contato) {
		this.dao.remove(contato);
	}
		
}

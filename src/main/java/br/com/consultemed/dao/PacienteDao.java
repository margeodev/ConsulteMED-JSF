package br.com.consultemed.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.consultemed.model.Paciente;
import br.com.consultemed.tx.Transacional;

@SuppressWarnings("serial")
public class PacienteDao implements Serializable {

	@Inject
	private EntityManager manager;

	private DAO<Paciente> dao;

	@PostConstruct
	private void init() {
		this.dao = new DAO<Paciente>(this.manager, Paciente.class);
	}

//	##### CREATE #####
	@Transacional
	public void adiciona(Paciente paciente) {
		this.dao.adiciona(paciente);
	}

//	##### READ #####
	public List<Paciente> listaTodos() {
		return this.dao.listaTodos();
	}

//	##### UPDATE #####
	@Transacional
	public void atualiza(Paciente paciente) {
		this.dao.atualiza(paciente);
	}

//	##### DELETE #####
	@Transacional
	public void remove(Paciente paciente) {
		this.dao.remove(paciente);
	}

	public Paciente buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public Paciente buscarPorCpf(String cpf) {
		try {
			return manager.createQuery("from Paciente where cpf = :pCpf", Paciente.class).setParameter("pCpf", cpf)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Paciente> buscarPorNome(String nome) {
		return manager.createQuery("from Paciente where nome like :pNome", Paciente.class)
				.setParameter("pNome", nome + "%").getResultList();
	}
}

package br.com.consultemed.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import br.com.consultemed.filter.AgendamentoFilter;
import br.com.consultemed.model.Agendamento;
import br.com.consultemed.model.Horas;
import br.com.consultemed.model.Medico;
import br.com.consultemed.model.StatusAgendamento;
import br.com.consultemed.tx.Transacional;

public class AgendamentoDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;
	
	private DAO<Agendamento> dao;
	
	@PostConstruct
	private void init() {
		this.dao = new DAO<Agendamento>(this.manager, Agendamento.class);
	}
	
//	##### CREATE #####
	@Transacional
	public void adiciona(Agendamento agendamento) {
		this.dao.adiciona(agendamento);		
	}
	
//	##### READ #####
	public List<Agendamento> listaTodos() {
		return this.dao.listaTodos();
	}

//	##### UPDATE #####
	@Transacional
	public void atualiza(Agendamento agendamento) {
		this.dao.atualiza(agendamento);
	}

//	##### DELETE #####
	@Transacional
	public void remove(Agendamento agendamento) {
		this.dao.remove(agendamento);
	}
	
	
	public Agendamento buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public Agendamento buscarPorMedicoDataHora(Agendamento agendamento) {
		Medico medico = agendamento.getMedico();
		Horas hora = agendamento.getHora();
		Date data = agendamento.getDataAtendimento();
		try {
			return manager.createQuery("from Agendamento where medico = :pMedico "
					+ "and hora = :pHora and dataAtendimento = :pdataAtendimento", Agendamento.class)
					.setParameter("pMedico", medico)
					.setParameter("pHora", hora)
					.setParameter("pdataAtendimento", data)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} 
	}
	
	@Transacional
	public void cancelarAgendamento(Agendamento agendamento) {
		Agendamento agendamentoBanco = this.buscaPorId(agendamento.getId());
		agendamentoBanco.setStatus(StatusAgendamento.CANCELADA);
		this.atualiza(agendamentoBanco);
	}

	@Transacional
	public void ativarAgendamento(Agendamento agendamento) {
		Agendamento agendamentoBanco = this.buscaPorId(agendamento.getId());
		agendamentoBanco.setStatus(StatusAgendamento.ATIVA);
		this.atualiza(agendamentoBanco);
	}
	
	
	public List<Agendamento> buscarFiltrados(AgendamentoFilter filter) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Agendamento> criteria = builder.createQuery(Agendamento.class);
		Root<Agendamento> root = criteria.from(Agendamento.class);
		
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Agendamento> query = manager.createQuery(criteria);
		
		return query.getResultList();
				
	}
	
	private Predicate[] criarRestricoes(AgendamentoFilter filter, CriteriaBuilder builder,
			Root<Agendamento> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(filter.getNomeMedico())) {
			predicates.add(builder.like(
					builder.lower(root.get("medico").get("nome")), "%" + filter.getNomeMedico().toLowerCase() + "%"));
		}

		if (!StringUtils.isEmpty(filter.getNomePaciente())) {
			predicates.add(builder.like(
					builder.lower(root.get("paciente").get("nome")), "%" + filter.getNomePaciente().toLowerCase() + "%"));
		}
		
		if (filter.getDataInicial() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("dataAtendimento"), filter.getDataInicial()));
		}
		
		if (filter.getDataFinal() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get("dataAtendimento"), filter.getDataFinal()));
		}

		if (filter.getStatus() != null) {
			predicates.add(
					builder.equal(root.get("status"), filter.getStatus()));
		}				
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}

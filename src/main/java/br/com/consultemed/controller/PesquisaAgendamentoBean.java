package br.com.consultemed.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ComponentSystemEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.consultemed.filter.AgendamentoFilter;
import br.com.consultemed.model.Agendamento;
import br.com.consultemed.model.Medico;
import br.com.consultemed.model.Paciente;
import br.com.consultemed.model.StatusAgendamento;
import br.com.consultemed.service.AgendamentoService;
import br.com.consultemed.service.MedicoService;
import br.com.consultemed.service.NegocioException;
import br.com.consultemed.utils.Constantes;
import br.com.consultemed.utils.jsf.FacesUtil;
import lombok.Getter;

@Named
@Getter
@ViewScoped
public class PesquisaAgendamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AgendamentoService ageService;

	@Inject
	private MedicoService medService;
	
	private AgendamentoFilter filtro;
	private List<Agendamento> agendamentosFiltrados;
	private List<Medico> medicos;

	public PesquisaAgendamentoBean() {
		filtro = new AgendamentoFilter();
		agendamentosFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		agendamentosFiltrados = ageService.buscarFiltrados(filtro);
	}

	public void carregarMedicos(ComponentSystemEvent event) {
		this.medicos = medService.listar();
	}

	public void remover(Agendamento agendamento) {
		try {
			this.agendamentosFiltrados.remove(this.agendamentosFiltrados.indexOf(agendamento));
			this.ageService.remover(agendamento);
			FacesUtil.addInfoMessage(Constantes.AGENDAMENTO_REMOVIDO);
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public StatusAgendamento[] getStatusAgendamento() {
		return StatusAgendamento.values();
	}
	
	public void mudarStatusAgendamento(Agendamento agendamento) {
		try {
			this.ageService.mudarStatusAgendamento(agendamento);
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}		
	}
	
	public void pesquisarPacienteQueMaisCancelou() {		
		this.filtro.setNomeMedico(null);
		this.filtro.setNomePaciente(null);
		this.filtro.setStatus(StatusAgendamento.CANCELADA);
		
		this.agendamentosFiltrados = ageService.buscarFiltrados(this.filtro);
		if(this.agendamentosFiltrados.size() > 0) {
			Paciente pacMax = new Paciente();
			
			this.agendamentosFiltrados.sort((a1, a2) -> 
				a1.getPaciente().getId().compareTo(a2.getPaciente().getId()));
			
			int cont = 0;
			int max = 0;
			
			Long idPaciente = this.agendamentosFiltrados.get(0).getPaciente().getId();
			
			for(Agendamento a : this.agendamentosFiltrados) {	
				if(idPaciente == a.getPaciente().getId()) {
					cont++;					
				} else {					
					cont = 1;			
				}
				
				if(cont >= max) {
					pacMax = a.getPaciente();
					max = cont;
				} 
				
				idPaciente = a.getPaciente().getId();
			}
			
			this.filtro.setNomePaciente(pacMax.getNome());
			this.agendamentosFiltrados = ageService.buscarFiltrados(this.filtro);
			
			System.out.println("maximo: " + max);
			System.out.println("paciente: " + pacMax.getNome());
		}
	}
	
	private boolean isDatasPreenchidas() {
		return this.filtro.getDataInicial() != null 
			&& this.filtro.getDataFinal() != null;
	}
	
	public  boolean isDatasNaoPreenchidas() {
		return !this.isDatasPreenchidas();
	}
	
	public void limpar() {
		this.filtro = new AgendamentoFilter();
	}

}

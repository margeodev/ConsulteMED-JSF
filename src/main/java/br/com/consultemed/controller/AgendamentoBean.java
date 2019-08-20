 package br.com.consultemed.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.consultemed.model.Agendamento;
import br.com.consultemed.model.Dias;
import br.com.consultemed.model.Horas;
import br.com.consultemed.model.Medico;
import br.com.consultemed.model.Paciente;
import br.com.consultemed.model.StatusAgendamento;
import br.com.consultemed.model.Usuario;
import br.com.consultemed.service.AgendamentoService;
import br.com.consultemed.service.MedicoService;
import br.com.consultemed.service.NegocioException;
import br.com.consultemed.service.PacienteService;
import br.com.consultemed.utils.Constantes;
import br.com.consultemed.utils.jsf.FacesUtil;
import lombok.Getter;
import lombok.Setter;

@Named
@Getter
@ViewScoped
public class AgendamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AgendamentoService ageService;
	
	@Inject
	private MedicoService medService;
	
	@Inject
	private PacienteService pacService;

	private List<Agendamento> agendamentos = new ArrayList<>();
	private List<Medico> medicos;
	private List<LocalDate> invalidDates;
	private List<Integer> invalidDays;
	private List<Horas> horas = new ArrayList<>();
	private int numeroConsultasAgendadas;	

	@Setter
	private Agendamento agendamento = new Agendamento();
	@Setter
	private Usuario usuario = new Usuario();

	private Date minDate;	
	private Date maxDate;

	@PostConstruct
	public void iniciarHoras() {
		this.horas = Arrays.asList(Horas.values());		
	}

//	CARREGA LISTA DE AGENDAMENTOS
	public void inicializar() {
		this.agendamentos = ageService.listar();
		this.numeroConsultasAgendadas = this.agendamentos.size();
	}

	// CARREGA LISTA DE MEDICOS
	public void carregarMedicos(ComponentSystemEvent event) {
		this.medicos = medService.listar();
		
		if(this.isEditando()) {
			this.desabilitarDias();
		}
	}

	public void salvar() {		
		try {
			if(!this.isEditando()) {
				this.agendamento.setStatus(StatusAgendamento.ATIVA);
			}
			this.ageService.salvar(this.agendamento);
			
			if(this.agendamento.getStatus() == StatusAgendamento.CANCELADA) 
				FacesUtil.addInfoMessage(Constantes.AGENDAMENTO_CANCELADO);				

			if(this.agendamento.getStatus() != StatusAgendamento.CANCELADA) 
				FacesUtil.addInfoMessage(Constantes.AGENDAMENTO_CADASTRADO);		
			
			this.agendamento = new Agendamento();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public boolean isEditando() {
		return this.agendamento.getId() != null;
	}

	public List<Paciente> escolherPaciente(String nome) {
		return this.pacService.buscarPorNome(nome);
	}
	
	public void zerarDiasAtendimento() {
		this.agendamento.setDataAtendimento(null);
	}
	
	
//	##### DESABILITA OS DIAS QUE O MÉDICO NÃO ATENDE ####
	public void desabilitarDias() {
		long oneDay = 24 * 60 * 60 * 1000;
		Date today = new Date();
		minDate = new Date(today.getTime() - (1 * oneDay));
		this.maxDate = new Date(today.getTime() + (120 * oneDay));
		List<Dias> dias = this.agendamento.getMedico().getDiasAtendimento();
		this.invalidDays = new ArrayList<>();

		for (int i = 0; i < 7; i++) {
			if (i == 0) {
				if (!dias.contains(Dias.DOMINGO))
					this.invalidDays.add(i);
			}
			if (i == 1) {
				if (!dias.contains(Dias.SEGUNDA))
					this.invalidDays.add(i);
			}
			if (i == 2) {
				if (!dias.contains(Dias.TERÇA))
					this.invalidDays.add(i);
			}
			if (i == 3) {
				if (!dias.contains(Dias.QUARTA))
					this.invalidDays.add(i);
			}
			if (i == 4) {
				if (!dias.contains(Dias.QUINTA))
					this.invalidDays.add(i);
			}
			if (i == 5) {
				if (!dias.contains(Dias.SEXTA))
					this.invalidDays.add(i);
			}
			if (i == 6) {
				if (!dias.contains(Dias.SABADO))
					this.invalidDays.add(i);
			}

		}

	}

}

package br.com.consultemed.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.consultemed.model.Contato;
import br.com.consultemed.model.Paciente;
import br.com.consultemed.service.NegocioException;
import br.com.consultemed.service.PacienteService;
import br.com.consultemed.utils.Constantes;
import br.com.consultemed.utils.jsf.FacesUtil;
import lombok.Getter;
import lombok.Setter;

@Named
@Getter
@Setter
@ViewScoped
public class PacienteBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Contato contato = new Contato();
	private Paciente paciente = new Paciente();
	private List<Contato> contatos = new ArrayList<>();
	private List<Paciente> pacientes = new ArrayList<>();
	private int numeroPacientes;

	@Inject
	private PacienteService pacienteService;

	public void inicializar() {
		if(FacesUtil.isNotPostback()) {
			this.pacientes = pacienteService.listar();
			this.numeroPacientes = this.pacientes.size();
		}
	}
	
	public void salvar() {
		try {
			this.paciente.setContatos(contatos);
			this.pacienteService.salvar(this.paciente);
			FacesUtil.addInfoMessage(Constantes.PACIENTE_CADASTRADO);
			this.limparVariaveis();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());				
		}
	}
		
	public void adicionarContato() {		
		if(isEditando()) {
			this.contatos = this.paciente.getContatos();
		}
		this.contatos.add(contato);
		this.contato = new Contato();		
		FacesUtil.addInfoMessage(Constantes.CONTATO_CADASTRADO);
	}
	
	public void removerContato(Contato contato) {
		this.paciente.getContatos().remove(contato);
		this.contatos = this.paciente.getContatos();
//		this.contatos.remove((contato));
	}
	
	private void limparVariaveis() {
		this.paciente = new Paciente();
		this.contato = new Contato();
		this.contatos = new ArrayList<>();
	}
	
	public void remover(Paciente paciente) {
		try {
			this.pacienteService.remover(paciente);
			this.pacientes.remove(this.pacientes.indexOf(paciente));
			FacesUtil.addInfoMessage(Constantes.PACIENTE_REMOVIDO);
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());				
		}	
	}
	
	public boolean isEditando() {
		return this.paciente.getId() != null;
	}
	
	public void carregarContatos() {
//		if(FacesUtil.isNotPostback()) {
//			this.medicos = medicoService.listar();			
//		}
		if(isEditando()) {
			this.contatos = this.paciente.getContatos();
//			System.out.println("nome do paciente: " + this.paciente.getNome());
//			System.out.println("total de contatos: " + this.paciente.getContatos().size());
//			for(Contato c : this.paciente.getContatos()) {
//				System.out.println("nome do contato: " + c.getNome());
//			}
		}
	}
}

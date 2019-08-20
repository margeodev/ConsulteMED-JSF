package br.com.consultemed.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.consultemed.model.Dias;
import br.com.consultemed.model.Medico;
import br.com.consultemed.model.Usuario;
import br.com.consultemed.service.MedicoService;
import br.com.consultemed.service.NegocioException;
import br.com.consultemed.service.UsuarioService;
import br.com.consultemed.utils.Constantes;
import br.com.consultemed.utils.jsf.FacesUtil;
import lombok.Getter;
import lombok.Setter;

@Named
@Getter
@Setter
@ViewScoped
public class MedicoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Medico medico = new Medico();
	private List<Dias> dias = new ArrayList<>();
	private List<Medico> medicos = new ArrayList<>();
	private List<String> diasAtendimentoStr;
	private int numeroMedicos;
	
	@Inject
	private MedicoService medicoService;

	@Inject
	private UsuarioService usuarioService;
		
	@PostConstruct
	public void iniciarDias() {
		for(Dias dia: getDiasDescricao()) {
			dias.add(dia);
		}
	}
	
	public void inicializar() {
		if(FacesUtil.isNotPostback()) {
			this.medicos = medicoService.listar();
			this.numeroMedicos = this.medicos.size();
		}
		
		if(this.medico.getId() != null) {
			this.diasAtendimentoStr = this.converteEnumParaString(this.medico.getDiasAtendimento());
		} 
	}
	
	public void salvar() {
		if(this.diasAtendimentoStr.isEmpty()) {
			FacesUtil.addErrorMessage(Constantes.ERRO_INSERIR_DIAS);
		} else {
			this.setarDiasAtendimento();		
			try {
				this.medicoService.salvar(this.medico);
				FacesUtil.addInfoMessage(Constantes.MEDICO_CADASTRADO);
				limparVariaveis();
			} catch (NegocioException e) {
				FacesUtil.addErrorMessage(e.getMessage());				
			}	
		}
	}

	public void remover(Medico medico) {
		try {
			this.medicoService.remover(medico);
			this.medicos.remove(this.medicos.indexOf(medico));
			FacesUtil.addInfoMessage(Constantes.MEDICO_REMOVIDO);	
		} catch (NegocioException e) {
			FacesUtil.addInfoMessage(e.getMessage());				
		}	
	}
	
	public List<Usuario> escolherUsuario(String nome) {		
		return this.usuarioService.buscarUsuarioPorNomeGrupo(nome);
	}
	
	// ############ MÉTODOS AUXILIARES ############
	
	private List<String> converteEnumParaString(List<Dias> lista) {
		List<String> dias = new ArrayList<>();
		for (Dias dia : lista) {
			dias.add(dia.toString());
        }
		return dias;
	}
	
	private List<Dias> converteStringParaEnum(List<String> lista) {
		List<Dias> dias = new ArrayList<>();
		for (String diaStr : lista) {
			try {
				Dias dia = Dias.valueOf (diaStr);
				dias.add(dia);
            } catch (java.lang.IllegalArgumentException ex) {
                System.out.println ("String " + diaStr + " não localizada no Enum.");
            }			
        }
		return dias;
	}
	
	private void setarDiasAtendimento() {
		List<Dias> dias = converteStringParaEnum(this.diasAtendimentoStr);
		this.medico.setDiasAtendimento(dias);
	}
	
	private Dias[] getDiasDescricao() {
		return Dias.values();
	}
	
	private void limparVariaveis() {
		this.medico = new Medico();
		this.medicos = new ArrayList<>();
		this.diasAtendimentoStr = new ArrayList<>();
	}
	
}

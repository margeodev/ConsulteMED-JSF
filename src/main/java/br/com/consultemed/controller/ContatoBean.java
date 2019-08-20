package br.com.consultemed.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.consultemed.model.Contato;
import br.com.consultemed.service.ContatoService;
import br.com.consultemed.service.NegocioException;
import br.com.consultemed.utils.Constantes;
import br.com.consultemed.utils.jsf.FacesUtil;
import lombok.Getter;

@Named
@ViewScoped
@Getter
public class ContatoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Contato contato = new Contato();
	
	@Inject
	private ContatoService service;
		
	public void adicionar() {

		try {
			this.service.salvar(this.contato);
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(Constantes.ERRO_ADICIONAR_CONTATO);
		}
		
		this.contato = new Contato();
	}
}


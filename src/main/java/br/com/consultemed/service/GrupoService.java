package br.com.consultemed.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.consultemed.dao.GrupoDao;
import br.com.consultemed.model.Grupo;

public class GrupoService implements Serializable { 
	
	private static final long serialVersionUID = 1L;

	@Inject
	private GrupoDao dao;	
			
	public List<Grupo> listar(){
		return this.dao.listaTodos();
	}	


}

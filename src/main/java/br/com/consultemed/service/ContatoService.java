package br.com.consultemed.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import br.com.consultemed.dao.ContatoDao;
import br.com.consultemed.model.Contato;
import br.com.consultemed.tx.Transacional;
import br.com.consultemed.utils.Constantes;

public class ContatoService implements Serializable { 
	
	private static final long serialVersionUID = 1L;

	@Inject
	private ContatoDao contatoDao;
	
	@Transacional
	public void salvar(Contato contato) throws NegocioException {
		contatoDao.adiciona(contato);	
	}
	
	@Transacional
	public void atualizar(Contato contato) throws NegocioException {
		try {
			contatoDao.atualiza(contato);
		} catch (Exception e) {
			throw new NegocioException(Constantes.ERRO_AO_ATUALIZAR);
		}			
	}
	
	
	public List<Contato> listar(){
		return this.contatoDao.listaTodos();
	}
	
	@Transacional
	public void remover(Contato contato) throws NegocioException {
		try {
			this.contatoDao.remove(contato);			
		} catch (PersistenceException e) {
			throw new NegocioException(Constantes.ERRO_AO_EXCLUIR);
		}
	}
}

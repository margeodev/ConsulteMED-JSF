package br.com.consultemed.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

import br.com.consultemed.dao.GrupoDao;
import br.com.consultemed.dao.UsuarioDao;
import br.com.consultemed.model.Grupo;
import br.com.consultemed.model.Usuario;
import br.com.consultemed.utils.Constantes;

public class UsuarioService implements Serializable { 
	
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDao dao;
	
	@Inject
	private GrupoDao grupoDao;
		
	public void salvar(Usuario usuario) throws NegocioException {
		Usuario userBanco = dao.buscarPorEmail(usuario.getEmail());	
		
		if(userBanco != null && !userBanco.equals(usuario))
			throw new NegocioException(Constantes.ERRO_EMAIL_JA_CADASTRADO);	
		
		if(usuario.getId() != null) {
			userBanco = dao.buscaPorId(usuario.getId());
			if(userBanco != null) {
				dao.atualiza(usuario);
			}
		} else {
			dao.adiciona(usuario);
		}	
	}		
			
	public List<Usuario> listar(){
		return this.dao.listaTodos();
	}	
	
	public void remover(Usuario usuario) throws NegocioException {
		try {
			this.dao.remove(usuario);			
		} catch (PersistenceException e) {
			throw new NegocioException(Constantes.ERRO_AO_EXCLUIR);
		}
	}
	
	public boolean existe(Usuario usuario) {
		Usuario usuarioBanco = dao.buscarUsuario(usuario);

		if(usuarioBanco != null)
			return true;
		
		return false;		
	}
	
	public List<Usuario> buscarUsuarioPorNomeGrupo(String nome) {
		Grupo grupo = grupoDao.buscarPorNome("Medicos");
		Usuario usuario = new Usuario();
		usuario.setGrupo(grupo);
		usuario.setNome(nome);
		return dao.buscarUsuarioPorNomeGrupo(usuario);
	}

}

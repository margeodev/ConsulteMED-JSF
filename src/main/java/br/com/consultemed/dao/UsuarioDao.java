package br.com.consultemed.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.consultemed.model.Usuario;
import br.com.consultemed.tx.Transacional;

@SuppressWarnings("serial")
public class UsuarioDao implements Serializable {

	@Inject
	private EntityManager manager;
	
	private DAO<Usuario> dao;
	
	@PostConstruct
	private void init() {
		this.dao = new DAO<Usuario>(this.manager, Usuario.class);
	}
	
//	##### CREATE #####
	@Transacional
	public void adiciona(Usuario usuario) {
		this.dao.adiciona(usuario);		
	}
	
//	##### READ #####
	public List<Usuario> listaTodos() {
		return this.dao.listaTodos();
	}

//	##### UPDATE #####
	@Transacional
	public void atualiza(Usuario usuario) {
		this.dao.atualiza(usuario);
	}

//	##### DELETE #####
	@Transacional
	public void remove(Usuario usuario) {
		this.dao.remove(usuario);
	}
	
	
	public Usuario buscaPorId(Long id) {
		return this.dao.buscaPorId(id);
	}

	public Usuario buscarPorEmail(String email) {
		try {
			return manager.createQuery("from Usuario where email = :pEmail", Usuario.class)
					.setParameter("pEmail", email.toLowerCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} 
	}

	public Usuario buscarPorLogin(String login) {
		try {
			return manager.createQuery("from Usuario where login = :pLogin", Usuario.class)
					.setParameter("pLogin", login.toLowerCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} 
	}

	public Usuario buscarUsuario(Usuario usuario) {
		String login = usuario.getLogin();
		String senha = usuario.getSenha();
		try {
			return manager.createQuery("from Usuario where login = :pLogin and senha = :pSenha", Usuario.class)
					.setParameter("pLogin", login)
					.setParameter("pSenha", senha)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} 
	}
	
//	##### BUSCA USUÁRIOS POR NOME QUE PERTENCEM AO GRUPO MEDICOS #####
	public List<Usuario> buscarUsuarioPorNomeGrupo(Usuario usuario) {
		return manager.createQuery("from Usuario where nome like :pNome and grupo = :pGrupo", Usuario.class)
				.setParameter("pNome", usuario.getNome() + "%")
				.setParameter("pGrupo", usuario.getGrupo())
				.getResultList();
	}

}

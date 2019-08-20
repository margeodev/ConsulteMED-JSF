package br.com.consultemed.controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.consultemed.model.Grupo;
import br.com.consultemed.model.Usuario;
import br.com.consultemed.service.GrupoService;
import br.com.consultemed.service.NegocioException;
import br.com.consultemed.service.UsuarioService;
import br.com.consultemed.utils.Constantes;
import br.com.consultemed.utils.jsf.FacesUtil;
import lombok.Getter;
import lombok.Setter;

@Named
@Getter
@ViewScoped
public class UsuarioBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private GrupoService grupoService;
	
	private List<Usuario> usuarios = new ArrayList<>();
	private List<Grupo> grupos;
	
	@Setter
	private Usuario usuario = new Usuario();

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			this.usuarios = this.usuarioService.listar();
			this.grupos = this.grupoService.listar();			
		}
	}
	
	public void salvar() {
		try {
			this.usuarioService.salvar(this.usuario);
			FacesUtil.addInfoMessage(Constantes.USUARIO_CADASTRADO);
			this.usuario = new Usuario();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());				
		}	
	}
	
	public void remover(Usuario usuario) {
		try {
			this.usuarioService.remover(usuario);
			this.usuarios.remove(this.usuarios.indexOf(usuario));
			FacesUtil.addInfoMessage(Constantes.USUARIO_REMOVIDO);
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());			
		}	
	}
	
	public boolean isEditando() {
		return this.usuario.getId() != null;
	}
}

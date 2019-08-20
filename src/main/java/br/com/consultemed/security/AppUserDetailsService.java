package br.com.consultemed.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.consultemed.dao.UsuarioDao;
import br.com.consultemed.model.Grupo;
import br.com.consultemed.model.Usuario;
import br.com.consultemed.utils.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UsuarioDao dao = CDIServiceLocator.getBean(UsuarioDao.class);
		Usuario usuario = dao.buscarPorLogin(login);
		
		UsuarioSistema user = null;
		
		if (usuario != null) {
			user = new UsuarioSistema(usuario, getGrupos(usuario));
		}
		
		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		Grupo grupo = usuario.getGrupo();
		authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
		
		return authorities;
	}

}
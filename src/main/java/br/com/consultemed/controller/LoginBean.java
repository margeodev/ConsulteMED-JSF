package br.com.consultemed.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.consultemed.utils.Constantes;
import br.com.consultemed.utils.jsf.FacesUtil;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
@Getter
@Setter
public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;
	
	@Inject
	private HttpServletRequest request;
	
	@Inject
	private HttpServletResponse response;
	
	private String login;
			
	public void fazerlogin() throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(Constantes.LOGOUT);
		dispatcher.forward(request, response);
		
		facesContext.responseComplete();
	}
	
	public void preRender() {
		if ("true".equals(request.getParameter("invalid"))) {
			FacesUtil.addErrorMessage(Constantes.ERRO_USUARIO_NAO_ENCONTRADO);
		}
	}
		
}

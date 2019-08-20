package br.com.consultemed.tx;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@SuppressWarnings("serial")
@Transacional
@Interceptor
public class GerenciadorDeTransacao implements Serializable {

	@Inject
	private EntityManager manager;
	
	@AroundInvoke
	public Object executaTransacao(InvocationContext context) throws Exception {	
		manager.getTransaction().begin();
		
		Object result = context.proceed();
		// commita a transacao
		manager.getTransaction().commit();
		
		return result;
	}
}

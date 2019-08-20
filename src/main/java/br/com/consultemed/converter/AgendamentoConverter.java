package br.com.consultemed.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.consultemed.dao.AgendamentoDao;
import br.com.consultemed.model.Agendamento;
import br.com.consultemed.utils.cdi.CDIServiceLocator;

@FacesConverter(forClass = Agendamento.class)
public class AgendamentoConverter implements Converter {

	private AgendamentoDao dao;
	
	public AgendamentoConverter() {
		dao = CDIServiceLocator.getBean(AgendamentoDao.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Agendamento retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = dao.buscaPorId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Agendamento agendamento = (Agendamento) value;
			return agendamento.getId() == null ? null : agendamento.getId().toString();
		}
		
		return "";
	}
}

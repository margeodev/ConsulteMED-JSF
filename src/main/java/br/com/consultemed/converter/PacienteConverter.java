package br.com.consultemed.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.consultemed.dao.PacienteDao;
import br.com.consultemed.model.Paciente;
import br.com.consultemed.utils.cdi.CDIServiceLocator;

@FacesConverter(forClass = Paciente.class)
public class PacienteConverter implements Converter {

	private PacienteDao dao;
	
	public PacienteConverter() {
		dao = CDIServiceLocator.getBean(PacienteDao.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Paciente retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = dao.buscaPorId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Paciente paciente = (Paciente) value;
			return paciente.getId() == null ? null : paciente.getId().toString();
		}
		
		return "";
	}
}

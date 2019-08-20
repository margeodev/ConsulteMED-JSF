package br.com.consultemed.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.consultemed.dao.MedicoDao;
import br.com.consultemed.model.Medico;
import br.com.consultemed.utils.cdi.CDIServiceLocator;

@FacesConverter(forClass = Medico.class)
public class MedicoConverter implements Converter {

	private MedicoDao dao;
	
	public MedicoConverter() {
		dao = CDIServiceLocator.getBean(MedicoDao.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Medico retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = dao.buscaPorId(id);
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Medico medico = (Medico) value;
			return medico.getId() == null ? null : medico.getId().toString();
		}
		
		return "";
	}
}

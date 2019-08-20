import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import br.com.consultemed.model.Agendamento;
import br.com.consultemed.model.Paciente;
import br.com.consultemed.model.StatusAgendamento;
import br.com.consultemed.utils.Constantes;

public class Teste {
	
	
	public static void main(String[] args) {
		
//		java.util.Date d = new Date();
//
//		String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
//		System.out.println(dStr);
		
//		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
//		Date date = new Date(); 
//		System.out.println(date);
		
//		LocalTime horarioDeEntrada = LocalTime.of(23, 0);
//		System.out.println(horarioDeEntrada); //09:00
		
		StatusAgendamento status;
//		System.out.println(StatusAgendamento.ATIVA);
		
		
		
		
		
//	   SimpleDateFormat sdf = new SimpleDateFormat( "HH:mm:ss" );
//	    Calendar cal = Calendar.getInstance();
//	    
//	    cal.set(Calendar.HOUR_OF_DAY, 10);
//	    cal.set(Calendar.MINUTE, 23);
//	    cal.set(Calendar.SECOND, 15);
	   
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(Constantes.PERSISTENCE_UNIT_NAME);
		EntityManager manager = emf.createEntityManager();
		
		
		Agendamento agendamento = new Agendamento();
		List<Agendamento> agendamentos = new ArrayList<>();		
		
		try {
			agendamentos = manager.createQuery("from Agendamento where status = :pStatus", Agendamento.class)
					.setParameter("pStatus", StatusAgendamento.CANCELADA)
					.getResultList();
			
			int totalPacientes = 1;
			int totalCancelPacientes = 0;
			Paciente pacMax = new Paciente();
			
			if(agendamentos.size()>0) {		
				List<Agendamento> list = Arrays.asList(agendamento);
								

				
				agendamentos.sort((a1, a2) -> 
					a1.getPaciente().getId().compareTo(a2.getPaciente().getId()));
				
		
				
				int cont = 0;
				int cont2 = 0;
				int max = 0;
				
				Long idPaciente = agendamentos.get(0).getPaciente().getId();
				for(Agendamento a : agendamentos) {	
					Long controle = a.getPaciente().getId();
					if(idPaciente == a.getPaciente().getId()) {

						cont++;

						
					} else {

						
						cont = 1;
				
					}
					
					if(cont >= max) {
						pacMax = a.getPaciente();
						max = cont;
					} 
					
					
					idPaciente = a.getPaciente().getId();
				}
				System.out.println("maximo: " + max);
				System.out.println("paciente: " + pacMax.getNome());	

			}
			
		} catch (NoResultException e) {
			
		} 
	}
	
	
}

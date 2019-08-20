package br.com.consultemed.filter;

import java.io.Serializable;
import java.util.Date;

import br.com.consultemed.model.StatusAgendamento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendamentoFilter implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Date dataInicial;
	private Date dataFinal;
	private String nomeMedico;
	private String nomePaciente;
	private StatusAgendamento status;
	
}

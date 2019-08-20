package br.com.consultemed.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Setter
@Table(name = "TB_AGENDAMENTOS")
public class Agendamento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Getter
	@ManyToOne
	private Medico medico;
	
	@NotNull
	@Getter
	@ManyToOne
	private Paciente paciente;
	
	@NotNull
	@Getter
	private Date dataAtendimento;
	
	@NotNull
	@Getter
	@Enumerated(EnumType.STRING)	
	private Horas hora;
		
	@Getter
	@Enumerated(EnumType.STRING)
	private StatusAgendamento status;
	
	@Transient
	private boolean dataExpirada;
	
	public boolean isDataExpirada() {
		return this.getDataAtendimento().before(new Date());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agendamento other = (Agendamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}

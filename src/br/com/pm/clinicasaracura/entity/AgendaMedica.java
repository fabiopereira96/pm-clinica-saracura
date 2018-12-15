package br.com.pm.clinicasaracura.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AgendaMedica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date diaAgendamento;

	@ManyToOne
	@JoinColumn(name = "idMedico")
	private Medico medico;

	@ManyToOne
	@JoinColumn(name = "idPaciente")
	private Paciente paciente;

	public AgendaMedica() {
	}

	public AgendaMedica(Date diaAgendamento, Medico medico, Paciente paciente) {
		super();
		this.diaAgendamento = diaAgendamento;
		this.medico = medico;
		this.paciente = paciente;
	}

	public Date getDiaAgendamento() {
		return diaAgendamento;
	}

	public void setDiaAgendamento(Date diaAgendamento) {
		this.diaAgendamento = diaAgendamento;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@Override
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return f.format(diaAgendamento) + " - " + medico;
	}
}

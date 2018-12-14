package br.com.pm.clinicasaracura.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AgendaEquipamento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAgendamento;

	@ManyToOne
	@JoinColumn(name = "idMedico")
	private Medico medico;

	@ManyToOne
	@JoinColumn(name = "idPaciente")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name ="idEquipamento")
	private Equipamento equipamento;
	
	@Column 
	private String descricao;

	public AgendaEquipamento() {}

	public AgendaEquipamento(Date dataAgendamento, Medico medico, Paciente paciente, Equipamento equipamento,
			String descricao) {
		super();
		this.dataAgendamento = dataAgendamento;
		this.medico = medico;
		this.paciente = paciente;
		this.equipamento = equipamento;
		this.descricao = descricao;
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
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

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataAgendamento == null) ? 0 : dataAgendamento.hashCode());
		result = prime * result + ((equipamento == null) ? 0 : equipamento.hashCode());
		result = prime * result + ((medico == null) ? 0 : medico.hashCode());
		result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
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
		AgendaEquipamento other = (AgendaEquipamento) obj;
		if (dataAgendamento == null) {
			if (other.dataAgendamento != null)
				return false;
		} else if (!dataAgendamento.equals(other.dataAgendamento))
			return false;
		if (equipamento == null) {
			if (other.equipamento != null)
				return false;
		} else if (!equipamento.equals(other.equipamento))
			return false;
		if (medico == null) {
			if (other.medico != null)
				return false;
		} else if (!medico.equals(other.medico))
			return false;
		if (paciente == null) {
			if (other.paciente != null)
				return false;
		} else if (!paciente.equals(other.paciente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return dataAgendamento + " - " + medico;
	}
}

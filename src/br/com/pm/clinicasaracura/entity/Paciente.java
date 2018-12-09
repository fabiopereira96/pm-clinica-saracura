package br.com.pm.clinicasaracura.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Paciente")
public class Paciente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPaciente;

	@Column
	private String nome;
	@Column
	private Date dataNascimento;
	@Column
	private char sexo;

	@ManyToOne
	@JoinColumn(name = "idConvenio")
	private Convenio convenio;

	public Paciente() {
	}

	public Paciente(String nome, Date dataNascimento, Convenio convenio, char sexo) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.convenio = convenio;
		this.sexo = sexo;
	}

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPaciente == null) ? 0 : idPaciente.hashCode());
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
		Paciente other = (Paciente) obj;
		if (idPaciente == null) {
			if (other.idPaciente != null)
				return false;
		} else if (!idPaciente.equals(other.idPaciente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return idPaciente + " - " + nome;
	}
}

package br.com.pm.clinicasaracura.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Especialidade")
public class Especialidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEspecialidade;
	@Column
	private String nome;

	public Especialidade() {
	}

	public Especialidade(Integer idEspecialidade, String nome) {
		super();
		this.idEspecialidade = idEspecialidade;
		this.nome = nome;
	}

	public Integer getIdEspecialidade() {
		return idEspecialidade;
	}

	public void setIdEspecialidade(Integer idEspecialidade) {
		this.idEspecialidade = idEspecialidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
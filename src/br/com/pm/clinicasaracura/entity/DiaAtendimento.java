package br.com.pm.clinicasaracura.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DiaAtendimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer idDia;

	@Column 
	private String nomeDia;

	public DiaAtendimento() {
	}

	public DiaAtendimento(Integer idDia, String nomeDia) {
		super();
		this.idDia = idDia;
		this.nomeDia = nomeDia;
	}

	public Integer getIdDia() {
		return idDia;
	}

	public void setIdDia(Integer idDia) {
		this.idDia = idDia;
	}

	public String getNomeDia() {
		return nomeDia;
	}

	public void setNomeDia(String nomeDia) {
		this.nomeDia = nomeDia;
	}

}

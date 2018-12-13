package br.com.pm.clinicasaracura.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Equipamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEquipamento;
	
	@Column
	private String nome;
	
	@Column
	private Boolean statusFuncionamento;
	
	@ManyToOne
	@JoinColumn(name = "idTipoExame")
	private TipoExame tipoExame;

	public Equipamento() {}

	public Equipamento(String nome, Boolean statusFuncionamento, TipoExame tipoExame) {
		super();
		this.nome = nome;
		this.statusFuncionamento = statusFuncionamento;
		this.tipoExame = tipoExame;
	}

	public Integer getIdEquipamento() {
		return idEquipamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getStatusFuncionamento() {
		return statusFuncionamento;
	}

	public void setStatusFuncionamento(Boolean statusFuncionamento) {
		this.statusFuncionamento = statusFuncionamento;
	}

	public TipoExame getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(TipoExame tipoExame) {
		this.tipoExame = tipoExame;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEquipamento == null) ? 0 : idEquipamento.hashCode());
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
		Equipamento other = (Equipamento) obj;
		if (idEquipamento == null) {
			if (other.idEquipamento != null)
				return false;
		} else if (!idEquipamento.equals(other.idEquipamento))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return idEquipamento + " - " + nome;
	}
}

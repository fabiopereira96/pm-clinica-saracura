package br.com.pm.clinicasaracura.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TipoExame")
public class TipoExame implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoExame;
	
	@Column
	private String descricao;

	public TipoExame() {}

	public TipoExame(String descricao) {
		super();
		this.descricao = descricao;
	}

	public Integer getIdTipoExame() {
		return idTipoExame;
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
		result = prime * result + ((idTipoExame == null) ? 0 : idTipoExame.hashCode());
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
		TipoExame other = (TipoExame) obj;
		if (idTipoExame == null) {
			if (other.idTipoExame != null)
				return false;
		} else if (!idTipoExame.equals(other.idTipoExame))
			return false;
		return true;
	}
	
}

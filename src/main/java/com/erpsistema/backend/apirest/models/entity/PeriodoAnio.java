package com.erpsistema.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="periodo_anio")
public class PeriodoAnio implements Serializable{

	private static final long serialVersionUID = -2168472702615989728L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 10, name="anio_periodo")
	private String anioPeriodo;
	
	@Column(length = 1, name = "estado")
	private Integer estado;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnioPeriodo() {
		return anioPeriodo;
	}

	public void setAnioPeriodo(String anioPeriodo) {
		this.anioPeriodo = anioPeriodo;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	
}

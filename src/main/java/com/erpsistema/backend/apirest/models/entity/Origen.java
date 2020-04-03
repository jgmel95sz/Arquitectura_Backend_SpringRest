package com.erpsistema.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="origen")
public class Origen implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "ORIGEN_SEQ")
	//@SequenceGenerator(sequenceName = "OrigenIdSeq", name = "ORIGEN_SEQ", allocationSize = 1, initialValue = 15)
	private Integer id; 

	@Column(length = 2, name="codigo")
	//@Column(length = 2, name="CODIGO")
	private String codigo;
	
	@Column(length = 25, name="descripcion")
	//@Column(length = 25, name="DESCRIPCION")
	private String descripcion;
	
	private Boolean enabled;
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public Boolean getEnabled() {
		return enabled;
	}



	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}



	private static final long serialVersionUID = 1L;

}

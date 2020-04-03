package com.erpsistema.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_documento")
public class TipoDocumento implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "TIPODOCUMENTO_SEQ")
	//@SequenceGenerator(sequenceName = "tipodocumentoIdSeq", name = "TIPODOCUMENTO_SEQ", allocationSize = 1, initialValue = 7)
	private Integer id;
	
	@Column(length = 22, name="nombre")
	//@Column(length = 22, name="NAME")
	private String name;
	
	@Column(length = 2, name="codigo")
	//@Column(length = 2, name="CODIGO")
	private String codigo;
	
	
	
	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	private static final long serialVersionUID = 1L;

}

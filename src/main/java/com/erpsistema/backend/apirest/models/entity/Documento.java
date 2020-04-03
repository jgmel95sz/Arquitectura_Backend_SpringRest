package com.erpsistema.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="documento")
public class Documento implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "DOCUMENTO_SEQ")
	//@SequenceGenerator(sequenceName = "documentoIdSeq", name = "DOCUMENTO_SEQ", allocationSize = 1, initialValue = 58)
	private Integer id;
	
	//@Column(length = 22, name="NOMBRE_DOCUMENTO")
	@Column(length = 22, name="nombre_documento")
	private String nombreDocumento;
	
	//@Column(length = 2, name="CODIGO")
	@Column(length = 2, name="codigo")
	private String codigo;
	
	
	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getNombreDocumento() {
		return nombreDocumento;
	}




	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}




	public String getCodigo() {
		return codigo;
	}




	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	private static final long serialVersionUID = 1L;

}

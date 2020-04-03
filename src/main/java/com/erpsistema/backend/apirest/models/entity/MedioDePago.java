package com.erpsistema.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="medio_pago")
public class MedioDePago implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "MEDIOPAGO_SEQ")
	//@SequenceGenerator(sequenceName = "mediopagoIdSeq", name = "MEDIOPAGO_SEQ", allocationSize = 1, initialValue = 21)
	private Integer id;
	
	//@Column(length = 3, name="CODIGO")
	@Column(length = 3, name="codigo")
	private String codigo;
	
	//@Column(length = 27, name="NOMBRE_MEDIO_PAGO")
	@Column(length = 27, name="nombre_medio_pago")
	private String nombreMedioDePago;
	

	
	
	
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



	public String getNombreMedioDePago() {
		return nombreMedioDePago;
	}



	public void setNombreMedioDePago(String nombreMedioDePago) {
		this.nombreMedioDePago = nombreMedioDePago;
	}



	private static final long serialVersionUID = 1L;

}

package com.erpsistema.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="moneda")
public class Moneda implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "MONEDA_SEQ")
	//@SequenceGenerator(sequenceName = "monedaIdSeq", name = "MONEDA_SEQ", allocationSize = 1, initialValue = 5)
	private Integer id;
	
	//@Column(length = 10, name="NOM_MONEDA")
	@Column(length = 10, name="nom_moneda")
	private String nomMoneda;
	
	//@Column(length = 3, name="ABRE_NOM_MONEDA")
	@Column(length = 3, name="abre_nom_moneda")
	private String abreNomMoneda;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomMoneda() {
		return nomMoneda;
	}

	public void setNomMoneda(String nomMoneda) {
		this.nomMoneda = nomMoneda;
	}

	public String getAbreNomMoneda() {
		return abreNomMoneda;
	}

	public void setAbreNomMoneda(String abreNomMoneda) {
		this.abreNomMoneda = abreNomMoneda;
	}	
	
	private static final long serialVersionUID = 1L;
}

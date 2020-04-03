package com.erpsistema.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_all_persona")
public class TipoAllPersona implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "TIPOALLPERSONA_SEQ")
	//@SequenceGenerator(sequenceName = "tipoallpersonaIdSeq", name = "TIPOALLPERSONA_SEQ", allocationSize = 1, initialValue = 10)
	private Integer id;
	
	//@Column(length = 12, name="NAME")
	@Column(length = 12, name="nombre")
	private String name;
	
	
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

package com.erpsistema.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="unidad_negocio")
public class UnidadNegocio implements Serializable{

	private static final long serialVersionUID = 5656689569400315504L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 10, name="codigo_unidad_negocio")
	private String codigoUnidadNegocio;
	
	@Column(length = 20, name="nombre_unidad")
	private String nombUnidad;
	
	@Column(length = 10, name="amarre_debe")
	private String amarreDebe;
	
	@Column(length = 10, name="amarre_haber")
	private String amarreHaber;
	
	@Column(name="enabled")
	private Integer enabled;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoUnidadNegocio() {
		return codigoUnidadNegocio;
	}

	public void setCodigoUnidadNegocio(String codigoUnidadNegocio) {
		this.codigoUnidadNegocio = codigoUnidadNegocio;
	}

	public String getNombUnidad() {
		return nombUnidad;
	}

	public void setNombUnidad(String nombUnidad) {
		this.nombUnidad = nombUnidad;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getAmarreDebe() {
		return amarreDebe;
	}

	public void setAmarreDebe(String amarreDebe) {
		this.amarreDebe = amarreDebe;
	}

	public String getAmarreHaber() {
		return amarreHaber;
	}

	public void setAmarreHaber(String amarreHaber) {
		this.amarreHaber = amarreHaber;
	}
	


}

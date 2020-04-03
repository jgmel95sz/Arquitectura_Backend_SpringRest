package com.erpsistema.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;

@Entity
@Table(name="cont_all_persona")
public class ContAllPerson implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "CONTALLPERSONA_SEQ")
	//@SequenceGenerator(sequenceName = "contallpersonIdSeq", name = "CONTALLPERSONA_SEQ", allocationSize = 1, initialValue = 1)
	private Integer id;
	
	//@Column(length = 15, name="CODIGO_TRIBUTARIO")
	@Column(length = 15, name="codigo_tributario")
	private String cod_tributario;
	
	//@Column(name="DIAS_VENC_FACTURAS")
	@Column(name="dias_venc_facturas")
	private Integer dias_venc_facturas;
	
	//@Column(name="ENABLED")
	@Column(name="enabled")
	private Integer enabled;
	
	@OneToOne ()
	//@JoinColumn(name="PERSONA_JURIDICA_ID")
	@JoinColumn(name="persona_juridica_id")
	@Valid
	private DrtPersJuridica datosPers;
	
	@OneToOne ()
	//@JoinColumn(name="TIPO_ALL_PERSONA_ID")
	@JoinColumn(name="tipo_all_persona_id")
	private TipoAllPersona contTipoAllper;
	

	public DrtPersJuridica getDatosPers() {
		return datosPers;
	}

	public void setDatosPers(DrtPersJuridica datosPers) {
		this.datosPers = datosPers;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public TipoAllPersona getContTipoAllper() {
		return contTipoAllper;
	}


	public void setContTipoAllper(TipoAllPersona contTipoAllper) {
		this.contTipoAllper = contTipoAllper;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCod_tributario() {
		return cod_tributario;
	}

	public void setCod_tributario(String cod_tributario) {
		this.cod_tributario = cod_tributario;
	}

	public Integer getDias_venc_facturas() {
		return dias_venc_facturas;
	}

	public void setDias_venc_facturas(Integer dias_venc_facturas) {
		this.dias_venc_facturas = dias_venc_facturas;
	}

	private static final long serialVersionUID = 1L;

}

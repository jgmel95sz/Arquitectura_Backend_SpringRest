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

@Entity
@Table(name="parametros")
public class Parametros implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PARAMETROS_SEQ")
	//@SequenceGenerator(sequenceName = "ParametrosIdSeq", name = "PARAMETROS_SEQ", allocationSize = 1, initialValue = 2)
	private Integer id;
	
	@Column(length = 7)
	private String impuesto;
	
	@Column(length = 7)
	private String impHon;
	
	@Column(length = 7)
	private String impHon2;
		
	@OneToOne ()
	//@JoinColumn(name="ORIGEN_ID")
	@JoinColumn(name="origen_id")
	private Origen origen;
	
		
	//@Column(length = 50, name="NOM_CTA_IMP_HON")
	@Column(length = 50, name="nom_cta_imp_hon")
	private String nomCtaImpHon;

	
	//@Column(length = 10, name="CTA_IMPHON")
	@Column(length = 10, name="cta_imphon")
	private String ctaImpHon;
	
	//@Column(length = 50, name="NOM_CTA_IMP_COM")
	@Column(length = 50, name="nom_cta_imp_com")
	private String nomCtaImpCom;
	
	//@Column(length = 10, name="CTA_IMPCOM")
	@Column(length = 10, name="cta_impcom")
	private String ctaImpCom;
	
	//@Column(length = 50, name="NOM_CTA_IMP_RET")
	@Column(length = 50, name="nom_cta_imp_ret")
	private String nomCtaImpRet;
	
	//@Column(length = 10, name="CTA_IMPVTA")
	@Column(length = 10, name="cta_impvta")
	private String ctaImpVta;
	
	//@Column(length = 50, name="NOM_CTA_IMP_TA")
	@Column(length = 50, name="nom_cta_imp_ta")
	private String nomCtaImpta;
	
	//@Column(length = 10, name="CTA_IMPRET")
	@Column(length = 10, name="cta_impret")
	private String ctaImpRet;
	
	//@Column(length = 50, name="NOM_RESUL_EJERCICIO")
	@Column(length = 50, name="nom_resul_ejercicio")
	private String nomResulEjercicio;
	
	//@Column(length = 10, name="RESUL_EJERCICIO")
	@Column(length = 10, name="resul_ejercicio")
	private String resulEjercicio;
	
	//@Column(length = 10, name="CTA_IMPHON2")
	@Column(length = 10, name="cta_imphon2")
	private String ctaImpHon2;
	
	//@Column(length = 10, name="CTA_CONTABLE")
	@Column(length = 10, name="cta_contable")
	private String ctaContable;

	@Column(name="periodo_id")  
	private Integer periodoId;
	
	public String getNomCtaImpHon() {
		return nomCtaImpHon;
	}




	public Integer getPeriodoId() {
		return periodoId;
	}




	public void setPeriodoId(Integer periodoId) {
		this.periodoId = periodoId;
	}




	public void setNomCtaImpHon(String nomCtaImpHon) {
		this.nomCtaImpHon = nomCtaImpHon;
	}




	public String getNomCtaImpCom() {
		return nomCtaImpCom;
	}




	public void setNomCtaImpCom(String nomCtaImpCom) {
		this.nomCtaImpCom = nomCtaImpCom;
	}




	public String getNomCtaImpRet() {
		return nomCtaImpRet;
	}




	public void setNomCtaImpRet(String nomCtaImpRet) {
		this.nomCtaImpRet = nomCtaImpRet;
	}




	public String getNomCtaImpta() {
		return nomCtaImpta;
	}




	public void setNomCtaImpta(String nomCtaImpta) {
		this.nomCtaImpta = nomCtaImpta;
	}




	public String getNomResulEjercicio() {
		return nomResulEjercicio;
	}




	public void setNomResulEjercicio(String nomResulEjercicio) {
		this.nomResulEjercicio = nomResulEjercicio;
	}




	public Origen getOrigen() {
		return origen;
	}




	public void setOrigen(Origen origen) {
		this.origen = origen;
	}




	public String getImpuesto() {
		return impuesto;
	}




	public void setImpuesto(String impuesto) {
		this.impuesto = impuesto;
	}




	public String getImpHon() {
		return impHon;
	}




	public void setImpHon(String impHon) {
		this.impHon = impHon;
	}




	public String getImpHon2() {
		return impHon2;
	}




	public void setImpHon2(String impHon2) {
		this.impHon2 = impHon2;
	}




	public String getCtaImpHon2() {
		return ctaImpHon2;
	}




	public void setCtaImpHon2(String ctaImpHon2) {
		this.ctaImpHon2 = ctaImpHon2;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}


	public String getCtaImpHon() {
		return ctaImpHon;
	}




	public void setCtaImpHon(String ctaImpHon) {
		this.ctaImpHon = ctaImpHon;
	}




	public String getCtaImpCom() {
		return ctaImpCom;
	}




	public void setCtaImpCom(String ctaImpCom) {
		this.ctaImpCom = ctaImpCom;
	}




	public String getCtaImpVta() {
		return ctaImpVta;
	}




	public void setCtaImpVta(String ctaImpVta) {
		this.ctaImpVta = ctaImpVta;
	}




	public String getCtaImpRet() {
		return ctaImpRet;
	}




	public void setCtaImpRet(String ctaImpRet) {
		this.ctaImpRet = ctaImpRet;
	}




	public String getResulEjercicio() {
		return resulEjercicio;
	}




	public void setResulEjercicio(String resulEjercicio) {
		this.resulEjercicio = resulEjercicio;
	}



	public String getCtaContable() {
		return ctaContable;
	}




	public void setCtaContable(String ctaContable) {
		this.ctaContable = ctaContable;
	}




	private static final long serialVersionUID = 1L;

}

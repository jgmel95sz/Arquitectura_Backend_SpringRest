package com.erpsistema.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tipo_cambio")
public class TipoCambio implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy=GenerationType.SEQUENCE, generator =
	// "TIPOCAMBIO_SEQ")
	// @SequenceGenerator(sequenceName = "tipocambioIdSeq", name = "TIPOCAMBIO_SEQ",
	// allocationSize = 1, initialValue = 1)
	private Integer id;

	// @Column(name="FECHA")
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	// @Column(length = 10, name="COMPRA")
	@Column(length = 10, name = "compra")
	private String compra;

	// @Column(length = 10, name="VENTA")
	@Column(length = 10, name = "venta")
	private String venta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCompra() {
		return compra;
	}

	public void setCompra(String compra) {
		this.compra = compra;
	}

	public String getVenta() {
		return venta;
	}

	public void setVenta(String venta) {
		this.venta = venta;
	}

	private static final long serialVersionUID = 1L;

}

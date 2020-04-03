package com.erpsistema.backend.apirest.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "PerfilUsuario")
@Table(name="perfil_usuario")
public class PerfilUsuario implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PERFILUSUARIO_SEQ")
	//@SequenceGenerator(sequenceName = "perfilUsuarioIdSeq", name = "PERFILUSUARIO_SEQ", allocationSize = 1, initialValue = 6)
	private Integer id;
	
	@Column(length = 25)
	private String nombre;
	
	@Column(length = 30)
	private String apellido;
	
	@Column(unique = true)
	private String email;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	private static final long serialVersionUID = 1L;
}

package com.erpsistema.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "Usuario")
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "USAURIO_SEQ")
	//@SequenceGenerator(sequenceName = "usuarioIdSeq", name = "USAURIO_SEQ", allocationSize = 1, initialValue = 4)
	private Integer id;

	@Column(unique = true, length = 20)
	private String username;

	@Column(length = 60)
	private String password;

	private Boolean enabled;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="usuario_id")
	private List<PerfilUsuario> perfilUsuario;

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(joinColumns = @JoinColumn(name = "usuarios_id"), 
	inverseJoinColumns = @JoinColumn(name = "roles_id"), 
	uniqueConstraints = {@UniqueConstraint(columnNames= {"usuarios_id", "roles_id"})})
	public List<Role> roles;

	
	
	public Usuario() {
		/*this.perfilUsuario = new ArrayList<>();*/
		this.roles = new ArrayList<>();
	}
	
	public List<PerfilUsuario> getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(List<PerfilUsuario> perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	private static final long serialVersionUID = 1L;
}

package com.erpsistema.backend.apirest.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erpsistema.backend.apirest.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Integer>{

	public Usuario findByUsername(String username);
	
	@Query("select u from Usuario u where u.username=?1")
	public Usuario buscarPorUsuario(String username);
	
	@Query("select u from Usuario u where u.enabled = 1")
	public Page<Usuario> findByEnabledPaged(Pageable pageable);
}

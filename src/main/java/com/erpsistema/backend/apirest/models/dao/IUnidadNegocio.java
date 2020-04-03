package com.erpsistema.backend.apirest.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erpsistema.backend.apirest.models.entity.UnidadNegocio;

public interface IUnidadNegocio extends JpaRepository<UnidadNegocio, Integer>{

	@Query("Select u from UnidadNegocio u where u.enabled = 1")
	public Page<UnidadNegocio> findByEnabledPaged(Pageable pageable);
}

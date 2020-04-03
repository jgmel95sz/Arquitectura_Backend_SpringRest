package com.erpsistema.backend.apirest.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erpsistema.backend.apirest.models.entity.DrtPersJuridica;

public interface IDrtPersJuridicaDao extends JpaRepository<DrtPersJuridica, Integer> {

	@Query("Select u From DrtPersJuridica u where u.codigo=?1")
	public DrtPersJuridica buscarPorPersonaJuridica(String codigo);
	
	@Query("Select u From DrtPersJuridica u where u.id=?1")
	public DrtPersJuridica buscarPorPersonaJuridicaId(Integer id);
	
	@Query("Select u From DrtPersJuridica u where u.enabled = 1")
	public Page<DrtPersJuridica> findByEnabledPaged(Pageable pageable);
}

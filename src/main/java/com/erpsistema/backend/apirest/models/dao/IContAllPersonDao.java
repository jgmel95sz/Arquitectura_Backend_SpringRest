package com.erpsistema.backend.apirest.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erpsistema.backend.apirest.models.entity.ContAllPerson;

public interface IContAllPersonDao extends JpaRepository<ContAllPerson ,Integer> {

	@Query("Select u from ContAllPerson u where u.enabled = 1")
	public Page<ContAllPerson> findByEnabledPaged(Pageable pageable);

	/*/*@Query("Select u From DrtPersJuridica u where u.codigo=?1")*/
	/*@*/
	/*public DrtPersJuridica findByCodigo(String codigo);*/
}

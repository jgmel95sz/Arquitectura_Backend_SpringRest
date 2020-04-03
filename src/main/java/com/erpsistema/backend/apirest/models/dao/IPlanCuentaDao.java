package com.erpsistema.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erpsistema.backend.apirest.models.entity.PlanCuenta;


public interface IPlanCuentaDao extends JpaRepository<PlanCuenta, Integer> {
	
	public List<PlanCuenta> findByCodCuentaStartingWithIgnoreCase(String term);
	
	@Query("select u from PlanCuenta u where u.enabled = 1")
	public List<PlanCuenta> findByEnabledPaged();
	
	@Query("select u from PlanCuenta u where u.enabled = 1")
	public Page<PlanCuenta> findByEnabledPaged(Pageable pageable);
	

	public PlanCuenta findPlanCuentaByCodCuenta(String cod_cuenta);
	
}

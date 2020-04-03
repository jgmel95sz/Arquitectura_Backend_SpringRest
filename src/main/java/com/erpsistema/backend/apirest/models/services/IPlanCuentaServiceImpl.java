package com.erpsistema.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.erpsistema.backend.apirest.models.entity.Moneda;
import com.erpsistema.backend.apirest.models.entity.NivelCuenta;
import com.erpsistema.backend.apirest.models.entity.OpcionPlanCuenta;
import com.erpsistema.backend.apirest.models.entity.PlanCuenta;
import com.erpsistema.backend.apirest.models.entity.TipoAnalisis;
import com.erpsistema.backend.apirest.models.entity.TipoCuenta;

public interface IPlanCuentaServiceImpl {
	
	@Query("select u from PlanCuenta u where u.enabled = 1")
	public List<PlanCuenta> findByEnabledPaged();
	
	@Query("select u from PlanCuenta u where u.enabled = 1")
	public Page<PlanCuenta> findByEnabledPaged(Pageable pageable);
	
	public List<NivelCuenta> findNivelCuentaAll();
	
	public List<OpcionPlanCuenta> findOpcionPlanCuentaAll();
	
	public List<TipoAnalisis> findTipoAnalisisAll();
	
	public List<TipoCuenta> findTipoCuentaAll();
	
	public List<Moneda> findMonedaAll();
	
	public PlanCuenta save(PlanCuenta planCuenta);
	
	public PlanCuenta findById(Integer id);
}

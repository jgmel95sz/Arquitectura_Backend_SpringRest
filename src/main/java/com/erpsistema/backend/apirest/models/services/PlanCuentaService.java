package com.erpsistema.backend.apirest.models.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpsistema.backend.apirest.models.dao.IMonedaDao;
import com.erpsistema.backend.apirest.models.dao.INivelCuentaDao;
import com.erpsistema.backend.apirest.models.dao.IOpcionCuentaDao;
import com.erpsistema.backend.apirest.models.dao.IPlanCuentaDao;
import com.erpsistema.backend.apirest.models.dao.ITipoAnalisis;
import com.erpsistema.backend.apirest.models.dao.ITipoCuenta;
import com.erpsistema.backend.apirest.models.entity.Moneda;
import com.erpsistema.backend.apirest.models.entity.NivelCuenta;
import com.erpsistema.backend.apirest.models.entity.OpcionPlanCuenta;
import com.erpsistema.backend.apirest.models.entity.PlanCuenta;
import com.erpsistema.backend.apirest.models.entity.TipoAnalisis;
import com.erpsistema.backend.apirest.models.entity.TipoCuenta;

@Service
public class PlanCuentaService implements IPlanCuentaServiceImpl {

	private Logger logger = LoggerFactory.getLogger(PlanCuentaService.class);
	
	@Autowired
	private IPlanCuentaDao planCuentaDao;
	
	@Autowired
	private INivelCuentaDao nivelCuentaDao;
	
	@Autowired
	private IOpcionCuentaDao opcionCuentaDao;
	
	@Autowired
	private ITipoAnalisis tipoAnalisis;
	
	@Autowired
	private ITipoCuenta tipoCuenta;
	
	@Autowired
	private IMonedaDao moneda;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<PlanCuenta> findByEnabledPaged() {
		return planCuentaDao.findByEnabledPaged();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<PlanCuenta> findByEnabledPaged(Pageable pageable) {
		return planCuentaDao.findByEnabledPaged(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<NivelCuenta> findNivelCuentaAll() {
		return nivelCuentaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<OpcionPlanCuenta> findOpcionPlanCuentaAll() {
		return opcionCuentaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoAnalisis> findTipoAnalisisAll() {
		return tipoAnalisis.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoCuenta> findTipoCuentaAll() {
		return tipoCuenta.findAll();
	}

	@Override
	public PlanCuenta save(PlanCuenta planCuenta) {
		return planCuentaDao.save(planCuenta);
	}

	@Override
	@Transactional(readOnly = true)
	public PlanCuenta findById(Integer id) {
		return planCuentaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Moneda> findMonedaAll() {
		return moneda.findAll();
	}
	
	
	

}

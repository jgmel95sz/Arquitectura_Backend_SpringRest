package com.erpsistema.backend.apirest.models.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpsistema.backend.apirest.models.dao.ICentroCostoDao;
import com.erpsistema.backend.apirest.models.dao.IDocumento;
import com.erpsistema.backend.apirest.models.dao.IMedioDePago;
import com.erpsistema.backend.apirest.models.dao.IMesToJobDao;
import com.erpsistema.backend.apirest.models.dao.IOrigenDao;
import com.erpsistema.backend.apirest.models.dao.IParametrosAsisDao;
import com.erpsistema.backend.apirest.models.dao.IParametrosDao;
import com.erpsistema.backend.apirest.models.dao.IPeriodoAnioDao;
import com.erpsistema.backend.apirest.models.dao.IPeriodoDao;
import com.erpsistema.backend.apirest.models.dao.IPlanCuentaDao;
import com.erpsistema.backend.apirest.models.dao.ITipoCambio;
import com.erpsistema.backend.apirest.models.dao.IUnidadNegocio;
import com.erpsistema.backend.apirest.models.entity.CentroCosto;
import com.erpsistema.backend.apirest.models.entity.Documento;
import com.erpsistema.backend.apirest.models.entity.MedioDePago;
import com.erpsistema.backend.apirest.models.entity.MesToJob;
import com.erpsistema.backend.apirest.models.entity.Origen;
import com.erpsistema.backend.apirest.models.entity.Parametros;
import com.erpsistema.backend.apirest.models.entity.ParametrosAsis;
import com.erpsistema.backend.apirest.models.entity.Periodo;
import com.erpsistema.backend.apirest.models.entity.PeriodoAnio;
import com.erpsistema.backend.apirest.models.entity.PlanCuenta;
import com.erpsistema.backend.apirest.models.entity.TipoCambio;
import com.erpsistema.backend.apirest.models.entity.UnidadNegocio;

@Service
public class OtrosService implements IOtrosServiceImpl {
	
	private Logger logger = LoggerFactory.getLogger(ContAllPersonService.class);

	@Autowired
	private IPeriodoDao periodoDao;
	
	@Autowired
	private IPeriodoAnioDao periodoAnioDao;
	
	@Autowired
	private IMesToJobDao mesToJobDao;
	
	@Autowired
	private IUnidadNegocio unidadNegocioDao;
	
	@Autowired
	private ITipoCambio tipoCambioConect;
	
	@Autowired
	private IDocumento documentoConect;
	
	@Autowired
	private IMedioDePago medioDePagoConect;

	@Autowired
	private IOrigenDao origenDao;
	
	@Autowired
	private IParametrosAsisDao parametrosAsisDao;
	
	@Autowired
	private IParametrosDao parametrosDao;
	
	@Autowired
	private IPlanCuentaDao planCuentaDao;
	
	@Autowired
	private ICentroCostoDao centroCostoDao;
	
	@Override
	@Transactional(readOnly=true)
	public MedioDePago findMedioDePagoById(Integer id) {
		return medioDePagoConect.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<MedioDePago> findAll(Pageable pageable) {
		return medioDePagoConect.findAll(pageable);
	}

	@Override
	@Transactional
	public MedioDePago save(MedioDePago medioDePago) {
		return medioDePagoConect.save(medioDePago);
	}

	@Override
	@Transactional(readOnly=true)
	public TipoCambio findTipoCambioById(Integer id) {
		return tipoCambioConect.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<TipoCambio> findTipoCambioAll(Pageable pageable) {
		return tipoCambioConect.findAll(pageable);
	}

	@Override
	@Transactional
	public TipoCambio save(TipoCambio tipoCambio) {
		return tipoCambioConect.save(tipoCambio);
	}

	@Override
	@Transactional(readOnly=true)
	public Documento findDocumentooById(Integer id) {
		return documentoConect.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Documento> findDocumentoAll(Pageable pageable) {
		return documentoConect.findAll(pageable);
	}

	@Override
	@Transactional
	public Documento save(Documento documento) {
		return documentoConect.save(documento);
	}

	@Override
	public List<Origen> findAll() {
		return origenDao.findAll();
	}

	@Override
	public List<ParametrosAsis> findAllParametrosAsis() {
		return parametrosAsisDao.findAll();
	}

	@Override
	public Parametros findByParametrosId(Integer id) {
		return parametrosDao.findById(id).orElse(null);
	}

	@Override
	public List<PlanCuenta> findPlanCuentaByCodCuenta(String term) {
		return planCuentaDao.findByCodCuentaStartingWithIgnoreCase(term);
	}

	@Override
	public PlanCuenta findByPlanCuentaCodCuenta(String cod_cuenta) {
		return planCuentaDao.findPlanCuentaByCodCuenta(cod_cuenta);
	}

	@Override
	public ParametrosAsis saveParametrosAsis(ParametrosAsis parametrosAsis) {
		return parametrosAsisDao.save(parametrosAsis);
	}

	@Override
	public Parametros saveParametros(Parametros parametros) {
		return parametrosDao.save(parametros);
	}

	@Override
	public ParametrosAsis findByParametrosAsisId(Integer id) {
		return parametrosAsisDao.findById(id).orElse(null);
	}

	@Override
	public UnidadNegocio findByUnidadNegocioId(Integer id) {
		return unidadNegocioDao.findById(id).orElse(null);
	}

	@Override
	public Page<UnidadNegocio> findAllUnidadNegocio(Pageable pageable) {
		return unidadNegocioDao.findByEnabledPaged(pageable);
	}

	@Override
	public UnidadNegocio saveUnidadNegocio(UnidadNegocio unidadNegocio) {
		return unidadNegocioDao.save(unidadNegocio);
	}

	@Override
	public List<CentroCosto> findAllCentroCosto() {
		return centroCostoDao.findAll();
	}

	@Override
	public List<Periodo> findAllPeriodo() {
		return periodoDao.findAll();
	}

	@Override
	public Periodo savePeriodo(Periodo periodo) {
		return periodoDao.save(periodo);
	}

	@Override
	public Periodo findByPeriodoId(Integer id) {
		return periodoDao.findById(id).orElse(null);
	}

	@Override
	public List<MesToJob> findAllMesToJob() {
		return mesToJobDao.findAll();
	}

	@Override
	public PeriodoAnio savePeriodoAnio(PeriodoAnio periodoAnio) {
		return periodoAnioDao.save(periodoAnio);
	}

	@Override
	public PeriodoAnio findByPeriodoAnioId(Integer id) {
		return periodoAnioDao.findById(id).orElse(null);
	}

	@Override
	public List<PeriodoAnio> findAllPeriodoAnio() {
		return periodoAnioDao.findAll();
	}

	@Override
	public MesToJob saveMesToJob(MesToJob mesToJob) {
		return mesToJobDao.save(mesToJob);
	}

	@Override
	public MesToJob findByMesToJobId(Integer id) {
		return mesToJobDao.findById(id).orElse(null);
	}
	
}

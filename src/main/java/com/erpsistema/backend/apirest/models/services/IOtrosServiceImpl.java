package com.erpsistema.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

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

public interface IOtrosServiceImpl {

	public MedioDePago findMedioDePagoById(Integer id);
	public Page<MedioDePago> findAll(Pageable pageable);
	public MedioDePago save(MedioDePago medioDePago);
	
	public TipoCambio findTipoCambioById(Integer id);
	public Page<TipoCambio> findTipoCambioAll(Pageable pageable);
	public TipoCambio save(TipoCambio tipoCambio);
	
	public Documento findDocumentooById(Integer id);
	public Page<Documento> findDocumentoAll(Pageable pageable);
	public Documento save(Documento documento);
	
	public List<Origen> findAll();
	
	public List<ParametrosAsis> findAllParametrosAsis();
	public ParametrosAsis saveParametrosAsis(ParametrosAsis parametrosAsis);
	public ParametrosAsis findByParametrosAsisId(Integer id);
	
	public Parametros findByParametrosId(Integer id);
	public Parametros saveParametros(Parametros parametros);
	
	
	public PlanCuenta findByPlanCuentaCodCuenta(String cod_cuenta);
	
	public List<PlanCuenta> findPlanCuentaByCodCuenta(String term);
	
	public UnidadNegocio findByUnidadNegocioId(Integer id);
	
	@Query("select u from UnidadNegocio u where u.enabled = 1")
	public Page<UnidadNegocio> findAllUnidadNegocio(Pageable pageable);
	public UnidadNegocio saveUnidadNegocio(UnidadNegocio unidadNegocio);
	
	public List<CentroCosto> findAllCentroCosto();

	public List<Periodo> findAllPeriodo();
	public Periodo savePeriodo(Periodo periodo);
	public Periodo findByPeriodoId(Integer id);
	
	public List<MesToJob> findAllMesToJob();
	public MesToJob saveMesToJob(MesToJob mesToJob);
	public MesToJob findByMesToJobId(Integer id);
	
	public PeriodoAnio savePeriodoAnio(PeriodoAnio periodoAnio);
	public PeriodoAnio findByPeriodoAnioId(Integer id);
	public List<PeriodoAnio> findAllPeriodoAnio();	
}

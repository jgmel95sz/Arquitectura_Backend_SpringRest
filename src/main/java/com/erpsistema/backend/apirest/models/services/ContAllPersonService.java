package com.erpsistema.backend.apirest.models.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpsistema.backend.apirest.models.dao.IContAllPersonDao;
import com.erpsistema.backend.apirest.models.dao.IDrtPersJuridicaDao;
import com.erpsistema.backend.apirest.models.dao.ITipoAllPersonaDao;
import com.erpsistema.backend.apirest.models.dao.ITipoDocumentoDao;
import com.erpsistema.backend.apirest.models.entity.ContAllPerson;
import com.erpsistema.backend.apirest.models.entity.DrtPersJuridica;
import com.erpsistema.backend.apirest.models.entity.TipoAllPersona;
import com.erpsistema.backend.apirest.models.entity.TipoDocumento;
@Service
public class ContAllPersonService implements IContAllPersonServiceImpl {

	private Logger logger = LoggerFactory.getLogger(ContAllPersonService.class);
	
	@Autowired
	private IContAllPersonDao contAllPersonaDao;
	
	@Autowired
	private IDrtPersJuridicaDao drtPersJuridicaDao;
	
	@Autowired
	private ITipoAllPersonaDao tipoAllPersonaDao;
	
	@Autowired
	private ITipoDocumentoDao tipoDocumentoDao;
	

	@Override
	@Transactional(readOnly=true)
	public Page<ContAllPerson> findByEnabledPaged(Pageable pageable) {
		return contAllPersonaDao.findByEnabledPaged(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public ContAllPerson findById(Integer id) {
		return contAllPersonaDao.findById(id).orElse(null);
	}

	@Override
	public ContAllPerson save(ContAllPerson contAllPerson) {
		return contAllPersonaDao.save(contAllPerson);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TipoAllPersona> findTipoAllPersonaAll() {
		return tipoAllPersonaDao.findAll();
	}


	@Override
	@Transactional(readOnly=true)
	public List<TipoDocumento> findTipoDocumentoAll() {
		return tipoDocumentoDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Page<DrtPersJuridica> findByDrtPersJuridicaEnabledPaged(Pageable pageable) {
		return drtPersJuridicaDao.findByEnabledPaged(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public List<DrtPersJuridica> findDrtPersJuridicaAll() {
		return drtPersJuridicaDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public DrtPersJuridica findByCodigo(String codigo) {
		return drtPersJuridicaDao.buscarPorPersonaJuridica(codigo);
	}

	@Override
	@Transactional
	public DrtPersJuridica save(DrtPersJuridica drtPersJuridica) {
		return drtPersJuridicaDao.save(drtPersJuridica);
	}

	@Override
	public DrtPersJuridica findByDrtPersJuridicaId(Integer id) {
		return drtPersJuridicaDao.buscarPorPersonaJuridicaId(id);
	}

	
}

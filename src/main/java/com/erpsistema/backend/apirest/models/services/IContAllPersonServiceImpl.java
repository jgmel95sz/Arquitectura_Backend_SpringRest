package com.erpsistema.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.erpsistema.backend.apirest.models.entity.ContAllPerson;
import com.erpsistema.backend.apirest.models.entity.DrtPersJuridica;
import com.erpsistema.backend.apirest.models.entity.TipoAllPersona;
import com.erpsistema.backend.apirest.models.entity.TipoDocumento;

public interface IContAllPersonServiceImpl {
	
	@Query("select u from ContAllPerson u where u.enabled = 1")
	public Page<ContAllPerson> findByEnabledPaged(Pageable pageable);
	
	public ContAllPerson findById(Integer id);
	
	public ContAllPerson save(ContAllPerson contAllPerson);
	
	
	public List<TipoAllPersona> findTipoAllPersonaAll();
		
	public List<TipoDocumento> findTipoDocumentoAll();
	
	
	@Query("select u from DrtPersJuridica u where u.enabled = 1")
	public Page<DrtPersJuridica> findByDrtPersJuridicaEnabledPaged(Pageable pageable);
	
	public DrtPersJuridica findByDrtPersJuridicaId(Integer id);
	
	public List<DrtPersJuridica> findDrtPersJuridicaAll();
	
	public DrtPersJuridica findByCodigo(String codigo);
	
	public DrtPersJuridica save(DrtPersJuridica drtPersJuridica);
	
}

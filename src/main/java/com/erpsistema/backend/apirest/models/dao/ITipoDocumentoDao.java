package com.erpsistema.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erpsistema.backend.apirest.models.entity.TipoDocumento;

public interface ITipoDocumentoDao extends JpaRepository<TipoDocumento, Integer> {

}

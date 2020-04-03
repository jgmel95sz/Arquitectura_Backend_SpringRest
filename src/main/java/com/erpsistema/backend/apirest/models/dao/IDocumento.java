package com.erpsistema.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erpsistema.backend.apirest.models.entity.Documento;

public interface IDocumento extends JpaRepository<Documento,Integer>{

}

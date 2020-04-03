package com.erpsistema.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erpsistema.backend.apirest.models.entity.Parametros;

public interface IParametrosDao extends JpaRepository<Parametros , Integer> {

}

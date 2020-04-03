package com.erpsistema.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erpsistema.backend.apirest.models.entity.TipoAnalisis;

public interface ITipoAnalisis extends JpaRepository<TipoAnalisis, Integer> {

}

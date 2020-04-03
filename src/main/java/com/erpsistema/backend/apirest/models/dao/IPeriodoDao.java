package com.erpsistema.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erpsistema.backend.apirest.models.entity.Periodo;

public interface IPeriodoDao extends JpaRepository<Periodo, Integer> {

}

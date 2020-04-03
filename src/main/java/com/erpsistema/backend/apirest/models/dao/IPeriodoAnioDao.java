package com.erpsistema.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erpsistema.backend.apirest.models.entity.PeriodoAnio;

public interface IPeriodoAnioDao extends JpaRepository<PeriodoAnio, Integer> {

}

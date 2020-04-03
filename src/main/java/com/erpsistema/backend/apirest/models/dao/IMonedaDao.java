package com.erpsistema.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erpsistema.backend.apirest.models.entity.Moneda;

public interface IMonedaDao extends JpaRepository<Moneda, Integer> {

}

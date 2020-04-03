package com.erpsistema.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erpsistema.backend.apirest.models.entity.TipoCuenta;

public interface ITipoCuenta extends JpaRepository<TipoCuenta, Integer>{

}

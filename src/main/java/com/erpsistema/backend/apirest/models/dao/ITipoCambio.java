package com.erpsistema.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erpsistema.backend.apirest.models.entity.TipoCambio;

public interface ITipoCambio extends JpaRepository<TipoCambio,Integer> {

}

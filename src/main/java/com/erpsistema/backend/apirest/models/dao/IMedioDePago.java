package com.erpsistema.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erpsistema.backend.apirest.models.entity.MedioDePago;

public interface IMedioDePago extends JpaRepository<MedioDePago, Integer>{

}

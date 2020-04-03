package com.erpsistema.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erpsistema.backend.apirest.models.entity.NivelCuenta;

public interface INivelCuentaDao extends JpaRepository<NivelCuenta, Integer>{

}

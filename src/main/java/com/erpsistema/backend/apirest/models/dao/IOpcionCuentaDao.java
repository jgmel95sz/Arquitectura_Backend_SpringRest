package com.erpsistema.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erpsistema.backend.apirest.models.entity.OpcionPlanCuenta;

public interface IOpcionCuentaDao extends JpaRepository<OpcionPlanCuenta, Integer>{

}

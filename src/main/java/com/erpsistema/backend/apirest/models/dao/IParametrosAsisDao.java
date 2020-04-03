package com.erpsistema.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erpsistema.backend.apirest.models.entity.ParametrosAsis;

public interface IParametrosAsisDao  extends JpaRepository<ParametrosAsis,Integer> {

}

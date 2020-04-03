package com.erpsistema.backend.apirest.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;


import com.erpsistema.backend.apirest.models.entity.CentroCosto;

public interface ICentroCostoDao extends JpaRepository<CentroCosto, Integer> {

}

package com.erpsistema.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erpsistema.backend.apirest.models.entity.PerfilUsuario;

public interface IPerfilUsuario extends JpaRepository<PerfilUsuario, Integer>{

}

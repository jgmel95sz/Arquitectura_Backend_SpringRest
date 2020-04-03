package com.erpsistema.backend.apirest.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.erpsistema.backend.apirest.models.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Integer> {

	//List<Cliente> findAll();

}

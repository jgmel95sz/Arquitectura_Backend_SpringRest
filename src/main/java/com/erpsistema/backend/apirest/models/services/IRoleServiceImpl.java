package com.erpsistema.backend.apirest.models.services;

import java.util.List;

import com.erpsistema.backend.apirest.models.entity.Role;

public interface IRoleServiceImpl {
	public List<Role> findAll();
}

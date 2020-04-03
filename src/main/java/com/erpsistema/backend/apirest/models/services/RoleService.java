package com.erpsistema.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpsistema.backend.apirest.models.dao.IRoleDao;
import com.erpsistema.backend.apirest.models.entity.Role;

@Service
public class RoleService implements IRoleServiceImpl {
	
	@Autowired
	private IRoleDao roleDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	
	
}

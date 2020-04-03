package com.erpsistema.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpsistema.backend.apirest.models.entity.Role;
import com.erpsistema.backend.apirest.models.services.IRoleServiceImpl;

@RestController
@RequestMapping("/roles")
public class RoleRestController {

	@Autowired
	private IRoleServiceImpl roleService;
	
	//@Secured({"ROLE_ADMIN"})
		@GetMapping("/lista")
		public List<Role> index(){
			return roleService.findAll();
		}
}

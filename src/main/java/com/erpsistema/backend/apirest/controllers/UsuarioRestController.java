package com.erpsistema.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpsistema.backend.apirest.models.entity.Usuario;
import com.erpsistema.backend.apirest.models.services.IUsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/lista")
	public List<Usuario> index(){
		return usuarioService.findByEnabledPaged();
	}
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/lista/page/{page}")
	public Page<Usuario> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 15);
		return usuarioService.findByEnabledPaged(pageable);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/usuario/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id){
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = null;
		try {
			usuario = usuarioService.findById(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(usuario == null) {
			response.put("mensaje","El usuario con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/agregar") 
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		Usuario usuarioNew = null;
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			usuarioNew = usuarioService.save(usuario);
			
			
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Usuario fue creado con éxito");
		response.put("cliente",usuario);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/editar/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		Usuario usuarioActual = usuarioService.findById(id);
		if(usuarioActual == null) {
			response.put("mensaje","Error: no se pudo editar, el usuario con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		Usuario update = null;
		try {
			update = usuarioService.save(usuarioActual);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El cliente fue actualizado con éxito");
		response.put("cliente",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpsistema.backend.apirest.models.entity.ContAllPerson;
import com.erpsistema.backend.apirest.models.entity.DrtPersJuridica;
import com.erpsistema.backend.apirest.models.entity.TipoAllPersona;
import com.erpsistema.backend.apirest.models.entity.TipoDocumento;
import com.erpsistema.backend.apirest.models.services.IContAllPersonServiceImpl;

@RestController
@RequestMapping("/proveedor")
public class ProveedorRestController {

	@Autowired
	private IContAllPersonServiceImpl contAllPerson;
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/lista-proveedores/page/{page}")
	public Page<ContAllPerson> conAllPersonPage(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 15);
		return contAllPerson.findByEnabledPaged(pageable);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/lista-clientes/page/{page}")
	public Page<DrtPersJuridica> cliente(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 15);
		return contAllPerson.findByDrtPersJuridicaEnabledPaged(pageable);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/tipo-all-persona")
	public List<TipoAllPersona> tipoAllPersonaList(){
		return contAllPerson.findTipoAllPersonaAll();
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/tipo-documento")
	public List<TipoDocumento> tipoDocumentoList(){
		return contAllPerson.findTipoDocumentoAll();
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/ver-dato-persona/{codigo}")
	public ResponseEntity<?> show(@PathVariable String codigo){
		Map<String, Object> response = new HashMap<>();
		DrtPersJuridica drtPersJuridica = null;
		try {
			drtPersJuridica = contAllPerson.findByCodigo(codigo);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("error_data_1",e.getMessage());			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(drtPersJuridica == null) {
			response.put("mensaje","El Código ".concat(codigo.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<DrtPersJuridica>(drtPersJuridica, HttpStatus.OK);
	}
		
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/agregar-proveedores") 
	public ResponseEntity<?> agregarProveedores(@Valid @RequestBody ContAllPerson contAllPersonVar, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage()).collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		ContAllPerson contAllPersonNew = null;	
		try {
			contAllPersonNew = contAllPerson.save(contAllPersonVar);
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Provedor fue creado con éxito");
		response.put("proveedores",contAllPersonVar);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/editar-proveedores/{id}")
	public ResponseEntity<?> actualizarProveedores(@Valid @RequestBody ContAllPerson contAllPersonVar, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo'" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		ContAllPerson contAllPersonaActual = contAllPerson.findById(id);
		if(contAllPersonaActual == null) {
			response.put("mensaje","Error: no se pudo editar, el Proveedor con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		ContAllPerson update = null;
		try {
			update = contAllPerson.save(contAllPersonVar);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Proveedor fue actualizado con éxito");
		response.put("cliente",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/agregar-clientes") 
	public ResponseEntity<?> agregarClientes(@Valid @RequestBody DrtPersJuridica drtPersJuridicaVar, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors().stream().map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage()).collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		DrtPersJuridica drtPersJuridicaNew = null;	
		try {
			drtPersJuridicaNew = contAllPerson.save(drtPersJuridicaVar);
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Cliente fue creado con éxito");
		response.put("proveedores",drtPersJuridicaVar);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/editar-clientes/{id}")
	public ResponseEntity<?> actualizarClientes(@Valid @RequestBody DrtPersJuridica drtPersJuridicaVar, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo'" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		DrtPersJuridica drtPersJuridicaActual = contAllPerson.findByDrtPersJuridicaId(id);
		if(drtPersJuridicaActual == null) {
			response.put("mensaje","Error: no se pudo editar, el Cliente con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		DrtPersJuridica update = null;
		try {
			update = contAllPerson.save(drtPersJuridicaVar);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Proveedor fue actualizado con éxito");
		response.put("cliente",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}

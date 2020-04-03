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

import com.erpsistema.backend.apirest.models.entity.Moneda;
import com.erpsistema.backend.apirest.models.entity.NivelCuenta;
import com.erpsistema.backend.apirest.models.entity.OpcionPlanCuenta;
import com.erpsistema.backend.apirest.models.entity.PlanCuenta;
import com.erpsistema.backend.apirest.models.entity.TipoAnalisis;
import com.erpsistema.backend.apirest.models.entity.TipoCuenta;
import com.erpsistema.backend.apirest.models.services.IPlanCuentaServiceImpl;

@RestController
@RequestMapping("/plan-cuenta")
public class PlanCuentaRestController {
	
	@Autowired
	private IPlanCuentaServiceImpl planCuenta;
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/lista")
	public List<PlanCuenta> index(){
		return planCuenta.findByEnabledPaged();
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/lista/page/{page}")
	public Page<PlanCuenta> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 30);
		return planCuenta.findByEnabledPaged(pageable);
	}	
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/nivel-cuenta")
	public List<NivelCuenta> nivelCuenta(){
		return planCuenta.findNivelCuentaAll();
	}

	@Secured({"ROLE_ADMIN"})
	@GetMapping("/moneda")
	public List<Moneda> moneda(){
		return planCuenta.findMonedaAll();
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/opcion-cuenta")
	public List<OpcionPlanCuenta> opcionCuenta(){
		return planCuenta.findOpcionPlanCuentaAll();
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/tipo-analisis")
	public List<TipoAnalisis> tipoAnalisis(){
		return planCuenta.findTipoAnalisisAll();
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/tipo-cuenta")
	public List<TipoCuenta> tipoCuenta(){
		return planCuenta.findTipoCuentaAll();
	}	
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/agregar") 
	public ResponseEntity<?> create(@Valid @RequestBody PlanCuenta planCuentaVar, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		PlanCuenta planCuentaNew = null;
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			response.put("respuesta_error",planCuentaVar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			planCuentaNew = planCuenta.save(planCuentaVar);
			
			
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("respuesta Servidor",planCuentaVar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Plan de cuenta fue creado con éxito");
		response.put("cliente",planCuentaVar);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/editar/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody PlanCuenta planCuentaVar, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		PlanCuenta planCuentaActual = planCuenta.findById(id);
		if(planCuentaActual == null) {
			response.put("mensaje","Error: no se pudo editar, el Plan de Cuenta con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		PlanCuenta update = null;
		try {
			update = planCuenta.save(planCuentaVar);
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

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.erpsistema.backend.apirest.models.entity.CentroCosto;
import com.erpsistema.backend.apirest.models.entity.Documento;
import com.erpsistema.backend.apirest.models.entity.MedioDePago;
import com.erpsistema.backend.apirest.models.entity.MesToJob;
import com.erpsistema.backend.apirest.models.entity.Origen;
import com.erpsistema.backend.apirest.models.entity.Parametros;
import com.erpsistema.backend.apirest.models.entity.ParametrosAsis;
import com.erpsistema.backend.apirest.models.entity.Periodo;
import com.erpsistema.backend.apirest.models.entity.PeriodoAnio;
import com.erpsistema.backend.apirest.models.entity.PlanCuenta;
import com.erpsistema.backend.apirest.models.entity.TipoCambio;
import com.erpsistema.backend.apirest.models.entity.UnidadNegocio;
import com.erpsistema.backend.apirest.models.services.IOtrosServiceImpl;

@RestController
@RequestMapping("/otro")
public class OtroRestController {

	@Autowired
	private IOtrosServiceImpl otrosService;

	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/editar-mes-to-job/{id}")
	public ResponseEntity<?> updateMesToJob(@Valid @RequestBody MesToJob mesToJob, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		MesToJob mesToJob_obj = otrosService.findByMesToJobId(id);
		if(mesToJob_obj == null) {
			response.put("mensaje","Error: no se pudo editar, el Mes de Trabajo con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		MesToJob update = null;
		try {
			update = otrosService.saveMesToJob(mesToJob);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Mes de Trabajo fue actualizado con éxito");
		response.put("documento",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/mes-to-job")
	public List<MesToJob> mesToJob(){
		return otrosService.findAllMesToJob();
	}	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/periodo-anio")
	public List<PeriodoAnio> periodoAnio(){
		return otrosService.findAllPeriodoAnio();
	}	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/periodo-anio/{id}")
	public ResponseEntity<?> periodoAnio(@PathVariable Integer id){
		Map<String, Object> response = new HashMap<>();
		PeriodoAnio periodoAnio = null;
		try {
			periodoAnio = otrosService.findByPeriodoAnioId(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));		
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(periodoAnio == null) {
			response.put("mensaje","El Id ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<PeriodoAnio>(periodoAnio, HttpStatus.OK);
	}
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/agregar-eriodo-anio") 
	public ResponseEntity<?> createPeriodoAnio(@Valid @RequestBody PeriodoAnio periodoAnio, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		PeriodoAnio periodoAnio_obj = null;
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			response.put("respuesta_error",periodoAnio);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			periodoAnio_obj = otrosService.savePeriodoAnio(periodoAnio);
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("respuesta Servidor",periodoAnio);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Periodo Anio fue creado con éxito");
		response.put("documento",periodoAnio);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/editar-periodo-anio/{id}")
	public ResponseEntity<?> updatePeriodoAnio(@Valid @RequestBody PeriodoAnio periodoAnio, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		PeriodoAnio periodo_obj = otrosService.findByPeriodoAnioId(id);
		if(periodo_obj == null) {
			response.put("mensaje","Error: no se pudo editar, el Periodo Anio con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		PeriodoAnio update = null;
		try {
			update = otrosService.savePeriodoAnio(periodoAnio);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Periodo Anio fue actualizado con éxito");
		response.put("documento",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/periodo")
	public List<Periodo> periodo(){
		return otrosService.findAllPeriodo();
	}	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/agregar-periodo") 
	public ResponseEntity<?> createPeriodo(@Valid @RequestBody Periodo periodo, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		Periodo periodo_obj = null;
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			response.put("respuesta_error",periodo);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			periodo_obj = otrosService.savePeriodo(periodo);
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("respuesta Servidor",periodo);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Periodo fue creado con éxito");
		response.put("documento",periodo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/editar-periodo/{id}")
	public ResponseEntity<?> updatePeriodo(@Valid @RequestBody Periodo periodo, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		Periodo periodo_obj = otrosService.findByPeriodoId(id);
		if(periodo_obj == null) {
			response.put("mensaje","Error: no se pudo editar, el Periodo con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		Periodo update = null;
		try {
			update = otrosService.savePeriodo(periodo);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Periodo fue actualizado con éxito");
		response.put("documento",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/filtrar-codigo/{term}")
	@ResponseStatus(HttpStatus.OK)
	public List<PlanCuenta> filtrarProductos(@PathVariable String term){
		return otrosService.findPlanCuentaByCodCuenta(term);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/filtrar-codigo-cod/{plan}")
	@ResponseStatus(HttpStatus.OK)
	public PlanCuenta filtrarProductosCodigo(@PathVariable String plan){
		return otrosService.findByPlanCuentaCodCuenta(plan);
	}
	
	@GetMapping("/origen")
	public List<Origen> origen(){
		return otrosService.findAll();
	}
	
	@GetMapping("/centro-costo")
	public List<CentroCosto> centroCosto(){
		return otrosService.findAllCentroCosto();
	}
	
	@GetMapping("/parametros-asis")
	public List<ParametrosAsis> paramatrosAsis(){
		return otrosService.findAllParametrosAsis();
	}
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/agregar-parametros-asis") 
	public ResponseEntity<?> createParametrosAsis(@Valid @RequestBody ParametrosAsis parametrosAsis, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		ParametrosAsis parametros_asis = null;
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			response.put("respuesta_error",parametrosAsis);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			parametros_asis = otrosService.saveParametrosAsis(parametrosAsis);
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("respuesta Servidor",parametrosAsis);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Parametro Asistente fue creado con éxito");
		response.put("documento",parametrosAsis);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/editar-parametros-asis/{id}")
	public ResponseEntity<?> updateParametrosAsistencia(@Valid @RequestBody ParametrosAsis parametrosAsis, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		ParametrosAsis parametrosAsis_ob = otrosService.findByParametrosAsisId(id);
		if(parametrosAsis_ob == null) {
			response.put("mensaje","Error: no se pudo editar, el Parametro Asistente con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		ParametrosAsis update = null;
		try {
			update = otrosService.saveParametrosAsis(parametrosAsis);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Parametro Asistente fue actualizado con éxito");
		response.put("documento",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/parametro/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id){
		Map<String, Object> response = new HashMap<>();
		Parametros parametros = null;
		try {
			parametros = otrosService.findByParametrosId(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(parametros == null) {
			response.put("mensaje","El parametro con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Parametros>(parametros, HttpStatus.OK);
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/agregar-parametros") 
	public ResponseEntity<?> createParametros(@Valid @RequestBody Parametros parametros, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		Parametros parametros_obj = null;
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			response.put("respuesta_error",parametros);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			parametros_obj = otrosService.saveParametros(parametros);
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("respuesta Servidor",parametros);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Parametro fue creado con éxito");
		response.put("documento",parametros);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}	
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/editar-parametros/{id}")
	public ResponseEntity<?> updateParametros(@Valid @RequestBody Parametros parametros, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		Parametros parametros_ob = otrosService.findByParametrosId(id);
		if(parametros_ob == null) {
			response.put("mensaje","Error: no se pudo editar, el Parametro con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		Parametros update = null;
		try {
			update = otrosService.saveParametros(parametros);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Parametro fue actualizado con éxito");
		response.put("documento",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/lista-documento/page/{page}")
	public Page<Documento> documento(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 15);
		return otrosService.findDocumentoAll(pageable);
	}

	@Secured({"ROLE_ADMIN"})
	@GetMapping("/lista-unidad-negocio/page/{page}")
	public Page<UnidadNegocio> unidadNegocio(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 15);
		return otrosService.findAllUnidadNegocio(pageable);
	}
	

	@Secured({"ROLE_ADMIN"})
	@PostMapping("/agregar-unidad-negocio") 
	public ResponseEntity<?> createUnidadNegocio(@Valid @RequestBody UnidadNegocio unidadNegocio, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		UnidadNegocio unidad_negocio_obj = null;
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			response.put("respuesta_error",unidadNegocio);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			unidad_negocio_obj = otrosService.saveUnidadNegocio(unidadNegocio);
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("respuesta Servidor",unidadNegocio);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","La Unidad de Negocio fue creado con éxito");
		response.put("documento",unidadNegocio);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}	
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/editar-unidad-negocio/{id}")
	public ResponseEntity<?> updateUnidadNegocio(@Valid @RequestBody UnidadNegocio unidadNegocio, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		UnidadNegocio unidad_negocio_ob = otrosService.findByUnidadNegocioId(id);
		if(unidad_negocio_ob == null) {
			response.put("mensaje","Error: no se pudo editar, el Parametro con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		UnidadNegocio update = null;
		try {
			update = otrosService.saveUnidadNegocio(unidadNegocio);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","La unidad de negocio fue actualizado con éxito");
		response.put("documento",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	
	
	
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/lista-medio-de-pago/page/{page}")
	public Page<MedioDePago> medioDePago(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 15);
		return otrosService.findAll(pageable);
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/lista-tipo-de-cambio/page/{page}")
	public Page<TipoCambio> tipoCambio(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 15);
		return otrosService.findTipoCambioAll(pageable);
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping("/agregar-documento") 
	public ResponseEntity<?> createDocumento(@Valid @RequestBody Documento documentoVar, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		Documento documentoNew = null;
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			response.put("respuesta_error",documentoVar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			documentoNew = otrosService.save(documentoVar);
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("respuesta Servidor",documentoVar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Documento fue creado con éxito");
		response.put("documento",documentoVar);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/editar-documento/{id}")
	public ResponseEntity<?> updateDocumento(@Valid @RequestBody Documento documentoVar, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		Documento documentoActual = otrosService.findDocumentooById(id);
		if(documentoActual == null) {
			response.put("mensaje","Error: no se pudo editar, el Plan de Cuenta con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		Documento update = null;
		try {
			update = otrosService.save(documentoVar);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Documento fue actualizado con éxito");
		response.put("documento",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/agregar-medio-de-pago") 
	public ResponseEntity<?> createMedioDePago(@Valid @RequestBody MedioDePago medioDePagoVar, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		MedioDePago medioDepagoNew = null;
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			response.put("respuesta_error",medioDePagoVar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			medioDepagoNew = otrosService.save(medioDePagoVar);
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("respuesta Servidor",medioDePagoVar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Medio de Pago fue creado con éxito");
		response.put("medio_de_pago",medioDePagoVar);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/editar-medio-de-pago/{id}")
	public ResponseEntity<?> updateMedioDePago(@Valid @RequestBody MedioDePago medioDePagoVar, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		MedioDePago medioDePagoActual = otrosService.findMedioDePagoById(id);
		if(medioDePagoActual == null) {
			response.put("mensaje","Error: no se pudo editar, el Plan de Cuenta con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		MedioDePago update = null;
		try {
			update = otrosService.save(medioDePagoVar);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Medio de Pago fue actualizado con éxito");
		response.put("medio_de_pago",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/agregar-tipo-de-cambio") 
	public ResponseEntity<?> createTipoDeCambio(@Valid @RequestBody TipoCambio tipoCambioVar, BindingResult result){
		Map<String, Object> response = new HashMap<>();
		TipoCambio tipoCambioNew = null;
		if(result.hasErrors()) {			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			response.put("respuesta_error",tipoCambioVar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			tipoCambioNew = otrosService.save(tipoCambioVar);
		}catch(DataAccessException e) {
			response.put("mensaje","Error en el Servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			response.put("respuesta Servidor",tipoCambioVar);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Tipo de Cambio fue creado con éxito");
		response.put("tipo_de_cambio",tipoCambioVar);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/editar-tipo-de-cambio/{id}")
	public ResponseEntity<?> updateTipoDeCambio(@Valid @RequestBody TipoCambio tipoCambioVar, BindingResult result, @PathVariable Integer id){	
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' :" +err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors",errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		TipoCambio tipoCambioActual = otrosService.findTipoCambioById(id);
		if(tipoCambioActual == null) {
			response.put("mensaje","Error: no se pudo editar, el Plan de Cuenta con el ID ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		TipoCambio update = null;
		try {
			update = otrosService.save(tipoCambioVar);
		}
		catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en el servidor");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","El Tipo de Cambio fue actualizado con éxito");
		response.put("tipo_de_cambio",update);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}

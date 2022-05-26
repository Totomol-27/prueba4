package com.isita.ccapper.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.isita.ccapper.app.entity.Pantalla;
import com.isita.ccapper.app.entity.PantallaServicio;
import com.isita.ccapper.app.service.PantallaServicioService;

@RestController
@RequestMapping("/pantallaservicio")
public class PantallaServicioController {
	
	@Autowired
	private PantallaServicioService pantallaServicioService;
	/**
	 * 
	 * @param id
	 * @param tipoConsulta
	 * @return retorna row de un id en esfecifico
	 */
	@GetMapping("/{id}")
	public PantallaServicio  findById(@PathVariable Long id,@RequestParam String tipoConsulta) {
			PantallaServicio  pantallaServicio = new PantallaServicio();
			pantallaServicio.setId(id);
			pantallaServicio = pantallaServicioService.findById(pantallaServicio, tipoConsulta);
		return pantallaServicio;
	}
	/**
	 * 
	 * @param id
	 * @param tipoConsulta
	 * @return retorna row de un id en esfecifico
	 */
	@GetMapping("pantalla/{idPantalla}")
	public PantallaServicio  findServiciosDePantalla(@PathVariable Long idPantalla,@RequestParam String tipoConsulta) {
		PantallaServicio  pantallaServicio = new PantallaServicio();
		Pantalla pantalla = new Pantalla();
		pantalla.setId(idPantalla);
		pantallaServicio.setPantalla(pantalla);
		return pantallaServicioService.buscarServiciosDePantalla(pantallaServicio, tipoConsulta);
	}
	/**
	 * 
	 * @param pantallaServicio
	 * @return el id del registro creado
	 */
	@PostMapping("/create")
 	@ResponseStatus(HttpStatus.CREATED)
 	public PantallaServicio save(@RequestBody PantallaServicio pantallaServicio) {
 		System.out.println("-----------------------> crear pantalla servicio ");
		return pantallaServicioService.save(pantallaServicio);
 	}
	/**
	 * Actualiza los registros de pantalla servicio
	 * @param pantallaServicio
	 */
	@PutMapping("/update")
 	public void update(@RequestBody PantallaServicio pantallaServicio) {
 		this.pantallaServicioService.update(pantallaServicio);
 	}
	/**
	 * 
	 * @param listaServicios
	 * @return elimia los servicios asignados a una pantalla
	 */
	@DeleteMapping(value="/{listaServicios}")
 	public ResponseEntity<?> delete(@PathVariable String listaServicios) {
 		Map<String, Object> response = new HashMap<>();
 		try {
 			this.pantallaServicioService.delete(listaServicios);
 		}catch(DataAccessException e) {
 			response.put("mensaje", "Error al eliminar los servicios en la base de datos ");
 			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
 			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
 		}
 		response.put("Mensaje","El cliente eliminado con éxito");
 		 return new  ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
 	}
 	
	
}

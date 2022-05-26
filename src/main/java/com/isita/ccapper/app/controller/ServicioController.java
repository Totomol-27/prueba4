package com.isita.ccapper.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.isita.ccapper.app.entity.Servicio;
import com.isita.ccapper.app.model.MensajeModel;
import com.isita.ccapper.app.service.ServicioService;

@RestController
@RequestMapping("/servicio")
public class ServicioController {

	@Autowired
	private ServicioService servicioService;
		
	@GetMapping("/paginadoServicio")
	public List<Servicio> findAll(Servicio servicio,Integer page, Integer sizes, String tipoConsulta) {
		return servicioService.findAll(servicio, page, sizes, tipoConsulta);
	}
	
	@PostMapping("/createServicio")
 	@ResponseStatus(HttpStatus.CREATED)
	public Servicio save(@RequestBody Servicio servicio) {
		return servicioService.save(servicio);	
	} 
	@PutMapping("/updateServicio")
	public MensajeModel actuliza(@RequestBody Servicio servicio) {
		return servicioService.actuliza(servicio);
	}
	
	@DeleteMapping(value = "/deleteServicio")
	public void delete(Servicio servicio, Long id) {
       servicioService.delete(servicio);
	}
	
	@GetMapping("/cargarServicios")
	public List<Servicio> caragarServicios(@RequestParam String uri,@RequestParam  Integer pagina, @RequestParam  Integer tamanio, @RequestParam  String tipoConsulta) {
		return servicioService.buscarServicios(uri, pagina, tamanio, tipoConsulta);
	}
	
}

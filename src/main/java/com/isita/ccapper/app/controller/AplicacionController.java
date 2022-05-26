package com.isita.ccapper.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isita.ccapper.app.entity.Aplicacion;
import com.isita.ccapper.app.service.AplicacionService;

@RestController
@RequestMapping("/aplicacion")
public class AplicacionController {
	
	@Autowired
	private AplicacionService aplicacionService;
	
	@PostMapping("/crear")
	public Aplicacion save(Aplicacion aplicacion) {
		return aplicacionService.save(aplicacion);
	}
	
	@GetMapping("/listaAplicacion")
	public List<Aplicacion> listaAplicacion(Aplicacion aplicacion, Integer page, Integer sizes, String tipoConsulta) {
		return aplicacionService.listaAplicacion(aplicacion, page, sizes, tipoConsulta);
	}
	
	@PutMapping()
	public void update(Aplicacion aplicacion) {
		this.aplicacionService.update(aplicacion);
	}
	
	@DeleteMapping(value="/{id}")
	public void  delete(@PathVariable Long id) {
		this.aplicacionService.delete(id);
	}
}

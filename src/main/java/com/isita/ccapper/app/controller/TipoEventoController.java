package com.isita.ccapper.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.isita.ccapper.app.entity.TipoEvento;
import com.isita.ccapper.app.service.TipoEventoService;

@RestController
@RequestMapping("/tipoEvento")
public class TipoEventoController {
	
	@Autowired
	private TipoEventoService service;
	
	@GetMapping("/consultaTipoEvento")
	public List<TipoEvento> findAll(TipoEvento tipoEvento, Integer page, Integer sizes, String tipoConsulta){
		return service.findAll(tipoEvento, page, sizes, tipoConsulta);
	}
	
	@PostMapping("/createTipoEvento")
 	@ResponseStatus(HttpStatus.CREATED)
	public TipoEvento save(TipoEvento tipoEvento) {
		return service.save(tipoEvento);	
	} 
	@PutMapping("/updateTipoEvento")
	public void actuliza(TipoEvento tipoEvento) {
		service.actuliza(tipoEvento);
	}
	
	@DeleteMapping(value = "/deleteTipoEvento")
	public void delete(TipoEvento tipoEvento) {
		service.delete(tipoEvento);
	}

}

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

import com.isita.ccapper.app.entity.TipoSala;
import com.isita.ccapper.app.service.TipoSalaService;

@RestController
@RequestMapping("/tipoSala")
public class TipoSalaController {

	@Autowired
	private TipoSalaService service;
	
	@GetMapping("/consultaTipoSala")
	public List<TipoSala> findAll(TipoSala tipoSala,Integer page, Integer sizes, String tipoConsulta) {
		return service.findAll(tipoSala, page, sizes, tipoConsulta);
	}
	
	@PostMapping("/createTipoSala")
 	@ResponseStatus(HttpStatus.CREATED)
	public TipoSala save(TipoSala tipoSala) {
		return service.save(tipoSala);	
	} 
	@PutMapping("/updateTipoSala")
	public void actuliza(TipoSala tipoSala) {
		service.actuliza(tipoSala);
	}
	
	@DeleteMapping(value = "/deleteTipoSala")
	public void delete(TipoSala tipoSala) {
		service.delete(tipoSala);
	}
}

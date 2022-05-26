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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.isita.ccapper.app.entity.Sala;
import com.isita.ccapper.app.service.SalaService;

@RestController
@RequestMapping("/sala")
public class SalaController {

	@Autowired
	private SalaService service;
	
	@GetMapping("/consultaSala")
	public List<Sala> findAll(Sala sala,Integer page, Integer sizes, String tipoConsulta) {
		return service.findAll(sala, page, sizes, tipoConsulta);
	}
	
	@PostMapping("/createSala")
 	@ResponseStatus(HttpStatus.CREATED)
	public Sala save(@RequestBody Sala sala) {
		return service.save(sala);	
	} 
	@PutMapping("/updateSala")
	public void actuliza(@RequestBody Sala sala) {
		service.actuliza(sala);
	}
	
	@DeleteMapping(value = "/deleteSala")
	public void delete(Sala sala) {
		service.delete(sala);
	}
}

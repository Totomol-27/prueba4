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

import com.isita.ccapper.app.entity.EstatusSala;
import com.isita.ccapper.app.service.EstatusSalaService;


@RestController
@RequestMapping("/estatusSala")
public class EstatusSalaController {

	@Autowired
	private EstatusSalaService estatusSalaService;
	
	@GetMapping("/consultaSala")
	public List<EstatusSala> findAll(EstatusSala estatusSala,Integer page, Integer sizes, String tipoConsulta) {
		return estatusSalaService.findAll(estatusSala, page, sizes, tipoConsulta);
	}
	
	@PostMapping("/createSala")
 	@ResponseStatus(HttpStatus.CREATED)
	public EstatusSala save(EstatusSala estatusSala) {
		return estatusSalaService.save(estatusSala);	
	} 
	@PutMapping("/updateSala")
	public void actuliza(EstatusSala estatusSala) {
		estatusSalaService.actuliza(estatusSala);
	}
	
	@DeleteMapping(value = "/deleteSala")
	public void delete(EstatusSala estatusSala) {
		estatusSalaService.delete(estatusSala);
	}
	
}

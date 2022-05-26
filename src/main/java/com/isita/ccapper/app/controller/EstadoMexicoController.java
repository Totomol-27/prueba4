package com.isita.ccapper.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isita.ccapper.app.entity.EstatadosMexico;
import com.isita.ccapper.app.service.EstadoMexicoService;

@RestController
@RequestMapping("/estado")
public class EstadoMexicoController {

	
	@Autowired
	private EstadoMexicoService services;
	
//	@RequestMapping( method = RequestMethod.GET, produces = "application/json")
	@GetMapping("/consultaEstados")
	public List<EstatadosMexico> findAll(EstatadosMexico estatadosMexico, String tipoConsulta) {
		return services.findAll(estatadosMexico, tipoConsulta);
	}
}

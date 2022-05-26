package com.isita.ccapper.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isita.ccapper.app.entity.Cuestionario;
import com.isita.ccapper.app.service.CuestionarioService;

@RestController
@RequestMapping("/cuestionario")
public class CuestionarioController {

	@Autowired
	private CuestionarioService service;
	
	@RequestMapping("/consultaCuestionario")
	public List<Cuestionario> finAll(Cuestionario cuestionario, String tipoConsulta){
		return service.findAll(cuestionario, tipoConsulta);
	}
	
}

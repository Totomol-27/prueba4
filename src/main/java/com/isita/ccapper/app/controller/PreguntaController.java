package com.isita.ccapper.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isita.ccapper.app.entity.Pregunta;
import com.isita.ccapper.app.model.TipoPreguntaModelo;
import com.isita.ccapper.app.service.PreguntaService;
@CrossOrigin(origins = { "http://localhost:4200"})
@RestController
@RequestMapping("/pregunta")
public class PreguntaController {

	
	@Autowired
	private PreguntaService service;
	
	@RequestMapping("/consultaPregunta")
	public List<Pregunta> findAll(Pregunta pregunta, String tipoConsulta){
		List<Pregunta> preg = service.findAll(pregunta, tipoConsulta);
		return preg;
	}
	
	@RequestMapping("/consultaTipoPregunta")
	public List<TipoPreguntaModelo> finAlls(Pregunta pregunta, String tipoConsulta){
		return service.findAlls(pregunta, tipoConsulta);
	}
}

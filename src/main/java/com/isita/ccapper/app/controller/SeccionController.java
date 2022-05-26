package com.isita.ccapper.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isita.ccapper.app.entity.Seccion;
import com.isita.ccapper.app.service.SeccionService;

@RestController
@RequestMapping("/seccion")
public class SeccionController {
	
	@Autowired
	private SeccionService service;
	
	@RequestMapping("/consultaSeccion")
	public List<Seccion> findAll(Seccion seccion, String tipoConsulta){
		return service.findAll(seccion, tipoConsulta);
	}
}

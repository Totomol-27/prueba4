package com.isita.ccapper.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.isita.ccapper.app.entity.RespuestaOpcion;
import com.isita.ccapper.app.service.RespuestaOpcionService;


@RestController
@RequestMapping("/respuestaOpcion")
public class RespuestaOpcionController {
	
	@Autowired
	private RespuestaOpcionService service;
	
	@GetMapping("/consultaRespuestaOpcion")
	public List<RespuestaOpcion> findAll(RespuestaOpcion respuestaOpcion , String tipoConsulta) {
		return service.findAll(respuestaOpcion, tipoConsulta);
	}
	
	@PostMapping("/createRespuestaOpcion")
 	@ResponseStatus(HttpStatus.CREATED)
	public RespuestaOpcion save(@RequestBody List<RespuestaOpcion> respuestaOpcion) {
		return service.save(respuestaOpcion);	
	} 
	/*
	@PutMapping("/updateRespuestaOpcion")
	public void actuliza(@RequestBody RespuestaOpcion respuestaOpcion) {
		service.actuliza(respuestaOpcion);
	}
	*/
}

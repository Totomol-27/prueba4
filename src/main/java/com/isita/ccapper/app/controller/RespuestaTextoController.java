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

import com.isita.ccapper.app.entity.RespuestaTexto;
import com.isita.ccapper.app.service.RespuestaTextoService;

@RestController
@RequestMapping("/respuestaTexto")
public class RespuestaTextoController {
	
	@Autowired
	private RespuestaTextoService service;
	
	@GetMapping("/consultaRespuestaTexto")
	public List<RespuestaTexto> findAll(RespuestaTexto respuestaTexto , String tipoConsulta) {
		return service.findAll(respuestaTexto, tipoConsulta);
	}
	
	@PostMapping("/createRespuestaTexto")
 	@ResponseStatus(HttpStatus.CREATED)
	public RespuestaTexto save( @RequestBody List<RespuestaTexto> respuestaTexto) {
		return service.save(respuestaTexto);	
	} 
	/*
	@PutMapping("/updateRespuestaTexto")
	public void actuliza(@RequestBody RespuestaTexto respuestaTexto) {
		service.actuliza(respuestaTexto);
	}
	*/

}

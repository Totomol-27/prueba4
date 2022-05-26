package com.isita.ccapper.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isita.ccapper.app.entity.Credencial;
import com.isita.ccapper.app.service.CredencialService;

@RestController
@RequestMapping("/credencial")
public class CredencialController {
	
	@Autowired
	private CredencialService credencialService;
	
	@PostMapping("/createtoken")
	public Credencial  savetok(@RequestBody Credencial token) {
		return  credencialService.save(token);
	}
	
	@GetMapping("/usuario")
	public Credencial findByUserId(@RequestBody Credencial credencial, @RequestParam String tipoConsulta) {
		return credencialService.findByUserId(credencial, tipoConsulta);
	}
}

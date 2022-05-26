package com.isita.ccapper.app.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.isita.ccapper.app.entity.Perfil;
import com.isita.ccapper.app.service.PerfilService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/perfil")
public class PerfilesController {
	
	@Autowired
	private PerfilService perfilService;
	
	@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
	@RequestMapping(value = "/perfiles", method = RequestMethod.GET, produces = "application/json")
	public List<Perfil> findAll(Perfil perfil,Integer page, Integer sizes, String tipoConsulta) {
		return perfilService.findAll(perfil, page, sizes, tipoConsulta);
	}

	public Optional<Perfil> findById(Long id) {
		return null;
	}

}

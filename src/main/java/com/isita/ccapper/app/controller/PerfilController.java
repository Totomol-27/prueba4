package com.isita.ccapper.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.isita.ccapper.app.service.PerfilService;
import com.isita.ccapper.app.entity.Perfil;
import com.isita.ccapper.app.model.MensajeModel;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/perfil")
public class PerfilController {
		
	@Autowired
	private PerfilService perfilService;
	
	@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
	@RequestMapping( method = RequestMethod.GET, produces = "application/json")
	public List<Perfil> findAll(Perfil perfil,Integer page, Integer sizes, String tipoConsulta) {
		return perfilService.findAll(perfil, page, sizes, tipoConsulta);
	}
	
	@PostMapping("/create")
 	@ResponseStatus(HttpStatus.CREATED)
	public Perfil save(Perfil perfil) {
		return perfilService.save(perfil);	
	}
	
	@PutMapping("/mod")
	public MensajeModel actuliza(Perfil perfil) {
		return perfilService.actuliza(perfil);
	}
	
	@CrossOrigin(origins = "http://localhost:4200", methods= {RequestMethod.DELETE})
	@RequestMapping( value = "/delete{id}", method = RequestMethod.DELETE, produces = "application/json")
	public MensajeModel delete(Perfil perfil, Long id) {
		System.out.println(id);
		return perfilService.delete(perfil);
	}
}

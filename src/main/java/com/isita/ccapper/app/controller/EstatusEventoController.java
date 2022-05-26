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

import com.isita.ccapper.app.entity.EstatusEvento;
import com.isita.ccapper.app.service.EstatusEventoService;


@RestController
@RequestMapping("/estatusEvento")
public class EstatusEventoController {
	@Autowired
	private EstatusEventoService estatusEventoService;
	
	@GetMapping("/consultaEvento")
	public List<EstatusEvento> findAll(EstatusEvento estatusEvento,Integer page, Integer sizes, String tipoConsulta) {
		return estatusEventoService.findAll(estatusEvento, page, sizes, tipoConsulta);
	}
	
	@PostMapping("/createEvento")
 	@ResponseStatus(HttpStatus.CREATED)
	public EstatusEvento save(EstatusEvento estatusEvento) {
		return estatusEventoService.save(estatusEvento);	
	} 
	@PutMapping("/updateEvento")
	public void actuliza(EstatusEvento estatusEvento) {
		estatusEventoService.actuliza(estatusEvento);
	}
	
	@DeleteMapping(value = "/deleteEvento")
	public void delete(EstatusEvento estatusEvento) {
		estatusEventoService.delete(estatusEvento);
	}
}

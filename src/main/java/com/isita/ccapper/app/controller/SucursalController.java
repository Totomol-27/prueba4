package com.isita.ccapper.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.isita.ccapper.app.entity.Sucursal;
import com.isita.ccapper.app.service.SucursalService;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {

	@Autowired
	private SucursalService service;
	
	@GetMapping("/consultaSucursal")
	public List<Sucursal> findAll(Sucursal sucursal,Integer page, Integer sizes, String tipoConsulta) {
		return service.findAll(sucursal, page, sizes, tipoConsulta);
	}
	
}

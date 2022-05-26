package com.isita.ccapper.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isita.ccapper.app.entity.Permiso;
import com.isita.ccapper.app.service.PermisoService;

@RestController
@RequestMapping("/permiso")
public class PermisoController {
	
	@Autowired
	private PermisoService permisoService;
	
	@GetMapping("/listaPermiso")
	public List<Permiso> listaPermisos(Permiso permiso, Integer page, Integer sizes, String tipoConsulta){
		return permisoService.listaPermiso(permiso, page, sizes, tipoConsulta);
	}
}

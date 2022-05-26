package com.isita.ccapper.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.entity.Seccion;
import com.isita.ccapper.app.interfaces.ISeccionService;
import com.isita.ccapper.app.repository.SeccionRepository;

@Service
public class SeccionService implements ISeccionService {

	@Autowired
	private SeccionRepository repository;
	
	@Override
	public List<Seccion> findAll(Seccion seccion, String tipoConsulta) {
		return repository.findAll(seccion, tipoConsulta);
	}
	
}

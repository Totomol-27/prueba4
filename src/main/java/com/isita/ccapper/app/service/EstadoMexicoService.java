package com.isita.ccapper.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.entity.EstatadosMexico;
import com.isita.ccapper.app.interfaces.IEstadoService;
import com.isita.ccapper.app.repository.EstadoMexicoRepository;

@Service
public class EstadoMexicoService implements IEstadoService{
	
	@Autowired
	private EstadoMexicoRepository repository;

	@Override
	public List<EstatadosMexico> findAll(EstatadosMexico estatadosMexico, String tipoConsulta) {
		return repository.findAll(estatadosMexico, tipoConsulta);
	}
}

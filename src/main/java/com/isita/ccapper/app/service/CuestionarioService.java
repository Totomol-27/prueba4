package com.isita.ccapper.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.entity.Cuestionario;
import com.isita.ccapper.app.interfaces.ICuestionarioService;
import com.isita.ccapper.app.repository.CuestionarioRepository;

@Service
public class CuestionarioService implements ICuestionarioService {
	
	@Autowired
	private CuestionarioRepository repository;

	@Override
	public List<Cuestionario> findAll(Cuestionario cuestionario, String tipoConsulta) {
		return repository.findAll(cuestionario, tipoConsulta);
	}

}

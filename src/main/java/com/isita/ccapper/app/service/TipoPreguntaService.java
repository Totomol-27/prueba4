package com.isita.ccapper.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.entity.Pregunta;
import com.isita.ccapper.app.interfaces.ITipoPreguntaService;
import com.isita.ccapper.app.model.TipoPreguntaModelo;
import com.isita.ccapper.app.repository.PreguntaRepository;

@Service
public class TipoPreguntaService implements ITipoPreguntaService {

	@Autowired
	private PreguntaRepository repository;
	
	@Override
	public List<TipoPreguntaModelo> findAlls(Pregunta pregunta, String tipoConsulta) {
		return repository.findAlls(pregunta, tipoConsulta);
	}

}

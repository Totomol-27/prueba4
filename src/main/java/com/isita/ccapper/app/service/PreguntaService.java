package com.isita.ccapper.app.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.entity.Pregunta;
import com.isita.ccapper.app.entity.TipoPregunta;
import com.isita.ccapper.app.interfaces.IPreguntaService;
import com.isita.ccapper.app.model.TipoPreguntaModelo;
import com.isita.ccapper.app.repository.PreguntaRepository;

@Service
public class PreguntaService implements IPreguntaService {

	@Autowired
	private PreguntaRepository repository;

	@Override
	public List<Pregunta> findAll(Pregunta pregunta, String tipoConsulta) {
		return repository.consultaPregunta(pregunta, tipoConsulta);
	}

	@Override
	public List<TipoPreguntaModelo> findAlls(Pregunta pregunta, String tipoConsulta) {
		
		Pregunta pre = new Pregunta();
		List<Pregunta> preguntas = this.repository.consultaPregunta(pregunta, tipoConsulta);	
		List<TipoPregunta> tipopreguntas = this.repository.tipopregunta(tipoConsulta);
		
		HashMap <String,String> preguntasV = new HashMap<String, String>();
		preguntasV.put(pre.getPregunta(), pre.getPregunta());
		pregunta.getId();
		preguntas.size();
		return null;
	}

}

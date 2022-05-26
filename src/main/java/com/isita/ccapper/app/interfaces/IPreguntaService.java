package com.isita.ccapper.app.interfaces;

import java.util.List;

import com.isita.ccapper.app.entity.Pregunta;
import com.isita.ccapper.app.model.TipoPreguntaModelo;

public interface IPreguntaService {
	public List<Pregunta> findAll(Pregunta pregunta, String tipoConsulta);
	public List<TipoPreguntaModelo> findAlls(Pregunta pregunta,String tipoConsulta);
}

package com.isita.ccapper.app.interfaces;

import java.util.List;

import com.isita.ccapper.app.entity.Pregunta;
import com.isita.ccapper.app.model.TipoPreguntaModelo;

public interface ITipoPreguntaService {

	public List<TipoPreguntaModelo> findAlls(Pregunta pregunta,String tipoConsulta);
}

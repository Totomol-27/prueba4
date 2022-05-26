package com.isita.ccapper.app.interfaces;

import java.util.List;

import com.isita.ccapper.app.entity.Cuestionario;

public interface ICuestionarioService {
		
	public List<Cuestionario> findAll(Cuestionario cuestionario, String tipoConsulta);
}

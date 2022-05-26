package com.isita.ccapper.app.interfaces;

import java.util.List;

import com.isita.ccapper.app.entity.EstatadosMexico;


public interface IEstadoService {
	
	public List<EstatadosMexico> findAll(EstatadosMexico estatadosMexico, String tipoConsulta);
}

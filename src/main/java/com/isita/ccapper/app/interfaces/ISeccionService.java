package com.isita.ccapper.app.interfaces;

import java.util.List;

import com.isita.ccapper.app.entity.Seccion;

public interface ISeccionService {

	public List<Seccion> findAll(Seccion seccion, String tipoConsulta);
}

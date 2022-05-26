package com.isita.ccapper.app.interfaces;

import java.util.List;

import com.isita.ccapper.app.entity.RespuestaOpcion;

public interface IRespuestaOpcionService {

	public List<RespuestaOpcion> findAll(RespuestaOpcion respuestaOpcion, String tipoConsulta);
	
	public RespuestaOpcion save(List<RespuestaOpcion> respuestaOpcion);
	/*
	public void actuliza(RespuestaOpcion respuestaOpcion);
*/
}

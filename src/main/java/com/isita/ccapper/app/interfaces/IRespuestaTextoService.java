package com.isita.ccapper.app.interfaces;

import java.util.List;

import com.isita.ccapper.app.entity.RespuestaTexto;

public interface IRespuestaTextoService {
	
public List<RespuestaTexto> findAll(RespuestaTexto respuestaTexto, String tipoConsulta);
	
	public RespuestaTexto save(List<RespuestaTexto> listaRespuestaTexto);
	/*
	public void actuliza(RespuestaTexto respuestaTexto);
*/
}

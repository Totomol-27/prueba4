package com.isita.ccapper.app.interfaces;

import java.util.List;

import com.isita.ccapper.app.entity.TipoEvento;

public interface ITipoEventoService {
	
	public List<TipoEvento> findAll(TipoEvento tipoEvento, Integer page, Integer sizes, String tipoConsulta);
	
	public TipoEvento save(TipoEvento tipoEvento);
	
	public void actuliza(TipoEvento tipoEvento);
	
	public void delete(TipoEvento tipoEvento);

}

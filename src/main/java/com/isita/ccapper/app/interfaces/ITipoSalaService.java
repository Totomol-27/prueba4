package com.isita.ccapper.app.interfaces;

import java.util.List;
import com.isita.ccapper.app.entity.TipoSala;

public interface ITipoSalaService {
	
	public List<TipoSala> findAll(TipoSala tipoSala,Integer page,Integer sizes, String tipoConsulta);
		
	public TipoSala save(TipoSala tipoSala);
	
	public void actuliza(TipoSala tipoSala);
	
	public void delete(TipoSala tipoSala);
}

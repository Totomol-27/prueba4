package com.isita.ccapper.app.interfaces;

import java.util.List;
import com.isita.ccapper.app.entity.EstatusEvento;

public interface IEstatusEventoService {

	public List<EstatusEvento> findAll(EstatusEvento estatusEvento,Integer page,Integer sizes, String tipoConsulta);
	
	public EstatusEvento save(EstatusEvento estatusEvento);
	
	public void actuliza(EstatusEvento estatusEvento);
	
	public void delete(EstatusEvento estatusEvento);
}

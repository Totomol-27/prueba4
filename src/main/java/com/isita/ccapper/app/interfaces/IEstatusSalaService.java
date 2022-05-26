package com.isita.ccapper.app.interfaces;

import java.util.List;
import com.isita.ccapper.app.entity.EstatusSala;

public interface IEstatusSalaService {

	public List<EstatusSala> findAll(EstatusSala estatusSala,Integer page,Integer sizes, String tipoConsulta);
	
	public EstatusSala save(EstatusSala estatusSala);
	
	public void actuliza(EstatusSala estatusSala);
	
	public void delete(EstatusSala estatusSala);
}

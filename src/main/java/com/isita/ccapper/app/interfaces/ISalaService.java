package com.isita.ccapper.app.interfaces;

import java.util.List;

import com.isita.ccapper.app.entity.Sala;

public interface ISalaService {

	public List<Sala> findAll(Sala sala,Integer page,Integer sizes, String tipoConsulta);
	
	public Sala save(Sala sala);
	
	public void actuliza(Sala sala);
	
	public void delete(Sala sala);
}

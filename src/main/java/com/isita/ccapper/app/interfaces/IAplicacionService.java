package com.isita.ccapper.app.interfaces;

import java.util.List;

import com.isita.ccapper.app.entity.Aplicacion;

public interface IAplicacionService {
	public  Aplicacion save(Aplicacion aplicacion);
	public Aplicacion findAplicacionById(Long id, String tipoConsulta);
	public List<Aplicacion> listaAplicacion(Aplicacion aplicacion, Integer page, Integer sizes, String tipoConsulta);
	public void update(Aplicacion  aplicacion);
	public void delete(Long id);
	
	public List<Aplicacion> lstAplicacion(String tipoConsulta);

}

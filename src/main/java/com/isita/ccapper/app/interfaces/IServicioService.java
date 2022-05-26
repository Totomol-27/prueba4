package com.isita.ccapper.app.interfaces;

import java.util.List;

import com.isita.ccapper.app.entity.Servicio;
import com.isita.ccapper.app.model.MensajeModel;

public interface IServicioService {

	public List<Servicio> findAll(Servicio servicio,Integer page,Integer sizes, String tipoConsulta);
	
	public Servicio save(Servicio servicio);
	
	public MensajeModel actuliza(Servicio servicio);
	
	public Servicio updatePerfil(Servicio servicio);
	
	public void delete(Servicio servicio);
	
	public List<Servicio> buscarPermiso(Servicio servicio, String tipoConsulta);
	
	public List<Servicio> buscarServicios(String uri,Integer pagina,Integer tamanio, String tipoConsulta);
}

package com.isita.ccapper.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.entity.Servicio;
import com.isita.ccapper.app.interfaces.IServicioService;
import com.isita.ccapper.app.model.MensajeModel;
import com.isita.ccapper.app.repository.ServicioRepository;
import com.isita.ccapper.app.util.Constantes;

@Service("serviceServicio")
public class ServicioService implements IServicioService {

	@Autowired
	private ServicioRepository servicioRepository;
	
	@Override
	public List<Servicio> findAll(Servicio servicio, Integer page, Integer sizes, String tipoConsulta) {
		return servicioRepository.findAll(servicio, page, sizes, tipoConsulta);
	}

	@Override
	public Servicio save(Servicio servicio) {
		return servicioRepository.save(servicio);
	}

	@Override
	public MensajeModel actuliza(Servicio servicio) {
		return servicioRepository.actuliza(servicio);
	}

	@Override
	public Servicio updatePerfil(Servicio servicio) {
		return null;
	}

	@Override
	public void delete(Servicio servicio) {
	 servicioRepository.delete(servicio);
	}

	@Override
	public List<Servicio> buscarPermiso(Servicio servicio, String tipoConsulta) {
		return servicioRepository.buscarServicio(servicio,Constantes.NUMERO_CERO,Constantes.NUMERO_CERO,tipoConsulta);
	}
	
	public List<Servicio> buscarServicios(Servicio servicio, String tipoConsulta) {
		return servicioRepository.buscarServicio(servicio,Constantes.NUMERO_CERO,Constantes.NUMERO_CERO,tipoConsulta);
	}

	@Override
	public List<Servicio> buscarServicios(String uri, Integer pagina, Integer tamanio, String tipoConsulta) {
		Servicio servicio = new Servicio();
		servicio.setUri(uri);
		return servicioRepository.buscarServicio(servicio, pagina, tamanio, tipoConsulta);
	}
	

}

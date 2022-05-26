package com.isita.ccapper.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isita.ccapper.app.entity.Aplicacion;
import com.isita.ccapper.app.interfaces.IAplicacionService;
import com.isita.ccapper.app.repository.AplicacionRepository;
/**
 * Class: Service aplicacion
 * */
@Service
public class AplicacionService implements IAplicacionService {
	
	
	@Autowired
	private AplicacionRepository aplicacionRepository; 
	
	@Override
	public Aplicacion save(Aplicacion aplicacion) {
		return aplicacionRepository.save(aplicacion);
	}

	@Override
	public void update(Aplicacion aplicacion) {
		this.aplicacionRepository.update(aplicacion);
	}
	
	@Override
	public void delete(Long id) {
		this.aplicacionRepository.delete(id);
	}

	@Override
	public List<Aplicacion> listaAplicacion(Aplicacion aplicacion,Integer page, Integer sizes, String tipoConsulta) {
		return aplicacionRepository.listaAplicacion(aplicacion, page, sizes, tipoConsulta);
	}

	@Override
	public Aplicacion findAplicacionById(Long id, String tipoConsulta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aplicacion> lstAplicacion(String tipoConsulta) {
		// TODO Auto-generated method stub
		return null;
	}
	

}

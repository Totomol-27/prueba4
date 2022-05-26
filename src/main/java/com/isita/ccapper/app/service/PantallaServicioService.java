package com.isita.ccapper.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.authentication.MissingHeaderInfoException;
import com.isita.ccapper.app.entity.Pantalla;
import com.isita.ccapper.app.entity.PantallaServicio;
import com.isita.ccapper.app.entity.Servicio;
import com.isita.ccapper.app.interfaces.IPantallaServicioService;
import com.isita.ccapper.app.repository.PantallaServicioRepository;
import com.isita.ccapper.app.util.Constantes;

@Service
public class PantallaServicioService implements IPantallaServicioService {
	@Autowired
	private PantallaServicioRepository pantallaServicioRepository;
	
	@Autowired
	private ServicioService servicioService;
	/**
	 * Búsca pantalla servicio por id
	 */
	@Override
	public PantallaServicio findById(PantallaServicio pantallaServicio, String tipoConsulta) {
		List<PantallaServicio> listaPantallaServicio= pantallaServicioRepository.buscarPantallaServicio(pantallaServicio, tipoConsulta);
		return listaPantallaServicio.get(0);
	}
	/**
	 * busca servicios asigandos a la pantalla
	 */
	@Override
	public List<PantallaServicio> buscarServicios(PantallaServicio pantallaServicio, String tipoConsulta) {
		return pantallaServicioRepository.buscarPantallaServicio(pantallaServicio, tipoConsulta);
	}
	/**
	 * guarda asigancion de servicios a pantalla
	 */
	@Override
	public PantallaServicio save(PantallaServicio pantallaServicio) {
		List<PantallaServicio> listaServiciosExisentes= validarSiExisteServicioAgregado(pantallaServicio);
		PantallaServicio objPantallaServicio = new PantallaServicio();
		if(listaServiciosExisentes.size()==0) {
			this.asignarServiciosApantalla(pantallaServicio);
		}else {
			objPantallaServicio =this.retornarServiciosExistentes(listaServiciosExisentes);
			throw new MissingHeaderInfoException("00001,"+objPantallaServicio.getIdServicio());
		}
		
		return objPantallaServicio;
	}
	/**
	 * elimina una asigancion de servicios a pantalla
	 * */
	@Override
	public void delete(String listaServicios) {
		this.pantallaServicioRepository.eliminarPantallaServicio(listaServicios);
	}
	/**
	 * 
	 * actualiza datos de pantalla servicio
	 */
	@Override
	public void update(PantallaServicio pantallaServicio) {
		this.pantallaServicioRepository.ModificarPantallaServicio(pantallaServicio);
	}
	@Override
	public PantallaServicio buscarServiciosDePantalla(PantallaServicio pantallaServicio, String tipoConsulta) {
		Servicio servicio = new Servicio();
		Pantalla pantalla = new Pantalla();
		pantalla.setId(pantallaServicio.getPantalla().getId());
		servicio.setPantalla(pantalla);
		List<Servicio> listaServicio =this.servicioService.buscarServicios(servicio, tipoConsulta);
		pantallaServicio.setServiciosPantalla(listaServicio);
		return pantallaServicio;
	}
	
	private List<PantallaServicio> validarSiExisteServicioAgregado(PantallaServicio pantallaServicio){
		String [] arrayIdServicio = pantallaServicio.getIdServicio().split(",");
		pantallaServicio.getPantalla().getId();
		List<PantallaServicio> listaServiciosExisentes = new ArrayList<PantallaServicio>();
		List<PantallaServicio> serviciosExisentes = new ArrayList<PantallaServicio>();
		PantallaServicio restPantallaServicio = new  PantallaServicio();
		for(int i=0;i<arrayIdServicio.length;i++) {
			PantallaServicio objPantallaServicio = new PantallaServicio();
			Servicio servicio = new Servicio();
			Pantalla pantallas = new Pantalla();
			servicio.setId(Long.parseLong(arrayIdServicio[i]));
			objPantallaServicio.setServicio(servicio);
			pantallas.setId(pantallaServicio.getPantalla().getId());
			objPantallaServicio.setPantalla(pantallas);
			serviciosExisentes =this.pantallaServicioRepository.buscarPantallaServicio(objPantallaServicio, Constantes.TIPO_CONSULTA_L2);
			if(serviciosExisentes.size()>0) {
				restPantallaServicio = serviciosExisentes.get(0);
				listaServiciosExisentes.add(restPantallaServicio);
			}
			serviciosExisentes = new ArrayList<PantallaServicio>();
		}
		return listaServiciosExisentes;
	}
	
	private PantallaServicio retornarServiciosExistentes(List<PantallaServicio> listaServiciosExisentes) {
		StringBuilder uris = new StringBuilder();
		PantallaServicio PantallaServicio = new  PantallaServicio();
		for(int j=0;j<listaServiciosExisentes.size();j++) {
				uris.append(uris.length()!=0?","+listaServiciosExisentes.get(j).getServicio().getId().toString():
											 listaServiciosExisentes.get(j).getServicio().getId().toString());
			
		}
		PantallaServicio.setIdServicio(uris.toString());
		return PantallaServicio;
	}
	
	private PantallaServicio asignarServiciosApantalla(PantallaServicio pantallaServicio) {
		Pantalla pantalla = new Pantalla();
		Servicio servicio = new Servicio();
		String [] arrayIdServicio = pantallaServicio.getIdServicio().split(",");
		PantallaServicio objPantallaServicio = new PantallaServicio();

		for(int i=0;i<arrayIdServicio.length;i++) {
			pantalla.setId(pantallaServicio.getPantalla().getId());
			pantallaServicio.setPantalla(pantalla);
			servicio.setId(Long.parseLong(arrayIdServicio[i]));
			pantallaServicio.setServicio(servicio);
			servicio = new Servicio();
			pantallaServicio.setEstatus(Constantes.NUMERO_CERO);
			objPantallaServicio =this.pantallaServicioRepository.savePantallaServicio(pantallaServicio);
		}
		
		return objPantallaServicio;
	}
}

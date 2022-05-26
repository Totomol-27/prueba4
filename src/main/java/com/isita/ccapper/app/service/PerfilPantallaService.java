package com.isita.ccapper.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.authentication.MissingHeaderInfoException;
import com.isita.ccapper.app.authentication.RecordNotFoundException;
import com.isita.ccapper.app.entity.Pantalla;
import com.isita.ccapper.app.entity.PantallaServicio;
import com.isita.ccapper.app.entity.Perfil;
import com.isita.ccapper.app.entity.PerfilPantalla;
import com.isita.ccapper.app.interfaces.IPerfilPantallaService;
import com.isita.ccapper.app.repository.PerfilPantallaRepository;
import com.isita.ccapper.app.util.Constantes;

@Service
public class PerfilPantallaService implements IPerfilPantallaService{
	
	@Autowired
	private PerfilPantallaRepository perfilPantallaRepository;
	
	@Autowired
	private PantallaService PantallaService;
	
	public List<PerfilPantalla> buscarPantalla(PerfilPantalla perfilPantalla, String tipoConsulta) {
		List<PerfilPantalla> perfilPantallas  =  perfilPantallaRepository.buscarPerfilPantalla(perfilPantalla, tipoConsulta);
		return perfilPantallas;
	}

	@Override
	public PerfilPantalla findById(Long id,  String tipoConsulta) {
		PerfilPantalla PerfilPantalla = new PerfilPantalla();
		PerfilPantalla.setId(id);
		List<PerfilPantalla> ListaPerfilPantalla =perfilPantallaRepository.buscarPerfilPantalla(PerfilPantalla, tipoConsulta);
		if(ListaPerfilPantalla.size()>0) {
			PerfilPantalla = ListaPerfilPantalla.get(0);
		}
		return PerfilPantalla;
	}

	@Override
	public PerfilPantalla LoadPantallasDePerfil(Long idPerfil, String tipoConsulta) {
		List<Pantalla> listaPantalla = PantallaService.buscarPantallasPorIdPerfil(idPerfil.intValue(), tipoConsulta);
		PerfilPantalla perfilPantalla = new PerfilPantalla();
		perfilPantalla.setPantallasPerfil(listaPantalla);
		return perfilPantalla;
	}

	@Override
	public PerfilPantalla save(PerfilPantalla perfilPantalla) {
		 List<PerfilPantalla> listaPantallas = validarPantallaPerfilSiExiste(perfilPantalla);
		 if(listaPantallas.size()==0) {
			String idPantallas[] = perfilPantalla.getIdPantallas().split(",");
			Pantalla pantalla = new Pantalla();
			Perfil perfil = new Perfil();
			for(int i=0;i<idPantallas.length;i++) {
				pantalla.setId(Long.parseLong(idPantallas[i]));
				perfilPantalla.setPantalla(pantalla);
				perfil.setEstatus(Constantes.NUMERO_CERO);
				this.perfilPantallaRepository.savePerfilPantalla(perfilPantalla);
			}
		 }else {
			throw new MissingHeaderInfoException("00002,"+this.pantallaPerfilExistente(listaPantallas));
		 }
		
		return perfilPantalla;
	}

	

	@Override
	public void delete(String listaPantallas, Long perfilId) {
		this.perfilPantallaRepository.eliminarPerfilPantalla(listaPantallas,perfilId);
	}

	@Override
	public void update(PerfilPantalla perfilPantalla) {
		this.perfilPantallaRepository.modificarPerfilPantalla(perfilPantalla);
	}
	
	private List<PerfilPantalla> validarPantallaPerfilSiExiste(PerfilPantalla perfilPantalla) {
		List<PerfilPantalla> listapantallas = new ArrayList<PerfilPantalla>();
		List<PerfilPantalla> pantallasExistentes = new ArrayList<PerfilPantalla>();
		String idPantallas[] = perfilPantalla.getIdPantallas().split(",");
		Pantalla pantalla = new Pantalla();
		PerfilPantalla  rowExistenteperfilPantalla = new PerfilPantalla();
		Perfil perfil = new Perfil();
		for(int i=0;i<idPantallas.length;i++) {
			pantalla.setId(Long.parseLong(idPantallas[i]));
			perfilPantalla.setPantalla(pantalla);
			perfil.setEstatus(Constantes.NUMERO_CERO);
			listapantallas = this.perfilPantallaRepository.buscarPerfilPantalla(perfilPantalla, Constantes.TIPO_CONSULTA_L3);
			if(listapantallas.size()>0) {
			Pantalla rowPantalla =	this.PantallaService.findById(listapantallas.get(0).getPantalla().getId(), Constantes.TIPO_CONSULTA_C1);
			if(rowPantalla!=null) {
				listapantallas.get(0).getPantalla().setNombre(rowPantalla.getNombre());
			}
				rowExistenteperfilPantalla = listapantallas.get(0);
				pantallasExistentes.add(rowExistenteperfilPantalla); 
			}
			listapantallas = new ArrayList<PerfilPantalla>();
		}
		return pantallasExistentes;
	}
	
	private String  pantallaPerfilExistente(List<PerfilPantalla> perfilPantalla){
		
		StringBuilder pantallas = new StringBuilder();
		PerfilPantalla PantallaServicio = new  PerfilPantalla();
		for(int j=0;j<perfilPantalla.size();j++) {
			pantallas.append(pantallas.length()!=0?","+perfilPantalla.get(j).getPantalla().getNombre():
				perfilPantalla.get(j).getPantalla().getNombre());
			
		}
		System.out.print("-----------------> "+pantallas.toString());
	
		return pantallas.toString();
	}
}

package com.isita.ccapper.app.service;

import com.isita.ccapper.app.interfaces.IPantallaService;
import com.isita.ccapper.app.repository.PantallaRepository;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.authentication.MissingHeaderInfoException;
import com.isita.ccapper.app.entity.Pantalla;
import com.isita.ccapper.app.entity.Perfil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PantallaService implements IPantallaService{

    @Autowired
    private PantallaRepository pantallaRepository;
    private List<Pantalla> pantalla = new ArrayList<>();


    @Override
    public void delete(String pantallas){

        pantallaRepository.actualizarPantalla(pantallas);
    }

    @Override
    public Pantalla save(Pantalla pantalla){
        return pantallaRepository.savePantalla(pantalla);

    }

    @Override
	public List<Pantalla> findAll(Integer pagina, Integer tamanio, String tipoConsulta) {
		// TODO Auto-generated method stub
		return pantallaRepository.findAll(pagina, tamanio, tipoConsulta);
	}
	

	@Override
	public Pantalla findById(Long id, String tipo_consulta) {
		System.out.println("valores de la consulta "+id+" valor 2 "+tipo_consulta);
        Integer IdPAntalla = (int)(long)id;  
        Pantalla pantalla = pantallaRepository.buscarPantallaId(IdPAntalla, tipo_consulta);
        return pantalla;
	
	}

	@Override
	public void update(Pantalla pantalla, String tipoModificacion) {
		List<Pantalla> findRegistro = this.pantallaRepository.findAll(0, 0, Constantes.TIPO_CONSULTA_L6);
		if(findRegistro.size() > 0) {
			throw new MissingHeaderInfoException(Constantes.CODIGO_MENSAJE+", ");
		} else {
			this.pantallaRepository.modificarPantalla(pantalla, tipoModificacion);
		}
		
	}

	@Override
	public List<Pantalla> findByName(String nombre, String tipoConsulta) {
		// TODO Auto-generated method stub
		return pantallaRepository.findByName(nombre, tipoConsulta);
	}

	@Override
	public List<Pantalla> buscarPantallasPorIdPerfil(Integer idPerfil, String tipoConsulta) {
		Pantalla pantalla = new Pantalla();
		Perfil perfil = new  Perfil();
		perfil.setId(idPerfil);
		pantalla.setPerfil(perfil);
		return pantallaRepository.buscarPantallas(pantalla, 0, 0, tipoConsulta);
	}

	@Override
	public List<Pantalla> buscarPantallaClave(String clave, String tipoConsulta) {
		Pantalla pantalla = new Pantalla();
		pantalla.setClave(clave);
		return pantallaRepository.buscarPantallas(pantalla, 0, 0, tipoConsulta);
	}

}

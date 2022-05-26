package com.isita.ccapper.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.entity.Perfil;
import com.isita.ccapper.app.interfaces.IPerfilService;
import com.isita.ccapper.app.model.MensajeModel;
import com.isita.ccapper.app.repository.PerfilRepository;

@Service 
public class PerfilService implements IPerfilService {

	@Autowired
	private PerfilRepository perfilRepository;
	
	@Override
	public List<Perfil> findAll(Perfil perfil,Integer page, Integer size, String tipoConsulta) {
		return perfilRepository.findAll(perfil, page, size, tipoConsulta);
	}

	@Override
	public Optional<Perfil> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Perfil> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public Perfil save(Perfil perfil) {
		return perfilRepository.save(perfil);
	}

	@Override
	public MensajeModel actuliza(Perfil perfil) {
		return perfilRepository.actuliza(perfil);
	}

	
	@Override
	public Perfil deletePerfil(List<Perfil> perfil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MensajeModel delete(Perfil perfil) {
		System.out.print(perfil.getId());
		return perfilRepository.delete(perfil);
	}

	@Override
	public Perfil updatePerfil(Perfil instance) {
		return perfilRepository.updatePerfil(instance);
	}
	
	public List<Perfil> buscarPerfilesDeUsuario(Perfil perfil, String tipoConsulta) {
		return perfilRepository.findAll(perfil, null, null, tipoConsulta);
	}

}

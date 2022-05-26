package com.isita.ccapper.app.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.isita.ccapper.app.entity.Perfil;
import com.isita.ccapper.app.model.MensajeModel;

public interface IPerfilService {
	
	public List<Perfil> findAll(Perfil perfil,Integer page,Integer sizes, String tipoConsulta);
	
	public Perfil save(Perfil perfil);
	
	public MensajeModel actuliza(Perfil perfil);
	
	public Perfil updatePerfil(Perfil instance);
	
	public Optional<Perfil> findById(Long id);
	
	public Page<Perfil> findAll(Pageable pageable);
	
	public MensajeModel delete(Perfil perfil);

	Perfil deletePerfil(List<Perfil> perfil);
}

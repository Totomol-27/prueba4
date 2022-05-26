package com.isita.ccapper.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.entity.Permiso;
import com.isita.ccapper.app.interfaces.IPermisoService;
import com.isita.ccapper.app.repository.PermisoRepository;

@Service
public class PermisoService implements IPermisoService {

	@Autowired
	private PermisoRepository permisoRepository; 
	@Override
	public List<Permiso> listaPermiso(Permiso permiso, Integer page, Integer sizes, String tipoConsulta) {
		return permisoRepository.listaPaginacion(permiso, page, sizes, tipoConsulta);
	}
}

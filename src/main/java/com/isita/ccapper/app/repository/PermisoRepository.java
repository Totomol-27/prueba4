package com.isita.ccapper.app.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isita.ccapper.app.entity.Permiso;

@Repository
public class PermisoRepository {

	@Autowired
	private EntityManager entityManager;
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Permiso> listaPaginacion(Permiso permiso, Integer page, Integer sizes, String tipoConsulta){
		
		Long l = permiso.getId();
		Integer idPermiso = l.intValue(); 
		List  resultList = entityManager.createNamedQuery("permiso_consulta")
				.setParameter(1, idPermiso)
				.setParameter(2, "")
				.setParameter(3, permiso.getNombre())
				.setParameter(4, page)
				.setParameter(5, sizes)
				.setParameter(6, tipoConsulta).getResultList();
				List<Permiso>  permisos= resultList;		
				
		return permisos;
	}
}

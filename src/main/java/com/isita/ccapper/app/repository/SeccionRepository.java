package com.isita.ccapper.app.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isita.ccapper.app.entity.Seccion;

@Repository
public class SeccionRepository {

	@Autowired
	private EntityManager entityManager;
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Seccion> findAll(Seccion seccion, String tipoConsulta) {
		if(seccion.getId() == null) {
			seccion.setId((long) 0);			
		}
		long l=seccion.getId();
			List <Seccion> resultList = this.entityManager.createNamedQuery("seccion_consulta")
			.setParameter(1, (int)l)
			.setParameter(2, seccion.getCuestionarioId())
			.setParameter(3, tipoConsulta).getResultList();
			List<Seccion>  secciones= resultList;		
		return secciones;
	}
	
	
}

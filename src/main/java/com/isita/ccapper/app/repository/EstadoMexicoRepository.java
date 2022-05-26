package com.isita.ccapper.app.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isita.ccapper.app.entity.EstatadosMexico;

@Repository
public class EstadoMexicoRepository {

	@Autowired
	private EntityManager entityManager;
	
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<EstatadosMexico> findAll(EstatadosMexico estatadosMexico, String tipoConsulta) {
		if(estatadosMexico.getId() == null) {
			estatadosMexico.setId((long) 0);			
		}
		long l=estatadosMexico.getId();

		List <EstatadosMexico> resultList = this.entityManager.createNamedQuery("estado_consulta")
				.setParameter(1, (int)l)
				.setParameter(2, estatadosMexico.getPaisId())
				.setParameter(3, estatadosMexico.getNombreEstado())
				.setParameter(4, tipoConsulta).getResultList();
		List<EstatadosMexico>  estatados= resultList;		
		return estatados;
}
}

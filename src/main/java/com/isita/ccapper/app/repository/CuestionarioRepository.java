package com.isita.ccapper.app.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isita.ccapper.app.entity.Cuestionario;

@Repository
public class CuestionarioRepository {

	@Autowired
	private EntityManager entityManager;
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Cuestionario> findAll(Cuestionario cuestionario, String tipoConsulta) {
		if(cuestionario.getId() == null) {
			cuestionario.setId((long) 0);			
		}
		long l=cuestionario.getId();
			List <Cuestionario> resultList = this.entityManager.createNamedQuery("cuestionario_consulta")
			.setParameter(1, (int)l)
			.setParameter(2, cuestionario.getTipoCuestionarioId())
			.setParameter(3, tipoConsulta).getResultList();
			List<Cuestionario>  cuestionarios= resultList;		
		return cuestionarios;
	}
	
	
	

}

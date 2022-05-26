package com.isita.ccapper.app.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.isita.ccapper.app.entity.Sucursal;

@Repository
public class SucursalRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Sucursal> findAll(Sucursal sucursal, Integer page, Integer sizes, String tipoConsulta) {
		if(sucursal.getId() == null) {
			sucursal.setId((long) 0);			
		}
		long l=sucursal.getId();

		List <Sucursal> resultList = this.entityManager.createNamedQuery("sucursal_consulta")
				.setParameter(1, (int)l)
				.setParameter(2, sucursal.getNombre())
				.setParameter(3, page)
				.setParameter(4, sizes)
				.setParameter(5, tipoConsulta).getResultList();
		List<Sucursal>  sucursales= resultList;		
		return sucursales;
}

}

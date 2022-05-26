package com.isita.ccapper.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isita.ccapper.app.entity.EstatusEvento;
import com.isita.ccapper.app.util.Utility;



@Repository
public class EstatusEventoRepository {
	@Autowired
	private EntityManager entityManager;
	
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<EstatusEvento> findAll(EstatusEvento estatusEvento, Integer page, Integer sizes, String tipoConsulta) {
		if(estatusEvento.getId() == null) {
			estatusEvento.setId((long) 0);			
		}
		
		long l=estatusEvento.getId();
		List <EstatusEvento> resultList = this.entityManager.createNamedQuery("estatus_evento_consulta")
				.setParameter(1, (int)l)
				.setParameter(2, estatusEvento.getDescripcion())
				.setParameter(3, page)
				.setParameter(4, sizes)
				.setParameter(5, tipoConsulta).getResultList();
		List<EstatusEvento>  estatusEventos= resultList;		
		return estatusEventos;
	}
	
	@Transactional(readOnly = false)
	public EstatusEvento save(EstatusEvento estatusEvento) {
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("estatus_evento_alta");
		query.setParameter("descripcion", estatusEvento.getDescripcion());
		query.execute();
		Object id =  query.getOutputParameterValue("id");
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");

		if(id != null) {
			estatusEvento.setId(Long.parseLong(String.valueOf(id.toString())));
		}else {
			Utility.errores(error.toString(), mensaje.toString());
		}
		
		return estatusEvento;
	}

	@Transactional(readOnly = false)
	public void actuliza(EstatusEvento estatusEvento) {
		long l=estatusEvento.getId();
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("estatus_evento_modificar");
		query.setParameter("id", (int)l);
		query.setParameter("descripcion", estatusEvento.getDescripcion());
		query.setParameter("estatus", estatusEvento.getEstatus());
		query.execute();
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		Utility.errores(error.toString(),mensaje.toString());
	}
	
	
	@Transactional(readOnly = false)
	public void delete(EstatusEvento estatusEvento) {
		long l=estatusEvento.getId();
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("estatus_evento_baja");
		query.setParameter("id", (int)l);
		query.execute();
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		Utility.errores(error.toString(),mensaje.toString());
		

	}
	
}

package com.isita.ccapper.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isita.ccapper.app.entity.EstatusSala;
import com.isita.ccapper.app.model.MensajeModel;
import com.isita.ccapper.app.util.Utility;

@Repository
public class EstatusSalaRepository {

	@Autowired
	private EntityManager entityManager;
	
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<EstatusSala> findAll(EstatusSala estatusSala, Integer page, Integer sizes, String tipoConsulta) {
		if(estatusSala.getId() == null) {
			estatusSala.setId((long) 0);			
		}
		long l=estatusSala.getId();

		List <EstatusSala> resultList = this.entityManager.createNamedQuery("estatus_sala_consulta")
				.setParameter(1, (int)l)
				.setParameter(2, estatusSala.getDescripcion())
				.setParameter(3, page)
				.setParameter(4, sizes)
				.setParameter(5, tipoConsulta).getResultList();
		List<EstatusSala>  estatusSalas= resultList;		
		return estatusSalas;
}
	
	@Transactional(readOnly = false)
	public EstatusSala save(EstatusSala estatusSala) {
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("estatus_sala_alta");
		query.setParameter("descripcion", estatusSala.getDescripcion());
		query.execute();
		Object id =  query.getOutputParameterValue("id");
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");

		if(id != null) {
			estatusSala.setId(Long.parseLong(String.valueOf(id.toString())));
		}else {
			Utility.errores(error.toString(), mensaje.toString());
		}
		
		return estatusSala;
	}

	@Transactional(readOnly = false)
	public void actuliza(EstatusSala estatusSala) {
		long l=estatusSala.getId();
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("estatus_sala_modificar");
		query.setParameter("id", (int)l);
		query.setParameter("descripcion", estatusSala.getDescripcion());
		query.setParameter("estatus", estatusSala.getEstatus());
		query.execute();
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		Utility.errores(error.toString(),mensaje.toString());
	}
	
	
	@Transactional(readOnly = false)
	public void delete(EstatusSala estatusSala) {
		long l=estatusSala.getId();
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("estatus_sala_baja");
		query.setParameter("id", (int)l);
		query.execute();
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		Utility.errores(error.toString(),mensaje.toString());
		MensajeModel  modelos = new MensajeModel();
		modelos.setCodigo(error.toString());
		modelos.setMensaje(mensaje.toString());

	}
	
	
}

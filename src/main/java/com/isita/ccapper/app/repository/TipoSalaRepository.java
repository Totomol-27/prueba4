package com.isita.ccapper.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isita.ccapper.app.entity.TipoSala;
import com.isita.ccapper.app.model.MensajeModel;
import com.isita.ccapper.app.util.Utility;

@Repository
public class TipoSalaRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<TipoSala> findAll(TipoSala tipoSala, Integer page, Integer sizes, String tipoConsulta) {
		if(tipoSala.getId() == null) {
			tipoSala.setId((long) 0);			
		}
		long l=tipoSala.getId();

		List <TipoSala> resultList = this.entityManager.createNamedQuery("tipo_sala_consulta")
				.setParameter(1, (int)l)
				.setParameter(2, tipoSala.getNombre())
				.setParameter(3, page)
				.setParameter(4, sizes)
				.setParameter(5, tipoConsulta).getResultList();
		List<TipoSala>  tipoSalas= resultList;		
		return tipoSalas;
}
	
	@Transactional(readOnly = false)
	public TipoSala save(TipoSala tipoSala) {
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("tipo_sala_alta");
		query.setParameter("nombre", tipoSala.getNombre());
		query.setParameter("clave", tipoSala.getClave());
		query.setParameter("descripcion", tipoSala.getDescripcion());
		query.execute();
		Object id =  query.getOutputParameterValue("id");
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");

		if(id != null) {
			tipoSala.setId(Long.parseLong(String.valueOf(id.toString())));
		}else {
			Utility.errores(error.toString(), mensaje.toString());
		}
		
		return tipoSala;
	}

	@Transactional(readOnly = false)
	public void actuliza(TipoSala tipoSala) {
		long l=tipoSala.getId();
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("tipo_sala_modificar");
		query.setParameter("id", (int)l);
//		query.setParameter("clave", tipoSala.getClave());
		query.setParameter("nombre", tipoSala.getNombre());
		query.setParameter("descripcion", tipoSala.getDescripcion());
		query.execute();
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		Utility.errores(error.toString(),mensaje.toString());
		
		MensajeModel  modelos = new MensajeModel();
		modelos.setCodigo(error.toString());
		modelos.setMensaje(mensaje.toString());
	}
	
	
	@Transactional(readOnly = false)
	public void delete(TipoSala tipoSala) {
		long l=tipoSala.getId();
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("tipo_sala_baja");
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

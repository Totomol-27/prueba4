package com.isita.ccapper.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.isita.ccapper.app.entity.TipoEvento;
import com.isita.ccapper.app.model.MensajeModel;
import com.isita.ccapper.app.util.Utility;

@Repository
public class TipoEventoRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<TipoEvento> findAll(TipoEvento tipoEvento, Integer page, Integer sizes, String tipoConsulta) {
		if(tipoEvento.getId() == null) {
			tipoEvento.setId((long) 0);			
		}
		long l=tipoEvento.getId();

		List <TipoEvento> resultList = this.entityManager.createNamedQuery("tipo_evento_consulta")
				.setParameter(1, (int)l)
				.setParameter(2, tipoEvento.getNombre())
				.setParameter(3, page)
				.setParameter(4, sizes)
				.setParameter(5, tipoConsulta).getResultList();
		List<TipoEvento>  tipoEventos= resultList;		
		return tipoEventos;
}
	
	@Transactional(readOnly = false)
	public TipoEvento save(TipoEvento tipoEvento) {
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("tipo_evento_alta");
		query.setParameter("nombre", tipoEvento.getNombre());
		query.setParameter("descripcion", tipoEvento.getDescripcion());
		query.execute();
		Object id =  query.getOutputParameterValue("id");
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");

		if(id != null) {
			tipoEvento.setId(Long.parseLong(String.valueOf(id.toString())));
		}else {
			Utility.errores(error.toString(), mensaje.toString());
		}
		
		return tipoEvento;
	}

	@Transactional(readOnly = false)
	public void actuliza(TipoEvento tipoEvento) {
		long l=tipoEvento.getId();
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("tipo_evento_modificar");
		query.setParameter("id", (int)l);
		query.setParameter("nombre", tipoEvento.getNombre());
		query.setParameter("descripcion", tipoEvento.getDescripcion());
		query.execute();
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		Utility.errores(error.toString(),mensaje.toString());
	}
	
	
	@Transactional(readOnly = false)
	public void delete(TipoEvento tipoEvento) {
		long l=tipoEvento.getId();
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("tipo_evento_baja");
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

package com.isita.ccapper.app.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isita.ccapper.app.entity.Opcion;
import com.isita.ccapper.app.entity.Persona;
import com.isita.ccapper.app.entity.Pregunta;
import com.isita.ccapper.app.entity.RespuestaOpcion;
import com.isita.ccapper.app.entity.RespuestaTexto;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;

@Repository
public class RespuestaOpcionRepository {
	
	@Autowired
	private EntityManager entityManager;
		
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RespuestaOpcion> findAll(RespuestaOpcion respuestaOpcion, String tipoConsulta) {
		if(respuestaOpcion.getId() == null) {
			respuestaOpcion.setId((long) 0);			
		}
		long l=respuestaOpcion.getId();
			List <RespuestaOpcion> resultList = this.entityManager.createNamedQuery("respuesta_opcion_consulta")
			.setParameter(1, (int)l)
			.setParameter(2, respuestaOpcion.getOpcion().getId())
			.setParameter(3, tipoConsulta).getResultList();
			List<RespuestaOpcion>  respuestaOpciones= resultList;		
		return respuestaOpciones;
	}
	
	@Transactional(readOnly = false)
	public RespuestaOpcion save(RespuestaOpcion respuestaOpcion) {
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("respuesta_opcion_alta");
		respuestaOpcion = this.validarRespuesta(respuestaOpcion);
		query.setParameter("pregunta_tipo_opcion_id", respuestaOpcion.getPreguntaTipoOpcionId());
		query.setParameter("opciones_id", respuestaOpcion.getOpcion().getId().intValue());
		query.setParameter("persona_id", respuestaOpcion.getPersona().getId().intValue());
		query.setParameter("opcion_elegida", respuestaOpcion.getOpcionElegida());
		query.setParameter("opcion_id", respuestaOpcion.getOpcion().getId().intValue());
		query.setParameter("fecha_creacion", respuestaOpcion.getFechaCreacion());
		query.execute();
		Object id =  query.getOutputParameterValue("id");
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");

		if(id != null) {
			respuestaOpcion.setId(Long.parseLong(String.valueOf(id.toString())));
		}else {
			Utility.errores(error.toString(), mensaje.toString());
		}
		
		return respuestaOpcion;
	}
	
	public RespuestaOpcion validarRespuesta(RespuestaOpcion respuestaOpcion) {
		Persona persona = new Persona();
		Opcion opcion = new Opcion();
		respuestaOpcion.setPreguntaTipoOpcionId(respuestaOpcion.getPreguntaTipoOpcionId() instanceof Integer?respuestaOpcion.getPreguntaTipoOpcionId():Constantes.NUMERO_CERO);
		opcion.setId(respuestaOpcion.getOpcion()!=null?(respuestaOpcion.getOpcion().getId()!=null?respuestaOpcion.getOpcion().getId():Constantes.NUMERO_CERO_TIPO_LONG):Constantes.NUMERO_CERO_TIPO_LONG);
		persona.setId(respuestaOpcion.getPersona()!=null?(respuestaOpcion.getPersona().getId()!=null?respuestaOpcion.getPersona().getId():Constantes.NUMERO_CERO_TIPO_LONG):Constantes.NUMERO_CERO_TIPO_LONG);
		respuestaOpcion.setOpcion(opcion);
		respuestaOpcion.setPersona(persona);
		respuestaOpcion.setOpcionElegida(respuestaOpcion.getOpcionElegida()!=null?respuestaOpcion.getOpcionElegida():Constantes.VACIO);
		respuestaOpcion.setFechaCreacion(respuestaOpcion.getFechaCreacion()!=null?respuestaOpcion.getFechaCreacion():new Date());
		return respuestaOpcion;
	}

}

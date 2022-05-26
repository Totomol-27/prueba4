package com.isita.ccapper.app.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isita.ccapper.app.entity.Persona;
import com.isita.ccapper.app.entity.Pregunta;
import com.isita.ccapper.app.entity.RespuestaTexto;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;


@Repository
public class RespuestaTextoRepository {
	
	@Autowired
	private EntityManager entityManager;
		
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RespuestaTexto> findAll(RespuestaTexto respuestaTexto, String tipoConsulta) {
		if(respuestaTexto.getId() == null) {
			respuestaTexto.setId((long) 0);			
		}
		long l=respuestaTexto.getId();
			List <RespuestaTexto> resultList = this.entityManager.createNamedQuery("respuesta_texto_consulta")
			.setParameter(1, (int)l)
			.setParameter(2, respuestaTexto.getRespuesta())
			.setParameter(3, tipoConsulta).getResultList();
			List<RespuestaTexto>  respuestasTexto= resultList;		
		return respuestasTexto;
	}
	
	@Transactional(readOnly = false)
	public RespuestaTexto save(RespuestaTexto respuestaTexto) {
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("respuesta_texto_alta");
		respuestaTexto = this.validarRespuesta(respuestaTexto);
		query.setParameter("pregunta_id", respuestaTexto.getPregunta().getId().intValue());
		query.setParameter("persona_id", respuestaTexto.getPersona().getId().intValue());
		query.setParameter("respuesta", respuestaTexto.getRespuesta());
		query.setParameter("fecha_creacion", respuestaTexto.getFechaCreacion());
		query.execute();
		Object id =  query.getOutputParameterValue("id");
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");

		if(id != null) {
			respuestaTexto.setId(Long.parseLong(String.valueOf(id.toString())));
		}else {
			Utility.errores(error.toString(), mensaje.toString());
		}
		
		return respuestaTexto;
	}
	
	public RespuestaTexto validarRespuesta(RespuestaTexto respuestaTexto) {
		Persona persona = new Persona();
		Pregunta pregunta = new Pregunta();
		pregunta.setId(respuestaTexto.getPregunta()!=null?(respuestaTexto.getPregunta().getId()!=null?respuestaTexto.getPregunta().getId():Constantes.NUMERO_CERO_TIPO_LONG):Constantes.NUMERO_CERO_TIPO_LONG);
		persona.setId(respuestaTexto.getPersona()!=null?(respuestaTexto.getPersona().getId()!=null?respuestaTexto.getPersona().getId():Constantes.NUMERO_CERO_TIPO_LONG):Constantes.NUMERO_CERO_TIPO_LONG);
		respuestaTexto.setPregunta(pregunta);
		respuestaTexto.setPersona(persona);
		respuestaTexto.setRespuesta(respuestaTexto.getRespuesta()!=null?respuestaTexto.getRespuesta():Constantes.VACIO);
		respuestaTexto.setFechaCreacion(respuestaTexto.getFechaCreacion()!=null?respuestaTexto.getFechaCreacion():new Date());
		return respuestaTexto;
	}
}

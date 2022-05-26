package com.isita.ccapper.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isita.ccapper.app.entity.Programacion;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;

@Repository
public class ProgramacionRepository {
	
	@Autowired
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Programacion> programacionRepository(Programacion programacion, String tipoConsult){
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("programacion_con", Programacion.class)
				.registerStoredProcedureParameter("id", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("nombre", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("tipo_consulta", String.class, ParameterMode.IN);
		storedProcedureQuery.setParameter("id", programacion.getId() != null ? programacion.getId() : Constantes.NUMERO_CERO);
		storedProcedureQuery.setParameter("nombre", programacion.getNombre() != null ? programacion.getNombre() : Constantes.VACIO);
		storedProcedureQuery.execute();
		List<Programacion> dataProgramacion = storedProcedureQuery.getResultList();
		return dataProgramacion;
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Programacion> findAll(Programacion programacion, Integer page, Integer sizes, String tipoConsulta) {
		if(programacion.getId() == null) {
			programacion.setId((long) 0);			
		}

		List <Programacion> resultList = this.entityManager.createNamedQuery("consulta_programacion")
				.setParameter(1, programacion.getId().intValue())
				.setParameter(2, programacion.getNombre())
				.setParameter(3, page)
				.setParameter(4, sizes)
				.setParameter(5, tipoConsulta).getResultList();
		List<Programacion>  programaciones= resultList;		
		return programaciones;
	}
	
	@Transactional
	public Programacion save(Programacion programacion) {
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("alta_programacion");
		query.setParameter("persona_id", programacion.getPersona().getId().intValue());
		query.setParameter("expositor", programacion.getExpositor());
		query.setParameter("evento_id", programacion.getEvento().getId().intValue());
		query.setParameter("sala_id", programacion.getSala().getId().intValue());
		query.setParameter("nombre", programacion.getNombre());
		query.setParameter("descripcion", programacion.getDescripcion());
		query.setParameter("fecha_inicio", programacion.getFecha_inicio());
		query.setParameter("fecha_fin", programacion.getFecha_fin());
		query.execute();
		
		Object id = query.getOutputParameterValue("id");
		Object error = query.getOutputParameterValue("error_codigo");
		Object mensaje = query.getOutputParameterValue("error_mensaje");
		
		if (id != null) {
			programacion.setId(Long.parseLong(String.valueOf(id.toString())));
		}else {
			Utility.errores(error.toString(), mensaje.toString());
		}
		return programacion;
	}
	
	
	@Transactional
	public void modificarProgramacion(Programacion programacion) {
	
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("modificar_programacion");
		Utility.setStoreProcedureEnableNullParametersWithParametersOut(query, query.getParameters().stream().count());
		query.setParameter("id", programacion.getId().intValue());
		query.setParameter("persona_id", programacion.getPersona().getId().intValue());
		query.setParameter("expositor", programacion.getExpositor());
		query.setParameter("evento_id", programacion.getEvento().getId().intValue());
		query.setParameter("sala_id", programacion.getSala().getId().intValue());
		query.setParameter("nombre", programacion.getNombre());
		query.setParameter("descripcion", programacion.getDescripcion());
		query.setParameter("fecha_inicio", programacion.getFecha_inicio());
		query.setParameter("fecha_fin", programacion.getFecha_fin());
		query.execute();
		
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		Utility.errores(error.toString(),mensaje.toString());
	}
	
	
	@Transactional
	public void eliminarProgramacion(String programaciones, Integer eventoId, String tipoEliminacion) {
	StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("eliminar_programacion");
	query.setParameter("idprogramacion", programaciones != null ? programaciones : Constantes.VACIO);
	query.setParameter("evento_id", eventoId != 0 ? eventoId : Constantes.NUMERO_CERO);
	query.setParameter("eliminacion", tipoEliminacion);
	query.execute();
	Object error = query.getOutputParameterValue("error_codigo");
	Object mensaje = query.getOutputParameterValue("error_mensaje");
	Utility.errores(error.toString(), mensaje.toString());

	}

}

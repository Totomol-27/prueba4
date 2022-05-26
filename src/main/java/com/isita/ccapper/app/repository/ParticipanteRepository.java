package com.isita.ccapper.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.isita.ccapper.app.entity.Participante;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;

@Repository
public class ParticipanteRepository {
	@Autowired
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Participante> findAll(Participante participante,Integer page, Integer sizes, String tipoConsulta){
		if(participante.getId() == null) {
			participante.setId((long) 0);			
		}
		List<Participante> resultParticipante = entityManager.createNamedQuery("consulta_participante")
							.setParameter(1, participante.getId().intValue())
							.setParameter(2, participante.getEvento().getId().intValue())
							.setParameter(3, page)
							.setParameter(4, sizes)
							.setParameter(5, participante.getPersona().getId().intValue())
							.setParameter(6, tipoConsulta).getResultList();
		
		List<Participante>  participantes= resultParticipante;	
		return  participantes;
	}
	
	@SuppressWarnings("unchecked")
	public List<Participante> participanteRespository(Participante participante, String tipoConsulta){
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("participante_con", Participante.class)
				.registerStoredProcedureParameter("id", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("persona_id", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("evento_id", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("estatus", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("tipo_consulta", String.class, ParameterMode.IN);
//				storedProcedureQuery.setParameter("id", participante.getId() != null ? participante.getId() : Constantes.NUMERO_CERO);
//				storedProcedureQuery.setParameter("persona_id", participante.getPersona_id() != null ? participante.getPersona_id() : Constantes.NUMERO_CERO);
//				storedProcedureQuery.setParameter("evento_id", participante.getEvento_id() != null ? participante.getEvento_id() : Constantes.NUMERO_CERO);
//				storedProcedureQuery.setParameter("estatus", participante.getEstatus() != null ? participante.getEstatus() : Constantes.NUMERO_CERO);
//				storedProcedureQuery.setParameter("tipo_consulta", tipoConsulta);
//				storedProcedureQuery.execute();
				List<Participante> dataParticipante = storedProcedureQuery.getResultList();
				return dataParticipante;
	}
	
	@Transactional
	public Participante saveParticipante(Participante participante) {
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("alta_participante");
		query.setParameter("persona_id", participante.getPersona().getId());
		query.setParameter("evento_id", participante.getEvento().getId());
		query.execute();
			Object id = query.getOutputParameterValue("id");
			Object error = query.getOutputParameterValue("error_codigo");
			Object mensaje = query.getOutputParameterValue("error_mensaje");
		
		if(id != null) {
			participante.setId(Long.parseLong(String.valueOf(id.toString())));
		}else {
			Utility.errores(error.toString(), mensaje.toString());
		}
		return participante;
	}
	
	@Transactional
	public Participante saveListParticipante(String participantes,  @RequestParam Integer idEvento) {
		Participante objParticipante = new Participante();
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("participante_alta");
		query.setParameter("idParticipantes", participantes);
		query.setParameter("idEvento", idEvento);
		query.execute();
			Object id = query.getOutputParameterValue("id");
			Object error = query.getOutputParameterValue("error_codigo");
			Object mensaje = query.getOutputParameterValue("error_mensaje");
		
		if(id != null) {
			objParticipante.setId(Long.parseLong(String.valueOf(id.toString())));
		}else {
			Utility.errores(error.toString(), mensaje.toString());
		}
		return objParticipante;
	}
	
	@Transactional
	public void eliminarParticipante(String participantes) {
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("eliminar_participante");
		query.setParameter("idparticipantes", participantes);
		query.execute();
		Object error = query.getOutputParameterValue("error_codigo");
		Object mensaje = query.getOutputParameterValue("error_mensaje");
		Utility.errores(error.toString(), mensaje.toString());
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Participante buscarParticipanteById(Integer id, String tipoConsulta) {
		Participante participanteObj = new Participante();
		List<Participante> resultParticipante = entityManager.createNamedQuery("consulta_participante")
							.setParameter(1, 0)
							.setParameter(2, 0)
							.setParameter(3, 0)
							.setParameter(4, id)
							.setParameter(5, tipoConsulta).getResultList();
		if(resultParticipante.size() > 0) {
			participanteObj = resultParticipante.get(0);
		}
		return participanteObj;
	}
	
	public void eliminarParticipante(String participantes, Integer idEvento, String tipoEliminacion) {
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("eliminar_participante");
		query.setParameter("idparticipantes", participantes != null ? participantes : Constantes.VACIO);
		query.setParameter("evento_id", idEvento != 0 ?  idEvento : Constantes.NUMERO_CERO);
		query.setParameter("eliminacion", tipoEliminacion);
		query.execute();
		Object error = query.getOutputParameterValue("error_codigo");
		Object mensaje = query.getOutputParameterValue("error_mensaje");
		Utility.errores(error.toString(), mensaje.toString());
		}
	
	
}

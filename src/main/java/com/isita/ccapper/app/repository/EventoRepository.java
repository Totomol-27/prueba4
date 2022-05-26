package com.isita.ccapper.app.repository;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import com.isita.ccapper.app.entity.Evento;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EventoRepository {
	@Autowired
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Evento> eventoRepository(Evento evento, String tipo_consulta) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("evento_con", Evento.class)
				.registerStoredProcedureParameter("id", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("nombre", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("estatus_evento_id", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("fecha_inicio", Date.class, ParameterMode.IN)
				.registerStoredProcedureParameter("fecha_fin", Date.class, ParameterMode.IN)
				.registerStoredProcedureParameter("tipo_consulta", String.class, ParameterMode.IN);
		storedProcedureQuery.setParameter("id", evento.getId() != null ? evento.getId() : Constantes.NUMERO_CERO);
		storedProcedureQuery.setParameter("nombre", evento.getNombre() != null ? evento.getNombre() : Constantes.VACIO);
//		storedProcedureQuery.setParameter("estatus_evento_id", evento.getEstatusEventoId() != null ? evento.getEstatusEventoId() : Constantes.NUMERO_UNO);
		storedProcedureQuery.setParameter("estatus_evento_id", evento.getEstatusEvent().getId() != null ? evento.getEstatusEvent().getId() : Constantes.NUMERO_UNO);
		storedProcedureQuery.setParameter("fecha_inicio", evento.getFechaInicio() != null ? evento.getFechaInicio() : Timestamp.valueOf("1900-01-01 00:00:00.0"));
		storedProcedureQuery.setParameter("fecha_fin", evento.getFechaFin() != null ? evento.getFechaFin() : Timestamp.valueOf("1900-01-01 00:00:00.0"));
		storedProcedureQuery.setParameter("tipo_consulta", tipo_consulta);
		storedProcedureQuery.execute();
		List<Evento> dataEvento = storedProcedureQuery.getResultList();
		return dataEvento;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Evento buscarEventoById(int id, String tipo_consulta) {
		Evento eventoObj = new Evento();
		List<Evento> resultEvento = entityManager.createNamedQuery("consulta_evento")
				.setParameter(1, id)
				.setParameter(2, Constantes.VACIO)
				.setParameter(3, 0)
				.setParameter(4,  Timestamp.valueOf("1900-01-01 00:00:00.0"))
				.setParameter(5,  Timestamp.valueOf("1900-01-01 00:00:00.0"))
				.setParameter(6, 0)
				.setParameter(7, 0)
				.setParameter(8, tipo_consulta)
				.getResultList();
		if (resultEvento.size() > 0) {
			eventoObj = resultEvento.get(0);
		}
		return eventoObj;
	}

	@Transactional
	public Evento saveEvento(Evento evento) {
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("alta_evento");
		query.setParameter("tipo_evento_id", evento.getTipoEvento().getId().intValue());
		query.setParameter("responsable_id", evento.getResponsable_id());
		query.setParameter("fecha_inicio", evento.getFechaInicio());
		query.setParameter("fecha_fin", evento.getFechaFin());
		query.setParameter("nombre", evento.getNombre());
		query.setParameter("descripcion", evento.getDescripcion());
		query.setParameter("total_participantes", evento.getTotalParticipantes());
		query.setParameter("estatus", evento.getEstatus());
		query.execute();

		Object id = query.getOutputParameterValue("id");
		Object error = query.getOutputParameterValue("error_codigo");
		Object mensaje = query.getOutputParameterValue("error_mensaje");
		String idEvento = id.toString();

		if (idEvento.length() > 0) {
			Long idEvent = Long.parseLong(String.valueOf(idEvento));
			evento.setId(idEvent);
		} else {
			Utility.errores(error.toString(), mensaje.toString());
		}
		return evento;
	}

	@Transactional
	public void actualizarEvento(String eventos) {
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("eliminar_evento");
		query.setParameter("ideventos", eventos);
		query.execute();
		Object error = query.getOutputParameterValue("error_codigo");
		Object mensaje = query.getOutputParameterValue("error_mensaje");
		Utility.errores(error.toString(), mensaje.toString());
	}

	@Transactional()
	public void modificarEvento(Evento evento, String tipo_modificacion) {
		Integer IdEvento = (int) (long) evento.getId();
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("modificar_evento");
		Utility.setStoreProcedureEnableNullParametersWithParametersOut(query, query.getParameters().stream().count());
		query.setParameter("id", IdEvento);
		query.setParameter("estatus_evento_id", evento.getEstatusEvent().getId().intValue());
		query.setParameter("tipo_evento_id", evento.getTipoEvento().getId().intValue());
		query.setParameter("responsable_id", evento.getResponsable_id());
		query.setParameter("fecha_inicio", evento.getFechaInicio());
		query.setParameter("fecha_fin", evento.getFechaFin());
		query.setParameter("nombre", evento.getNombre());
		query.setParameter("descripcion", evento.getDescripcion());
		query.setParameter("total_participantes", evento.getTotalParticipantes());
		query.setParameter("estatus", evento.getEstatus());
		query.execute();
	}

	@Transactional()
	@SuppressWarnings({ "unchecked", "unused" })
	public List<Evento> findAll(Integer pagina, Integer tamanio, String tipo_consulta) {
		List<Evento> listaEvento;
		 listaEvento = this.entityManager.createNamedQuery("consulta_evento")
				.setParameter(1, 0)
				.setParameter(2, Constantes.VACIO)
				.setParameter(3, 0)
				.setParameter(4, Timestamp.valueOf("1900-01-01 00:00:00.0"))
				.setParameter(5, Timestamp.valueOf("1900-01-01 00:00:00.0"))
				.setParameter(6, pagina)
				.setParameter(7, tamanio)
				.setParameter(8, tipo_consulta).getResultList();
		return listaEvento;

	}
	public static String getFechaActual() {
	    Date ahora = new Date();
	    SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return formateador.format(ahora);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Evento> findByName(Evento evento, String tipo_consulta) {
		if(evento.getEstatusEvent().getId() == null) {
			evento.getEstatusEvent().setId((long) 0);			
		}
		long l=evento.getEstatusEvent().getId();
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		List<Evento> resultNameEvento = entityManager.createNamedQuery("consulta_evento")
				.setParameter(1, 0)
				.setParameter(2, evento.getNombre() != null ? evento.getNombre() : Constantes.VACIO)
				.setParameter(3, (int)l)
				.setParameter(4, evento.getFechaInicio() != null ? evento.getFechaInicio() : Timestamp.valueOf("1900-01-01 00:00:00.0"))
				.setParameter(5, evento.getFechaFin() != null ? evento.getFechaFin() : timestamp)
				.setParameter(6, 0)
				.setParameter(7, 0)
				.setParameter(8, tipo_consulta)
				.getResultList();
		if (resultNameEvento.size() > 0) {
			evento = resultNameEvento.get(0);
		}
		return resultNameEvento;
	}
}

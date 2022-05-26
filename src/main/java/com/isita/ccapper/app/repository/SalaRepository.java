package com.isita.ccapper.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isita.ccapper.app.entity.Sala;
import com.isita.ccapper.app.model.MensajeModel;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;


@Repository
public class SalaRepository {
	@Autowired
	private EntityManager entityManager;
	
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Sala> findAll(Sala sala, Integer page, Integer sizes, String tipoConsulta) {
		if(sala.getId() == null) {
			sala.setId((long) 0);			
		}
		long l=sala.getId();

		List <Sala> resultList = this.entityManager.createNamedQuery("sala_consulta")
				.setParameter(1, (int)l)
				.setParameter(2, sala.getNombre())
				.setParameter(3,  sala.getCapacidad() != null ? sala.getCapacidad() : Constantes.NUMERO_CERO)
				.setParameter(4, page)
				.setParameter(5, sizes)
				.setParameter(6, tipoConsulta).getResultList();
		List<Sala>  salas= resultList;		
		return salas;
	}
	
	@Transactional
	public Sala save(Sala sala) {
		
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("sala_alta");
		query.setParameter("tipo_sala_id", sala.getTipo_sala_id());
		query.setParameter("estatus_sala_id", sala.getEstatus_sala_id());
		query.setParameter("sucursal_id", sala.getSucursalId());
		query.setParameter("nombre", sala.getNombre());
		query.setParameter("clave", sala.getClave());
		query.setParameter("telefono", sala.getTelefono());
		query.setParameter("capacidad", sala.getCapacidad());
		query.execute();
		Object id =  query.getOutputParameterValue("id");
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");

		if(id != null) {
			sala.setId(Long.parseLong(String.valueOf(id.toString())));
		}else {
			Utility.errores(error.toString(), mensaje.toString());
		}
		
		return sala;
	}

	@Transactional
	public void actuliza(Sala sala) {
		long l=sala.getId();
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("sala_modificar");
		query.setParameter("id", (int)l);
		query.setParameter("tipo_sala_id", sala.getTipo_sala_id());
		query.setParameter("estatus_sala_id", sala.getEstatus_sala_id());
		query.setParameter("sucursal_id", sala.getSucursalId());
		query.setParameter("nombre", sala.getNombre());
//		query.setParameter("clave", sala.getClave());
		query.setParameter("telefono", sala.getTelefono());
		query.setParameter("capacidad", sala.getCapacidad());
		query.execute();
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		Utility.errores(error.toString(),mensaje.toString());
	}
	
	
	@Transactional
	public void delete(Sala sala) {
		long l=sala.getId();
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("sala_baja");
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

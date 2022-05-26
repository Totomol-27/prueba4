package com.isita.ccapper.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isita.ccapper.app.entity.Credencial;
import com.isita.ccapper.app.interfaces.ICredencialService;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;

@Repository
public class CredencialRepository implements ICredencialService{
	@Autowired
    EntityManager entityManager;
	
	@Override
	public Credencial save(Credencial credencial) {
		System.out.println("usuarios generado");

		StoredProcedureQuery query= this.entityManager.createNamedStoredProcedureQuery("alta_token");
		query.setParameter("credencial_generado",credencial.getCredencialGenerado());
		query.setParameter("fecha_inicia", 	credencial.getFechaInicia());
		query.setParameter("fecha_termina", credencial.getFechaTermina());
		query.setParameter("usuario_id", 	credencial.getUsuarioId());
		query.setParameter("direccion_ip", 	credencial.getDireccionIp());
		query.setParameter("estatus", 		credencial.getEstatus());
		query.execute();
		Object id =  query.getOutputParameterValue("id");
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		String idToken = id.toString();
		
		if(idToken.length()>Constantes.NUMERO_CERO) {
			credencial.setId(Long.parseLong(String.valueOf(idToken)));
		}else {
			Utility.errores(error.toString(),mensaje.toString());
		}
		return credencial;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Credencial findByUserId(Credencial credencial, String tipoConsulta) {
		List<Credencial> resultset = entityManager.createNamedQuery("consulta_token").setParameter(1, credencial.getUsuarioId())
				.setParameter(2, credencial.getFechaTermina())
				.setParameter(3, tipoConsulta).getResultList();
		
		return resultset.get(0);
	}

}
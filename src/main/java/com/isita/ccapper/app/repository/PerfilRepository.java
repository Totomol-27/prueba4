package com.isita.ccapper.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.isita.ccapper.app.entity.Perfil;
import com.isita.ccapper.app.model.MensajeModel;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;

@Repository
public class PerfilRepository  {
	
	@Autowired
	private EntityManager entityManager = null;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Perfil> findAll(Perfil perfil, Integer page, Integer sizes, String tipoConsulta) {
		if(perfil.getId() == null) {
			perfil.setId(0);
			
		}
		List resultList = entityManager.createNamedQuery("perfil_consulta")
				.setParameter(1, perfil.getId()!=null?perfil.getId():Constantes.NUMERO_CERO)
				.setParameter(2, perfil.getClave()!=null?perfil.getClave():Constantes.VACIO)
				.setParameter(3, perfil.getNombre()!=null?perfil.getNombre():Constantes.VACIO)
				.setParameter(4, page instanceof Integer?page:Constantes.NUMERO_CERO)
				.setParameter(5, sizes instanceof Integer? sizes:Constantes.NUMERO_CERO)
				.setParameter(6, perfil.getUsuario()!=null?
							(perfil.getUsuario().getId()!=null?perfil.getUsuario().getId().intValue():Constantes.NUMERO_CERO)
							:Constantes.NUMERO_CERO)
				.setParameter(7, tipoConsulta).getResultList();
				List<Perfil>  perfiles= resultList;		
				return perfiles;
	}
	
	@Transactional(readOnly = false)
	public Perfil save(Perfil perfil) {
		
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("perfil_alta");
		query.setParameter("clave", perfil.getClave());
		query.setParameter("nombre", perfil.getNombre());
		query.setParameter("descripcion", perfil.getDescripcion());
		query.execute();
		Object id =  query.getOutputParameterValue("id");
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		if(id != null) {
//			servicio.setId(Long.parseLong(String.valueOf(id.toString())));
			perfil.setId(Integer.parseInt(id.toString()));
		}else {
			Utility.errores(error.toString(), mensaje.toString());
		}
		return perfil;
	}

	@Transactional(readOnly = false)
	public MensajeModel actuliza(Perfil perfil) {
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("perfil_modificar");
		query.setParameter("id", perfil.getId());
		query.setParameter("clave", perfil.getClave());
		query.setParameter("nombre", perfil.getNombre());
		query.setParameter("descripcion", perfil.getDescripcion());
		query.setParameter("estatus", perfil.getEstatus());
		query.execute();
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		
		MensajeModel  modelos = new MensajeModel();
		modelos.setCodigo(error.toString());
		modelos.setMensaje(mensaje.toString());
		if(error.toString().equals("000000")) {
			return modelos;
		}else {
			return modelos;
		}
	}
	
	@Transactional(readOnly = false)
	public Perfil updatePerfil(Perfil instance) {
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("perfil_modificar");
		query.setParameter("id", instance.getId());
		query.setParameter("clave", instance.getClave());
		query.setParameter("nombre", instance.getNombre());
		query.setParameter("descripcion", instance.getDescripcion());
		query.setParameter("estatus", instance.getEstatus());
		query.execute();
		return instance;
		
	}
	
	@Transactional(readOnly = false)
	public MensajeModel delete(Perfil perfil) {
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("perfil_baja");
		query.setParameter("id", perfil.getId());
		query.setParameter("estatus", perfil.getEstatus());
		query.execute();
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		MensajeModel  modelos = new MensajeModel();
		modelos.setCodigo(error.toString());
		modelos.setMensaje(mensaje.toString());
		if(error.toString().equals("000000")) {
			return modelos;
		}else {
			return modelos;
		}
	}
	
}

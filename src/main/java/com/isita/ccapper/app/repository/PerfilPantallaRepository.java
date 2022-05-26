package com.isita.ccapper.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isita.ccapper.app.entity.Pantalla;
import com.isita.ccapper.app.entity.PantallaServicio;
import com.isita.ccapper.app.entity.Perfil;
import com.isita.ccapper.app.entity.PerfilPantalla;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;

@Repository
public class PerfilPantallaRepository {
	
	@Autowired
    EntityManager entityManager;
	/**
	 * busca pantallas asociadas del perfil del usuario
	 * @param perfilPantalla
	 * @param tipoConsulta
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<PerfilPantalla> buscarPerfilPantalla(PerfilPantalla perfilPantalla,String tipoConsulta) {
		perfilPantalla = this.validarEntity(perfilPantalla);
		List<PerfilPantalla> listaPerfilPantalla = entityManager.createNamedQuery("consulta_perfil_pantalla")
				.setParameter(1, perfilPantalla.getId().intValue())
				.setParameter(2, perfilPantalla.getPerfil()!=null?perfilPantalla.getPerfil():0)
				.setParameter(3, perfilPantalla.getPantalla().getId().intValue())
				.setParameter(4, perfilPantalla.getIdUsario())
				.setParameter(5, tipoConsulta).getResultList();
		return listaPerfilPantalla;	
	}
	/**
	 * Guarda una nueva asigancion de pantalla a perfil
	 * @param PerfilPantalla
	 * @return
	 */
	@Transactional()
	public PerfilPantalla savePerfilPantalla(PerfilPantalla PerfilPantalla) {
		PerfilPantalla = this.validarEntity(PerfilPantalla);
		StoredProcedureQuery query= this.entityManager.createNamedStoredProcedureQuery("alta_perfil_pantalla");
		query.setParameter("pantalla_id",	PerfilPantalla.getPantalla().getId());
		query.setParameter("perfil_id", 	PerfilPantalla.getPerfil().getId());
		query.setParameter("estatus", 		PerfilPantalla.getEstatus());
		query.execute();
		Object id =  query.getOutputParameterValue("id");
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		String idPerfilPantalla = id.toString();
		PerfilPantalla pPantalla= new PerfilPantalla();
		if(idPerfilPantalla.length()>0) {
			 pPantalla.setId(Long.parseLong(String.valueOf(idPerfilPantalla)));
		}else {
			Utility.errores(error.toString(),mensaje.toString());
		}
		return pPantalla;
	}
	
	/**
	 * Elimina pantallas asociadas a un perfil
	 * @param listaPantallas
	 */
	@Transactional()
	public void eliminarPerfilPantalla(String listaPantallas, Long perfilId) {
		StoredProcedureQuery query= this.entityManager.createNamedStoredProcedureQuery("eliminar_perfil_pantalla");
		query.setParameter("idspantallas", listaPantallas);
		query.setParameter("perfil_id", perfilId);
		query.execute();
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		Utility.errores(error.toString(),mensaje.toString());
	}
	
	
	/**
	 * Actualiza una asignacion de un id Perfil Pantalla
	 * @param perfilPantalla
	 */
	 @Transactional()
		public void modificarPerfilPantalla(PerfilPantalla perfilPantalla) {
		 perfilPantalla =this.validarEntity(perfilPantalla);
			StoredProcedureQuery query= this.entityManager.createNamedStoredProcedureQuery("modificar_perfil_pantalla");
			query.setParameter("id", 			perfilPantalla.getId());
			query.setParameter("pantalla_id", 	perfilPantalla.getPantalla().getId());
			query.setParameter("perfil_id", 	perfilPantalla.getPerfil().getId());
			query.setParameter("estatus", 		perfilPantalla.getEstatus());
			query.execute();
			Object error =  query.getOutputParameterValue("error_codigo");
			Object mensaje =  query.getOutputParameterValue("error_mensaje");
			Utility.errores(error.toString(),mensaje.toString());
		}
	 /**
	  * valida el entity en caso de valores nulos
	  * @param perfilPantalla
	  * @return
	  */
	
	private PerfilPantalla validarEntity(PerfilPantalla perfilPantalla) {
		perfilPantalla.setId(perfilPantalla.getId()!=null?perfilPantalla.getId():Constantes.NUMERO_CERO_TIPO_LONG);
		Perfil perfil = new Perfil();
		perfil.setId(perfilPantalla.getPerfil()!=null?(perfilPantalla.getPerfil().getId()!=null?perfilPantalla.getPerfil().getId():Constantes.NUMERO_CERO):Constantes.NUMERO_CERO);
		perfilPantalla.setPerfil(perfil);
		Pantalla pantalla= new Pantalla();
		pantalla.setId(perfilPantalla.getPantalla()!=null?(perfilPantalla.getPantalla().getId()!=null?perfilPantalla.getPantalla().getId():Constantes.NUMERO_CERO_TIPO_LONG):Constantes.NUMERO_CERO_TIPO_LONG);
		perfilPantalla.setPantalla(pantalla);
		perfilPantalla.setIdUsario(perfilPantalla.getIdUsario()!=null?perfilPantalla.getIdUsario():Constantes.NUMERO_CERO);
		perfilPantalla.setEstatus(perfilPantalla.getEstatus()instanceof Integer?perfilPantalla.getEstatus():Constantes.NUMERO_CERO);
		return perfilPantalla;
	}
	
	
	
}

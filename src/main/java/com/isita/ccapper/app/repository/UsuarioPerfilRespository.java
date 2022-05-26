package com.isita.ccapper.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isita.ccapper.app.entity.Pantalla;
import com.isita.ccapper.app.entity.Perfil;
import com.isita.ccapper.app.entity.PerfilPantalla;
import com.isita.ccapper.app.entity.Usuario;
import com.isita.ccapper.app.entity.UsuarioPerfil;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;

@Repository
public class UsuarioPerfilRespository {
	
	@Autowired
    EntityManager entityManager;
	/**
	 * Buscar Perfiles de usuario
	 * @param usuarioPerfil
	 * @param tipoConsulta
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<UsuarioPerfil> buscarPerfilUsuario(UsuarioPerfil usuarioPerfil, String tipoConsulta){
		usuarioPerfil = this.validarEntity(usuarioPerfil);
		List<UsuarioPerfil> listaUsuarioPerfil = entityManager.createNamedQuery("consulta_usuario_perfil")
				.setParameter(1, usuarioPerfil.getId())
				.setParameter(2, usuarioPerfil.getUsuario().getId())
				.setParameter(3, usuarioPerfil.getPerfil().getId())
				.setParameter(4, tipoConsulta).getResultList();
		return listaUsuarioPerfil;	
	}
	/**
	 * Guardar Perfiles de usuario
	 * @param usuarioPerfil
	 * @return
	 */
	@Transactional()
	public UsuarioPerfil savePerfilUsuario(UsuarioPerfil usuarioPerfil) {
		usuarioPerfil = this.validarEntity(usuarioPerfil);
		StoredProcedureQuery query= this.entityManager.createNamedStoredProcedureQuery("alta_usuario_perfil");
		query.setParameter("usuario_id",	usuarioPerfil.getUsuario().getId());
		query.setParameter("perfil_id", 	usuarioPerfil.getPerfil().getId());
		query.setParameter("estatus", 		usuarioPerfil.getEstatus());
		query.execute();
		Object id =  query.getOutputParameterValue("id");
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		String idUsuarioPerfil = id.toString();
		UsuarioPerfil uPerfil= new UsuarioPerfil();
		if(idUsuarioPerfil.length()>0) {
			uPerfil.setId(Long.parseLong(String.valueOf(idUsuarioPerfil)));
		}else {
			Utility.errores(error.toString(),mensaje.toString());
		}
		return uPerfil;
	}
	
	/**
	 * Eliminar los perfiles de un usuario
	 * @param listaPantallas
	 * @param perfilId
	 */
	@Transactional()
	public void eliminarPerfilUsuario(String listaPantallas, Long usuarioId) {
		StoredProcedureQuery query= this.entityManager.createNamedStoredProcedureQuery("eliminar_usuario_perfil");
		query.setParameter("idsperfiles", listaPantallas);
		query.setParameter("usuario_id", usuarioId);
		query.execute();
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		Utility.errores(error.toString(),mensaje.toString());
	}
	
	/**
	 * Modificar datos de los perfiles de un usuario
	 * @param usuarioPerfil
	 */
	 @Transactional()
		public void modificarPerfilUsuario(UsuarioPerfil usuarioPerfil) {
		 usuarioPerfil =this.validarEntity(usuarioPerfil);
			StoredProcedureQuery query= this.entityManager.createNamedStoredProcedureQuery("modificar_usuario_perfil");
			query.setParameter("id", 			usuarioPerfil.getId());
			query.setParameter("usuario_id", 	usuarioPerfil.getUsuario().getId());
			query.setParameter("perfil_id", 	usuarioPerfil.getPerfil().getId());
			query.setParameter("estatus", 		usuarioPerfil.getEstatus());
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
	
	private UsuarioPerfil validarEntity(UsuarioPerfil usuarioPerfil) {
		usuarioPerfil.setId(usuarioPerfil.getId()!=null?usuarioPerfil.getId():Constantes.NUMERO_CERO_TIPO_LONG);
		Perfil perfil = new Perfil();
		perfil.setId(usuarioPerfil.getPerfil()!=null?(usuarioPerfil.getPerfil().getId()!=null?usuarioPerfil.getPerfil().getId():Constantes.NUMERO_CERO):Constantes.NUMERO_CERO);
		usuarioPerfil.setPerfil(perfil);
		Usuario usuario= new Usuario();
		usuario.setId(usuarioPerfil.getUsuario()!=null?(usuarioPerfil.getUsuario().getId()!=null?usuarioPerfil.getUsuario().getId():Constantes.NUMERO_CERO_TIPO_LONG):Constantes.NUMERO_CERO_TIPO_LONG);
		usuarioPerfil.setUsuario(usuario);
		usuarioPerfil.setEstatus(usuarioPerfil.getEstatus()instanceof Integer?usuarioPerfil.getEstatus():Constantes.NUMERO_CERO);
		return usuarioPerfil;
	}
}

package com.isita.ccapper.app.repository;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.isita.ccapper.app.entity.Usuario;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;

@Repository
public class UsuarioRepository{
	@Autowired
    EntityManager entityManager;
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Usuario> usuarioRepository(Usuario usuario,String tipo_consulta){
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("usuario_con",Usuario.class)
				.registerStoredProcedureParameter("id", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("usuario", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("tipo_consulta", String.class, ParameterMode.IN);
				storedProcedureQuery.setParameter("id", usuario.getId()!=null?usuario.getId():Constantes.NUMERO_CERO);
				storedProcedureQuery.setParameter("usuario", usuario.getUsuario());
				storedProcedureQuery.setParameter("tipo_consulta", tipo_consulta);
				storedProcedureQuery.execute();
				List<Usuario> datoUsuario =storedProcedureQuery.getResultList();
		return datoUsuario;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Usuario> buscarUsuario(Usuario usuario, String tipo_consulta){
		List<Usuario> user = new ArrayList<Usuario>(); 
		List<Usuario> resultset = entityManager.createNamedQuery("consulta_usuario").setParameter(1, 0)
				.setParameter(2, usuario.getUsuario())
				.setParameter(3, 0)
				.setParameter(4, 0)
				.setParameter(5, tipo_consulta).getResultList();
		if(resultset.size()==0) {
			user= new ArrayList<Usuario>(); 
		}else {
			user = resultset;
		}
		
		return user;
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public Usuario buscarPorIdUsuario(int id, String tipo_consulta){
		Usuario usuario = new Usuario();
		
		 List<Usuario> user = entityManager.createNamedQuery("consulta_usuario").setParameter(1, id)
				.setParameter(2, Constantes.VACIO)
				.setParameter(3, 0)
				.setParameter(4, 0)
				.setParameter(5, tipo_consulta).getResultList();
		if(user.size()>0) {
			usuario = user.get(0); 
		}
		return usuario;
	}
	

	@Transactional()
	public Usuario saveUsuario(Usuario usuario) {
		
		StoredProcedureQuery query= this.entityManager.createNamedStoredProcedureQuery("alta_usuario");
		query.setParameter("usuario", 						usuario.getUsuario());
		query.setParameter("contrasena", 					usuario.getContrasena());
		query.setParameter("aplicacion_id", 				usuario.getAplicacionId());
		query.setParameter("tipo_usuario_id", 				usuario.getTipoUsuarioId());
		query.setParameter("correo", 						usuario.getCorreo());
		query.setParameter("activo",						usuario.isActivo());
		query.setParameter("ip_origen", 					usuario.getIpOrigen());
		query.setParameter("intento", 						usuario.getIntento());
		query.setParameter("hora_primer_intento_fallido",	usuario.getHoraPrimerIntentoFallido());
		query.setParameter("hora_bloqueado", 				usuario.getHoraBloqueo());
		query.setParameter("persona_id", 					usuario.getPersona().getId().intValue());
		query.setParameter("estatus", 						usuario.getEstatus());
		query.execute();
		Object id =  query.getOutputParameterValue("id");
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		String idUsuario = id.toString();
		
		if(idUsuario.length()>0) {
			 Long idUser = Long.parseLong(String.valueOf(idUsuario));
			usuario.setId(idUser);
		}else {
			Utility.errores(error.toString(),mensaje.toString());
		}
		return usuario;
	}
	
	 @Transactional()
	public void actualizarUsuario(String usuarios) {
		StoredProcedureQuery query= this.entityManager.createNamedStoredProcedureQuery("eliminar_usuario");
		query.setParameter("idsUsuarios", usuarios);
		query.execute();
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		Utility.errores(error.toString(),mensaje.toString());
	}
	
	 @Transactional()
	public void ModificarUsuario(Usuario usuario,String tipoModificacion) {
		Integer idUsuario = (int) (long) usuario.getId();
		StoredProcedureQuery query= this.entityManager.createNamedStoredProcedureQuery("modificar_usuario");
		Utility.setStoreProcedureEnableNullParametersWithParametersOut(query, query.getParameters().stream().count());
		query.setParameter("id", 							idUsuario);
		query.setParameter("usuario", 						usuario.getUsuario());
		query.setParameter("contrasena", 					usuario.getContrasena());
		query.setParameter("aplicacion_id", 				usuario.getAplicacionId());
		query.setParameter("tipo_usuario_id", 				usuario.getTipoUsuarioId());
		query.setParameter("correo", 						usuario.getCorreo());
		query.setParameter("activo", 						usuario.isActivo());
		query.setParameter("ip_origen", 					usuario.getIpOrigen());
		
		query.setParameter("intento", 						usuario.getIntento());
		query.setParameter("hora_primer_intento_fallido",	usuario.getHoraPrimerIntentoFallido());
		query.setParameter("hora_bloqueado", 				usuario.getHoraBloqueo());
		query.setParameter("tipo_bloqueo", 					usuario.getTipoBloqueo());
		query.setParameter("estatus", 						usuario.getEstatus());
		
		query.setParameter("tipo_modificacion",tipoModificacion);
		
		query.execute();
		/*Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		Utility.errores(error.toString(),mensaje.toString());*/
	}
	/**
	 * 
	 * @param usuario
	 * @param pagina
	 * @param tamanio
	 * @param tipoConsulta
	 * @return
	 */
	@Transactional()
	@SuppressWarnings({  "unchecked", "unused" })
	public List<Usuario> findAll(Integer pagina, Integer tamanio, String tipoConsulta){
		List<Usuario>  listaUsuario;
		return listaUsuario = this.entityManager.createNamedQuery("consulta_usuario")
				.setParameter(1, 0)
				.setParameter(2, Constantes.VACIO)
				.setParameter(3, pagina)
				.setParameter(4, tamanio)
				.setParameter(5, tipoConsulta).getResultList();
	}
	
	@Transactional()
	@SuppressWarnings("unchecked" )
	public List<Usuario> buscarPorNombreUsuario(String nombre, String tipoConsulta) {
		
		List<Usuario> listaUsuario = this.entityManager.createNamedQuery("consulta_usuario")
				.setParameter(1, Constantes.NUMERO_CERO)
				.setParameter(2, nombre)
				.setParameter(3, Constantes.NUMERO_CERO)
				.setParameter(4, Constantes.NUMERO_CERO)
				.setParameter(5, tipoConsulta).getResultList();
		return  listaUsuario;
	}

	@Transactional()
	public void procesoUsuario(int rangoMinutosBloqueo) {
		StoredProcedureQuery query= this.entityManager.createNamedStoredProcedureQuery("proceso_usuario");
		query.setParameter("rango_minutos_bloqueo",rangoMinutosBloqueo);
		query.execute();
	}
	
	
}

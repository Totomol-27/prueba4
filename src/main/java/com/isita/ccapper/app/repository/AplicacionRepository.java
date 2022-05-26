package com.isita.ccapper.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isita.ccapper.app.entity.Aplicacion;
import com.isita.ccapper.app.entity.Usuario;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;

@Repository
public class AplicacionRepository {
	@Autowired
    private  EntityManager entityManager;
	
	
	
	public Aplicacion save(Aplicacion aplicacion) {
		StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("alta_aplicacion");
		query.setParameter("clave", 				aplicacion.getClave());
		query.setParameter("nombre", 				aplicacion.getNombre());
		query.setParameter("descripcion", 			aplicacion.getDescripcion());
		query.setParameter("segundos_inactividad", 	aplicacion.getSegundosInactividad());
		query.setParameter("estatus",				aplicacion.getEstatus());
		query.setParameter("segundos_session", 		aplicacion.getSegundosSession());
		query.execute();
		Object id =  query.getOutputParameterValue("id");
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		String idApp = id.toString();
		Aplicacion objetoAPlicacion = new Aplicacion();
		if(idApp.length()>Constantes.NUMERO_CERO) {			 
			 objetoAPlicacion.setId(Long.parseLong(String.valueOf(idApp)));
		}else {
			Utility.errores(error.toString(),mensaje.toString());
		}
		return objetoAPlicacion;
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Aplicacion> listaAplicacion(Aplicacion aplicacion,Integer page, Integer sizes, String tipoConsulta) {
		Long l = aplicacion.getId();
		Integer idAplicacion = l.intValue(); 
		
		List <Aplicacion> resultList = entityManager.createNamedQuery("aplicacion_consulta")
				.setParameter(1, idAplicacion)
				.setParameter(2, "")
				.setParameter(3, aplicacion.getNombre())
				.setParameter(4, page)
				.setParameter(5, sizes)
				.setParameter(6, tipoConsulta).getResultList();
				List<Aplicacion>  aplicaciones= resultList;		
				
		return aplicaciones;
	}

	
	@SuppressWarnings("unchecked")
	public List<Usuario> usuarioRepository(Usuario usuario,String tipo_consulta){
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("usuario_con",Usuario.class)
				.registerStoredProcedureParameter("usuario", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("tipo_consulta", String.class, ParameterMode.IN);
				storedProcedureQuery.setParameter("usuario", usuario.getUsuario());
				storedProcedureQuery.setParameter("tipo_consulta", tipo_consulta);
				storedProcedureQuery.execute();
				List<Usuario> datoUsuario =storedProcedureQuery.getResultList();
		return datoUsuario;
	}
	
	public void update(Aplicacion aplicacion) {
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("modificar_aplicacion");
		Utility.setStoreProcedureEnableNullParametersWithParametersOut(query, query.getParameters().stream().count());
		query.setParameter("id", 					aplicacion.getId());
		query.setParameter("clave", 				aplicacion.getClave());
		query.setParameter("nombre", 				aplicacion.getNombre());
		query.setParameter("descripcion", 			aplicacion.getDescripcion());
		query.setParameter("segundos_inactividad", 	aplicacion.getSegundosInactividad());
		query.setParameter("estatus", 				aplicacion.getEstatus());
		query.setParameter("segundos_session", 		aplicacion.getSegundosSession());
		query.execute();
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		Utility.errores(error.toString(),mensaje.toString());
	}
	
	public void delete(Long id) {
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("eliminar_aplicion");
		query.setParameter(1, (int) (long)id);
		query.execute();
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		Utility.errores(error.toString(),mensaje.toString());
	}


}

package com.isita.ccapper.app.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isita.ccapper.app.authentication.MissingHeaderInfoException;
import com.isita.ccapper.app.entity.Servicio;

import com.isita.ccapper.app.model.MensajeModel;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;

@Repository
public class ServicioRepository {

	@Autowired
	private EntityManager entityManager;
	
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Servicio> findAll(Servicio servicio, Integer page, Integer sizes, String tipoConsulta) {
		if(servicio.getId() == null) {
			servicio.setId((long) 0);
			
		}
		
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("servicio_consulta");
		query.setParameter("id", servicio.getId().intValue());
		query.setParameter("uri", servicio.getUri());
		query.setParameter("page", page);
		query.setParameter("sizes", sizes);
		query.setParameter("metodo", Constantes.VACIO);
		query.setParameter("usuario", Constantes.VACIO);
		query.setParameter("pantalla_id", servicio.getPantalla()!=null?(servicio.getPantalla().getId()
		!=null?servicio.getPantalla().getId():Constantes.NUMERO_CERO_TIPO_LONG):Constantes.NUMERO_CERO_TIPO_LONG);
		query.setParameter("tipo_consulta", tipoConsulta);
 		query.execute();
 		
		List<Servicio> list = query.getResultList();

		return list;
	}
	public static List<?> convertObjectToList(Object obj) {
	    List<?> list = new ArrayList<>();
	    if (obj.getClass().isArray()) {
	        list = Arrays.asList((Object[])obj);
	    } else if (obj instanceof Collection) {
	        list = new ArrayList<>((Collection<?>)obj);
	    }
	    return list;
	}
	@Transactional(readOnly = false)
	public Servicio save(Servicio servicio) {
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("servicio_alta");
		query.setParameter("uri", servicio.getUri());
		query.setParameter("aplicacion_id", servicio.getAplicacion().getId().intValue());
		query.setParameter("permiso_id", servicio.getPermiso().getId().intValue());
		query.execute();
		Object id =  query.getOutputParameterValue("id");
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");

		if(id != null) {
			servicio.setId(Long.parseLong(String.valueOf(id.toString())));
		}else {
			Utility.errores(error.toString(), mensaje.toString());
		}
		
		return servicio;
	}

	@Transactional(readOnly = false)
	public MensajeModel actuliza(Servicio servicio) {
		long l=servicio.getId();
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("servicio_modificar");
		query.setParameter("id", (int)l);
		query.setParameter("uri", servicio.getUri());
		query.setParameter("aplicacion_id", servicio.getAplicacion().getId().intValue());
		query.setParameter("permiso_id", servicio.getPermiso().getId().intValue());
		query.setParameter("estatus", servicio.getEstatus());
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
	public void delete(Servicio servicio) {
		long l=servicio.getId();
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("servicio_baja");
		query.setParameter("id", (int)l);
		query.setParameter("estatus", 1);
		query.execute();
		Object ids =  query.getOutputParameterValue("ids");
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		Utility.errores(error.toString(),mensaje.toString());
		if(ids.equals(0)) {
			throw new MissingHeaderInfoException("SERVICIO_ASIGNADO_PANTALLA");
		}
		
		MensajeModel  modelos = new MensajeModel();
		modelos.setCodigo(error.toString());
		modelos.setMensaje(mensaje.toString());

	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Servicio> buscarServicio(Servicio servicio, Integer pagina, Integer tamanio, String tipoConsulta) {
		
		StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("servicio_consulta");
		query.setParameter("id", servicio.getId()!=null?servicio.getId().intValue():Constantes.NUMERO_CERO);
		query.setParameter("uri", servicio.getUri()!=null?servicio.getUri():Constantes.VACIO);
		query.setParameter("page", pagina);
		query.setParameter("sizes", tamanio);
		query.setParameter("metodo", servicio.getPermiso()!=null?(servicio.getPermiso().getNombre()!=null?servicio.getPermiso().getNombre():Constantes.VACIO):Constantes.VACIO);
		query.setParameter("usuario", servicio.getUsuario()!=null?servicio.getUsuario():Constantes.VACIO);
		query.setParameter("pantalla_id", servicio.getPantalla()!=null?(servicio.getPantalla().getId()!=null?
				servicio.getPantalla().getId():Constantes.NUMERO_CERO_TIPO_LONG):Constantes.NUMERO_CERO_TIPO_LONG);
		query.setParameter("tipo_consulta", tipoConsulta);
 		query.execute();
 		List<Servicio>lista = new ArrayList<Servicio>();
		List<Object[]> objectList = query.getResultList();
		for (Object[] object : objectList) {
			lista.add(new Servicio(Long.parseLong(String.valueOf(object[0])), String.valueOf(object[1]),null,null,Integer.parseInt(String.valueOf(object[4]))));
		}
		
		return lista;
	}
	
	
	
}

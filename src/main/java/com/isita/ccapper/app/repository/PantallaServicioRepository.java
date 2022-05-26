package com.isita.ccapper.app.repository;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isita.ccapper.app.entity.Pantalla;
import com.isita.ccapper.app.entity.PantallaServicio;
import com.isita.ccapper.app.entity.Servicio;
import com.isita.ccapper.app.entity.Usuario;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;

@Repository
public class PantallaServicioRepository {
	@Autowired
    EntityManager entityManager;
	
	/**
	 * @param pantallaServicio
	 * @param tipo_consulta
	 * @return retorna los valores de pantallaServcio deacuerdo al tipo de consulta
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<PantallaServicio> buscarPantallaServicio(PantallaServicio pantallaServicio, String tipo_consulta){
		List<PantallaServicio> listaPantallaServicio = new ArrayList<PantallaServicio>(); 
		List<PantallaServicio> resultset = entityManager.createNamedQuery("consulta_pantalla_servicio").setParameter(1, 
				pantallaServicio.getId()!=null?pantallaServicio.getId().intValue():Constantes.NUMERO_CERO)
				.setParameter(2, 
						(pantallaServicio.getPantalla()!=null?
						(pantallaServicio.getPantalla().getId()!=null?pantallaServicio.getPantalla().getId().intValue():Constantes.NUMERO_CERO)
						:Constantes.NUMERO_CERO))
				.setParameter(3, (pantallaServicio.getServicio()!=null?
						(pantallaServicio.getServicio().getId()!=null?pantallaServicio.getServicio().getId().intValue():Constantes.NUMERO_CERO)
						:Constantes.NUMERO_CERO))
				.setParameter(4, (pantallaServicio.getPantalla()!=null?
							(pantallaServicio.getPantalla().getNombre()!=null?pantallaServicio.getPantalla().getNombre():Constantes.VACIO)
							:Constantes.VACIO))
				.setParameter(5, (pantallaServicio.getServicio()!=null?
						(pantallaServicio.getServicio().getUri()!=null?pantallaServicio.getServicio().getUri():Constantes.VACIO)
						:Constantes.VACIO))
				.setParameter(6, tipo_consulta).getResultList();
		if(resultset.size()==0) {
			listaPantallaServicio= new ArrayList<PantallaServicio>(); 
		}else {
			listaPantallaServicio.add(pantallaServicio);
		}
		
		return listaPantallaServicio;
	}
	/**
	 * 
	 * @param pantallaServicio
	 * @return guarda los datos de Servicios asignados
	 */
	@Transactional()
	public PantallaServicio savePantallaServicio(PantallaServicio pantallaServicio) {
		pantallaServicio = this.validarEntidadPantallaServicio(pantallaServicio);
		StoredProcedureQuery query= this.entityManager.createNamedStoredProcedureQuery("alta_pantalla_servicio");
		query.setParameter("pantalla_id",	pantallaServicio.getPantalla().getId());
		query.setParameter("servicio_id", 	pantallaServicio.getServicio().getId());
		query.setParameter("estatus", 		pantallaServicio.getEstatus());
		query.execute();
		Object id =  query.getOutputParameterValue("id");
		Object error =  query.getOutputParameterValue("error_codigo");
		Object mensaje =  query.getOutputParameterValue("error_mensaje");
		String idUsuario = id.toString();
		PantallaServicio pServicio= new PantallaServicio();
		if(idUsuario.length()>0) {
			 Long idUser = Long.parseLong(String.valueOf(idUsuario));
			 pServicio.setId(idUser);
		}else {
			Utility.errores(error.toString(),mensaje.toString());
		}
		return pServicio;
	}
	
	/**
	 * metodo elimina los servicios asignados a la pantalla
	 * @param usuarios
	 */
	 @Transactional()
		public void eliminarPantallaServicio(String listaServicios) {
			StoredProcedureQuery query= this.entityManager.createNamedStoredProcedureQuery("eliminar_pantalla_servicio");
			query.setParameter("idservicios", listaServicios);
			query.execute();
			Object error =  query.getOutputParameterValue("error_codigo");
			Object mensaje =  query.getOutputParameterValue("error_mensaje");
			Utility.errores(error.toString(),mensaje.toString());
		}
	 
	 /**
	  * metodo para modificar el registro de pantalla-servicio
	  * @param pantallaServicio
	  */
	 @Transactional()
		public void ModificarPantallaServicio(PantallaServicio pantallaServicio) {
		 	pantallaServicio =this.validarEntidadPantallaServicio(pantallaServicio);
			StoredProcedureQuery query= this.entityManager.createNamedStoredProcedureQuery("modificar_pantalla_servicio");
			query.setParameter("id", 			pantallaServicio.getId());
			query.setParameter("pantalla_id", 	pantallaServicio.getPantalla().getId());
			query.setParameter("servicio_id", 	pantallaServicio.getServicio().getId());
			query.setParameter("estatus", 		pantallaServicio.getEstatus());
			query.execute();
			Object error =  query.getOutputParameterValue("error_codigo");
			Object mensaje =  query.getOutputParameterValue("error_mensaje");
			Utility.errores(error.toString(),mensaje.toString());
		}
	 
	 
	/**
	 * metodo que valida si valores que necesita los sp estan nulos les asigna un valor por default
	 * @param pantallaServicio
	 * @return
	 */
	public PantallaServicio validarEntidadPantallaServicio(PantallaServicio pantallaServicio) {
		pantallaServicio.setId(pantallaServicio.getId()!=null?pantallaServicio.getId():0L);
		Pantalla pantalla= new Pantalla();
		pantalla.setId(pantallaServicio.getPantalla().getId()!=null?pantallaServicio.getPantalla().getId():0L);
		pantallaServicio.setPantalla(pantalla);
		Servicio servicio = new Servicio();
		servicio.setId(pantallaServicio.getServicio().getId()!=null?pantallaServicio.getServicio().getId():0L);
		servicio.setEstatus(pantallaServicio.getEstatus()instanceof Integer?pantallaServicio.getEstatus():Constantes.NUMERO_CERO);
		pantallaServicio.setServicio(servicio);
		return pantallaServicio;
	}
	
}

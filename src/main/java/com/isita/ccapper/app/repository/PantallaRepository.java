package com.isita.ccapper.app.repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import com.isita.ccapper.app.entity.Pantalla;
import com.isita.ccapper.app.entity.Usuario;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PantallaRepository {

    @Autowired
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Pantalla> pantallaRepository(Pantalla pantalla, String tipo_consulta) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("pantalla_con", Pantalla.class)
                .registerStoredProcedureParameter("id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("clave", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("tipo_consulta", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("id", pantalla.getId() != null ? pantalla.getId() : Constantes.NUMERO_CERO);
        storedProcedureQuery.setParameter("clave", pantalla.getClave());
        storedProcedureQuery.setParameter("tipo_consulta", tipo_consulta);
        storedProcedureQuery.execute();
        List<Pantalla> dataPantalla = storedProcedureQuery.getResultList();
        return dataPantalla;

    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public Pantalla buscarPantallaId(int id, String tipo_consulta){
    	System.out.println("Repository valor1 "+id+" repository valor 2 "+tipo_consulta);
        Pantalla pantalla = new Pantalla();

    List<Pantalla> listaPantalla =   entityManager.createNamedQuery("consulta_pantalla").setParameter(1, id)
                              .setParameter(2, Constantes.VACIO)
                              .setParameter(3, Constantes.VACIO)
                              .setParameter(4, 0)
                              .setParameter(5, 0)
                              .setParameter(6, 0)
                              .setParameter(7, tipo_consulta).getResultList();
       // System.out.println(pant+"Informacion----------------------------------->>>>>>>>>>>><<Linea 47 PantallaRepository");
    if(listaPantalla.size()>0) {
        pantalla = listaPantalla.get(0);
    }else {
    	pantalla = new Pantalla();
    }
        return pantalla;                             
    }

    @Transactional()
    public Pantalla savePantalla(Pantalla pantalla){
        StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("alta_pantalla");
        query.setParameter("clave",  pantalla.getClave());
        query.setParameter("nombre", pantalla.getNombre());
        query.setParameter("descripcion", pantalla.getDescripcion());
        query.setParameter("indice", pantalla.getIndice());
        query.setParameter("pantalla_padre", pantalla.getPantallaPadre());
        query.setParameter("aplicacion_id", pantalla.getAplicacionId());
        query.setParameter("estatus", pantalla.getEstatus());
        query.execute();
        Object id = query.getOutputParameterValue("id");
        Object error = query.getOutputParameterValue("error_codigo");
        Object mensaje = query.getOutputParameterValue("error_mensaje");        
        if(id != null){
        	pantalla.setId(Long.parseLong(String.valueOf(id.toString())));
        } else {
            Utility.errores(error.toString(), mensaje.toString());
        }
        return pantalla;
    }

    public void actualizarPantalla(String pantallas){
        StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("eliminar_pantalla");
        query.setParameter("idPantallas", pantallas);
        query.execute();
        Object error = query.getOutputParameterValue("error_codigo");
        Object mensaje = query.getOutputParameterValue("error_mensaje");
        Utility.errores(error.toString(), mensaje.toString());
    }

    @Transactional
    public void modificarPantalla(Pantalla pantalla, String tipoModificacion){
        Integer idPantalla = (int)(long) pantalla.getId();
        StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("modificar_pantalla");
        Utility.setStoreProcedureEnableNullParametersWithParametersOut(query, query.getParameters().stream().count());
        query.setParameter("id", idPantalla);
        query.setParameter("clave", pantalla.getClave());
        query.setParameter("nombre", pantalla.getNombre());
        query.setParameter("descripcion", pantalla.getDescripcion());
        query.setParameter("indice", pantalla.getIndice());
        query.setParameter("pantalla_padre", pantalla.getPantallaPadre());
        query.setParameter("aplicacion_id", pantalla.getAplicacionId()!=null?pantalla.getAplicacionId():Constantes.NUMERO_CERO);
        query.setParameter("estatus", pantalla.getEstatus());
        //query.setParameter("tipo_modificacion", tipoModificacion);
		/*
		 * Object error = query.getOutputParameterValue("error_codigo"); Object mensaje
		 * = query.getOutputParameterValue("error_mensaje");
		 */
        
        query.execute();
        
		/*
		 * Object error = query.getOutputParameterValue("error_codigo"); 
		 * Object mensaje = query.getOutputParameterValue("error_mensaje");
		 * Utility.errores(error.toString(), mensaje.toString());
		 */
    }

    @Transactional()
    @SuppressWarnings({"unchecked","unused"})
    public List<Pantalla> findAll(Integer pagina, Integer tamanio, String tipoConsulta){
        List<Pantalla> listaPantalla;
        return listaPantalla = this.entityManager.createNamedQuery("consulta_pantalla")
                               .setParameter(1, 0)
                               .setParameter(2, Constantes.VACIO)
                               .setParameter(3, Constantes.VACIO)
                               .setParameter(4, pagina)
                               .setParameter(5, tamanio)
                               .setParameter(6, 0)
                               .setParameter(7, tipoConsulta).getResultList();
    }
    
    
    @SuppressWarnings("unchecked")
   	@Transactional
   	public List<Pantalla> findByName(String nombre, String tipoConsulta){
    	Pantalla pantalla = new Pantalla();
    	List<Pantalla> resultPantalla = this.entityManager.createNamedQuery("consulta_pantalla")
    			.setParameter(1, 0)
                .setParameter(2, nombre)
                .setParameter(3, Constantes.VACIO)
                .setParameter(4, 0)
                .setParameter(5, 0)
                .setParameter(6, 0)
                .setParameter(7, tipoConsulta).getResultList();
    	
    	if(resultPantalla.size() > 0 ) {
    		pantalla = resultPantalla.get(0);
    	}
    	
    	return resultPantalla;
    }
    
    @SuppressWarnings("unchecked")
   	@Transactional
   	public List<Pantalla> buscarPantallas(Pantalla pantalla, Integer pagina, Integer tamanio, String tipoConsulta){
   
    	List<Pantalla> resultPantalla = this.entityManager.createNamedQuery("consulta_pantalla")
    			.setParameter(1, pantalla.getId()!=null?pantalla.getId().intValue():Constantes.NUMERO_CERO)
                .setParameter(2, pantalla.getNombre()!=null?pantalla.getNombre():Constantes.VACIO)
                .setParameter(3, pantalla.getClave()!=null?pantalla.getClave():Constantes.VACIO)
                .setParameter(4, pagina!=null?pagina:Constantes.NUMERO_CERO)
                .setParameter(5, tamanio!=null?tamanio:Constantes.NUMERO_CERO)
                .setParameter(6, pantalla.getPerfil()!=null?(pantalla.getPerfil().getId()!=null?pantalla.getPerfil().getId():
                	Constantes.NUMERO_CERO):Constantes.NUMERO_CERO)
                .setParameter(7, tipoConsulta).getResultList();
    	
    	if(resultPantalla.size() > 0 ) {
    		pantalla = resultPantalla.get(0);
    	}
    	
    	return resultPantalla;
    }
    
    

}

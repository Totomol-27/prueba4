package com.isita.ccapper.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import com.isita.ccapper.app.entity.Persona;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersonaRepository {
    
    @Autowired
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Persona> personaRepository(Persona persona, String tipo_consulta){
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("persona_con", Persona.class)
                             .registerStoredProcedureParameter("id", Integer.class, ParameterMode.IN)
                             .registerStoredProcedureParameter("tipo_consulta", String.class, ParameterMode.IN);
                             storedProcedureQuery.setParameter("id", persona.getId() != null ? persona.getId() : Constantes.NUMERO_CERO);
                             storedProcedureQuery.setParameter("tipo_consulta", tipo_consulta);
                             storedProcedureQuery.execute();
                             List<Persona> dataPersona = storedProcedureQuery.getResultList();
                return dataPersona;
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public Persona buscarPersonaById(int id, String tipo_consulta){
    	System.out.println("PersonaRepository buscar por id "+id+" Parama: "+tipo_consulta);
        Persona personaObj = new Persona();
        List<Persona> resultPersona = entityManager.createNamedQuery("consulta_persona")
                                                  .setParameter(1, id)
                                                  .setParameter(2, 0)
                                                  .setParameter(3, 0)
                                                  .setParameter(4, 0)
                                                  .setParameter(5, 0)
                                                  .setParameter(6, Constantes.VACIO)
                                                  .setParameter(7, Constantes.VACIO)
                                                  .setParameter(8, tipo_consulta).getResultList();
            
                if(resultPersona.size() > 0){
                    personaObj = resultPersona.get(0);
                }
                return personaObj;
    }
    
    

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Persona> buscarPersonaByNumeroSeguridadSocial(String nss, String tipoConsulta){
    	System.out.println("PersonaRepository buscar por id "+nss+" Parama: "+tipoConsulta);
        Persona persona = new Persona();
        List<Persona> resultPersona = entityManager.createNamedQuery("consulta_persona")
                                                  .setParameter(1, 0)
                                                  .setParameter(2, 0)
                                                  .setParameter(3, 0)
                                                  .setParameter(4, 0)
                                                  .setParameter(5, 0)
                                                  .setParameter(6, nss)
                                                  .setParameter(7, Constantes.VACIO)
                                                  .setParameter(8, tipoConsulta).getResultList();
            
 
                return resultPersona;
    }
    
    

    @Transactional
    public Persona savePersona(Persona persona){
    	System.out.println(persona.getFechaNacimiento()+"fecha en personaRepository");
        StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("alta_persona");
        query.setParameter("cliente_id",                persona.getCliente_id());
        query.setParameter("apellido_paterno",          persona.getApellidoPaterno());
        query.setParameter("apellido_materno",          persona.getApellidoMaterno());
        query.setParameter("primer_nombre",             persona.getPrimerNombre());
        query.setParameter("segundo_nombre",            persona.getSegundoNombre()!=null?persona.getSegundoNombre():Constantes.VACIO);
        query.setParameter("fecha_nacimiento",          persona.getFechaNacimiento());
        query.setParameter("sexo",                      persona.getSexo());
        query.setParameter("calle",                     persona.getCalle());
        query.setParameter("numero_casa",               persona.getNumeroCasa());
        query.setParameter("colonia",                   persona.getColonia());
        query.setParameter("codigo_postal",             persona.getCodigoPostal());
        query.setParameter("municipio",                 persona.getMunicipio());
        query.setParameter("estado",                    persona.getEstado());
        query.setParameter("grado_estudio",             persona.getGradoEstudio());
        query.setParameter("no_celular",                persona.getNumCelular());
        query.setParameter("no_telefono",               persona.getNumTelefono());
        query.setParameter("corre_electronico",         persona.getEmail());
        query.setParameter("estado_civil",              persona.getEstadoCivil());
        query.setParameter("peso",                      persona.getPeso());
        query.setParameter("estatura",                  persona.getEstatura());
        query.setParameter("nss",                       persona.getNss());
        query.setParameter("curp",                      persona.getCurp());
        query.setParameter("estatus",                   persona.getEstatus());
        query.execute();

        Object id = query.getOutputParameterValue("id");
        Object error = query.getOutputParameterValue("error_codigo");
        Object mensaje = query.getOutputParameterValue("error_mensaje");
        
        if(id != null){
        	persona.setId(Long.parseLong(String.valueOf(id.toString())));
        }else {
        	System.out.println("Error al guardar la informacion");
            Utility.errores(error.toString(), mensaje.toString()+"error al guardar la informacion");
        }
        return persona;
    }

    public void actualizarPersona(String personas){
    	System.out.println("PersonaRepository ID persona a eliminar: "+personas);
        StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("eliminar_persona");
        query.setParameter("idpersonas", personas);
        query.execute();
        Object error = query.getOutputParameterValue("error_codigo");
        Object mensaje = query.getOutputParameterValue("error_mensaje");
        Utility.errores(error.toString(), mensaje.toString());
    }

    @Transactional()
    public void modificarPersona(Persona persona, String tipo_modiciacion){
        Integer idPersona = (int)(long) persona.getId();
        StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("modificar_persona");
        Utility.setStoreProcedureEnableNullParametersWithParametersOut(query, query.getParameters().stream().count());
        query.setParameter("id",  idPersona);
        query.setParameter("cliente_id",                persona.getCliente_id());
        query.setParameter("apellido_paterno", 			persona.getApellidoPaterno());
        query.setParameter("apellido_materno",          persona.getApellidoMaterno());
        query.setParameter("primer_nombre",             persona.getPrimerNombre());
        query.setParameter("segundo_nombre",            persona.getSegundoNombre()!=null?persona.getSegundoNombre():Constantes.VACIO);
        query.setParameter("fecha_nacimiento",          persona.getFechaNacimiento());
        query.setParameter("sexo",                      persona.getSexo());
        query.setParameter("calle",                     persona.getCalle());
        query.setParameter("numero_casa",               persona.getNumeroCasa());
        query.setParameter("colonia",                   persona.getColonia());
        query.setParameter("codigo_postal",             persona.getCodigoPostal());
        query.setParameter("municipio",                 persona.getMunicipio());
        query.setParameter("estado",                    persona.getEstado());
        query.setParameter("grado_estudio",             persona.getGradoEstudio());
        query.setParameter("no_celular",                persona.getNumCelular());
        query.setParameter("no_telefono",               persona.getNumTelefono());
        query.setParameter("corre_electronico",         persona.getEmail());
        query.setParameter("estado_civil",              persona.getEstadoCivil());
        query.setParameter("peso",                      persona.getPeso());
        query.setParameter("estatura",                  persona.getEstatura());
        query.setParameter("nss",                       persona.getNss());
        query.setParameter("curp",                      persona.getCurp());
        query.setParameter("estatus",                   persona.getEstatus());
        query.execute();
    }
    
    @Transactional()
	@SuppressWarnings({  "unchecked", "unused" })
    public List<Persona> findAll(Integer pagina, Integer tamanio, String tipo_consulta){
    	System.out.println("Lista de parametros PersonaRepository "+ pagina+" tamaño "+tamanio+" tipo de consulta "+tipo_consulta);
        List<Persona> listaPersona;
         listaPersona = this.entityManager.createNamedQuery("consulta_persona")
                             .setParameter(1, 1)
                             .setParameter(2, pagina)
                             .setParameter(3, tamanio)
                             .setParameter(4, 0)
                             .setParameter(5, 0)
                             .setParameter(6, Constantes.VACIO)
                             .setParameter(7, Constantes.VACIO)
                             .setParameter(8, tipo_consulta).getResultList();
         return listaPersona;
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Persona> findByName(String nombre, String tipoConsulta) {
    	Persona persona = new Persona();
    	List<Persona> resultNamePersona = entityManager.createNamedQuery("consulta_persona")
    									  .setParameter(1, 0)
    									  .setParameter(2, 0)
    									  .setParameter(3, 0)
    									  .setParameter(4, 0)
    									  .setParameter(5, 0)
    									  .setParameter(6, Constantes.VACIO)
    									  .setParameter(7, nombre)
    									  .setParameter(8, tipoConsulta).getResultList();
    	if(resultNamePersona.size() > 0) {
    		persona = resultNamePersona.get(0);
    	}
    	return resultNamePersona;
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Persona> findByNameClienteId(Integer clienteId, String nombre, String tipoConsulta) {
  
    	List<Persona> resultNamePersona = entityManager.createNamedQuery("consulta_persona")
							    			  .setParameter(1, 0)
											  .setParameter(2, 0)
											  .setParameter(3, 0)
											  .setParameter(4, clienteId)
											  .setParameter(5, 0)
											  .setParameter(6, Constantes.VACIO)
											  .setParameter(7, nombre)
											  .setParameter(8, tipoConsulta).getResultList();
//      	Persona personas = new Persona();
//    	if(resultNamePersona.size() > 0) {
//    		personas = resultNamePersona.get(0);
//    	}
    	return resultNamePersona;
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Persona> listPerson(String tipoConsulta){
    	List<Persona> lstPersona = entityManager.createNamedQuery("consulta_persona")
    							   .setParameter(1, 0)
    							   .setParameter(2, 0)
    							   .setParameter(3, 0)
    							   .setParameter(4, 0)
    							   .setParameter(5, 0)
    							   .setParameter(6, Constantes.VACIO)
    							   .setParameter(7, Constantes.VACIO)
    							   .setParameter(8, tipoConsulta).getResultList();
    	Persona persona = new Persona();
    	if(lstPersona.size() > 0) {
    		persona = lstPersona.get(0);
    	}
    	return lstPersona;
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Persona>  listEventos(Integer id ,String tipoConsulta) {
    	List<Persona> lstPersona = entityManager.createNamedQuery("consulta_persona")
				   .setParameter(1, 0)
				   .setParameter(2, 0)
				   .setParameter(3, 0)
				   .setParameter(4, 0)
				   .setParameter(5, id)
				   .setParameter(6, Constantes.VACIO)
				   .setParameter(7, Constantes.VACIO)
				   .setParameter(8, tipoConsulta).getResultList();
	Persona persona = new Persona();
	if(lstPersona.size() > 0) {
	persona = lstPersona.get(0);
	}
	return lstPersona;
	    }

}

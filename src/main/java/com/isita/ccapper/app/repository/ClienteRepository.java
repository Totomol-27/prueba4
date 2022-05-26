package com.isita.ccapper.app.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isita.ccapper.app.entity.Cliente;
import com.isita.ccapper.app.entity.Usuario;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;

@Repository
public class ClienteRepository {

	@Autowired
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Cliente> clienteRepository(Cliente cliente, String tipo_consulta){
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("cliente_con", Cliente.class)
							 .registerStoredProcedureParameter("id", Integer.class, ParameterMode.IN)
							 .registerStoredProcedureParameter("tipo_consulta", String.class, ParameterMode.IN);
							 storedProcedureQuery.setParameter("id", cliente.getId() != null ? cliente.getId() : Constantes.VACIO);
							 storedProcedureQuery.setParameter("tipo_consulta", tipo_consulta);
							 storedProcedureQuery.execute();
							 List<Cliente> dataCliente = storedProcedureQuery.getResultList();
							 return dataCliente;
	}
	
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public Cliente buscarEmpresaById(int id, String tipo_consulta) {
    	Cliente empresaObj = new Cliente();
    	List<Cliente> listEmpresa = entityManager.createNamedQuery("consulta_cliente")
    							.setParameter(1, id)
    							.setParameter(2, Constantes.VACIO)
    							.setParameter(3, 0)
    							.setParameter(4, 0)
    							.setParameter(5, tipo_consulta).getResultList();
    	if( listEmpresa.size() > 0) {
    		empresaObj = listEmpresa.get(0);
    	}
    	return empresaObj;
    }
    
    
    @Transactional()
    public Cliente saveEmpresa(Cliente cliente) {
    	StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("alta_cliente");
    	query.setParameter("nombre", cliente.getNombre());
    	query.setParameter("descripcion", cliente.getDescripcion());
    	query.setParameter("estado_id", cliente.getEstado_id());
    	query.setParameter("giro", cliente.getGiro());
    	query.setParameter("clave", cliente.getClave());
    	query.setParameter("rfc", cliente.getRfc());
    	query.setParameter("telefono", cliente.getTelefono());
    	query.setParameter("correo", cliente.getCorreo());
    	query.setParameter("razon_social", cliente.getRazon_social());
    	query.setParameter("municipio", cliente.getMunicipio());
    	query.setParameter("colonia", cliente.getColonia());
    	query.setParameter("calle", cliente.getCalle());
    	query.setParameter("numero_exterior", cliente.getNumero_exterior());
    	query.setParameter("estatus", cliente.getEstatus());
    	query.execute();
    	Object id = query.getOutputParameterValue("id");
    	Object error = query.getOutputParameterValue("error_codigo");
    	Object mensaje = query.getOutputParameterValue("error_mensaje");
    	String idEmpresa = id.toString();
    	if(idEmpresa.length() > 0) {
    		Long idEmp = Long.parseLong(String.valueOf(idEmpresa));
    		cliente.setId(idEmp);
    	}else {
    		Utility.errores(error.toString(), mensaje.toString());
    	}
    	
    	return cliente;
    }
    
    public void actualizarEmpresa(String clientes) {
    	StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("eliminar_cliente");
    	query.setParameter("idclientes", clientes);
    	query.execute();
    	Object error = query.getOutputParameterValue("error_codigo");
    	Object mensaje = query.getOutputParameterValue("error_mensaje");
    	Utility.errores(error.toString(), mensaje.toString());
    }
    
    @Transactional()
    public void modificarEmpresa(Cliente cliente, String tipo_modificacion) {
    	Integer IdEmpresa = (int)(long) cliente.getId();
    	StoredProcedureQuery query = this.entityManager.createNamedStoredProcedureQuery("modificar_cliente");
    	Utility.setStoreProcedureEnableNullParametersWithParametersOut(query, query.getParameters().stream().count());
    	
    	query.setParameter("id", IdEmpresa);
    	query.setParameter("nombre", cliente.getNombre());
    	query.setParameter("descripcion", cliente.getDescripcion());
    	query.setParameter("estado_id", cliente.getEstado_id());
    	query.setParameter("giro", cliente.getGiro());
    	query.setParameter("clave", cliente.getClave());
    	query.setParameter("rfc", cliente.getRfc());
    	query.setParameter("telefono", cliente.getTelefono());
    	query.setParameter("correo", cliente.getCorreo());
    	query.setParameter("razon_social", cliente.getRazon_social());
    	query.setParameter("municipio", cliente.getMunicipio());
    	query.setParameter("colonia", cliente.getColonia());
    	query.setParameter("calle", cliente.getCalle());
    	query.setParameter("numero_exterior", cliente.getNumero_exterior());
    	query.setParameter("estatus", cliente.getEstatus());
    	query.execute();
    	
    }
    
    @Transactional()
    @SuppressWarnings({"unchecked", "unused"})
    public List<Cliente> findAll(Integer pagina, Integer tamanio, String tipo_consulta){
    	System.out.println(pagina);
    	System.out.println(tamanio);
    	System.out.println(tipo_consulta);
    	List<Cliente> listEmpresa;
    	return listEmpresa = this.entityManager.createNamedQuery("consulta_cliente")
    						.setParameter(1, 0)
    						.setParameter(2, Constantes.VACIO)
    						.setParameter(3, pagina)
    						.setParameter(4, tamanio)
    						.setParameter(5, tipo_consulta).getResultList();
    }
    
    @SuppressWarnings("unchecked")
	@Transactional
	public List<Cliente> buscarEmpresaNombre(String nombre, String tipo_consulta){
    	
    	List<Cliente> resultEmpresa = entityManager.createNamedQuery("consulta_cliente")
    				  .setParameter(1, 0)
    				  .setParameter(2, nombre)
    				  .setParameter(3, 0)
    				  .setParameter(4, 0)
    				  .setParameter(5, tipo_consulta).getResultList();
    	return resultEmpresa;
    }
    
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Cliente> lstClientes(String tipoConsulta){
    	List<Cliente> lstCliente = entityManager.createNamedQuery("consulta_cliente")
    				  .setParameter(1, 0)
    				  .setParameter(2, Constantes.VACIO)
    				  .setParameter(3, 0)
    				  .setParameter(4, 0)
    				  .setParameter(5, tipoConsulta).getResultList();
    	Cliente clientelst = new Cliente();
    	if(lstCliente.size()> 0) {
    		clientelst = lstCliente.get(0);
    	}
    	return lstCliente;
    }
}

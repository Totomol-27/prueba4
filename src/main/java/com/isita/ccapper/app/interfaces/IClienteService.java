package com.isita.ccapper.app.interfaces;

import java.util.List;

import com.isita.ccapper.app.entity.Cliente;

public interface IClienteService {
	public List<Cliente> findAll(Integer pagina, Integer tamanio, String tipoConsulta);
	public Cliente findById(Long id, String tipo_consulta);
	public List<Cliente> findByName (String clave, String tipo_consulta);
	public List<Cliente> lstClientes (String tipoConsulta);
	public Cliente save (Cliente cliente);
	public void delete (String clientes);
	public void update (Cliente cliente, String tipo_modificacion);
}

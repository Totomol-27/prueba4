package com.isita.ccapper.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.entity.Cliente;
import com.isita.ccapper.app.interfaces.IClienteService;
import com.isita.ccapper.app.repository.ClienteRepository;

@Service
public class ClienteService implements IClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;
	private List<Cliente> cliente = new ArrayList<>();

	@Override
	public List<Cliente> findAll(Integer pagina, Integer tamanio, String tipoConsulta) {
		return clienteRepository.findAll(pagina, tamanio, tipoConsulta);
	}

	@Override
	public Cliente findById(Long id, String tipo_consulta) {
		Integer idEmpresa = (int)(long) id;
		return clienteRepository.buscarEmpresaById(idEmpresa, tipo_consulta);
	}

	@Override
	public List<Cliente> findByName(String nombre, String tipo_consulta) {
		
		return clienteRepository.buscarEmpresaNombre(nombre, tipo_consulta);
	}

	@Override
	public Cliente save(Cliente cliente) {
		
		return clienteRepository.saveEmpresa(cliente);
	}

	@Override
	public void delete(String clientes) {
		clienteRepository.actualizarEmpresa(clientes);
		
	}

	@Override
	public void update(Cliente cliente, String tipo_modificacion) {
		clienteRepository.modificarEmpresa(cliente, tipo_modificacion);
	}

	@Override
	public List<Cliente> lstClientes(String tipoConsulta) {
		return clienteRepository.lstClientes(tipoConsulta);
	}

}

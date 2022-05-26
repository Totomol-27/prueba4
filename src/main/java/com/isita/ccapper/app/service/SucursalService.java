package com.isita.ccapper.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.entity.Sucursal;
import com.isita.ccapper.app.interfaces.ISucursalService;
import com.isita.ccapper.app.repository.SucursalRepository;

@Service
public class SucursalService implements ISucursalService {

	@Autowired
	private SucursalRepository repository;
	
	@Override
	public List<Sucursal> findAll(Sucursal sucursal, Integer page, Integer sizes, String tipoConsulta) {
		return repository.findAll(sucursal, page, sizes, tipoConsulta);
	}

}

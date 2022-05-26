package com.isita.ccapper.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.entity.Credencial;
import com.isita.ccapper.app.interfaces.ICredencialService;
import com.isita.ccapper.app.repository.CredencialRepository;

@Service
public class CredencialService implements ICredencialService {

	@Autowired
	private CredencialRepository credencialRepository;
	
	
	@Override
	public Credencial save(Credencial credencial) {	
		return  credencialRepository.save(credencial) ;
	}


	@Override
	public Credencial findByUserId(Credencial credencial, String tipoConsulta) {
		return credencialRepository.findByUserId(credencial, tipoConsulta);
	}
	
}

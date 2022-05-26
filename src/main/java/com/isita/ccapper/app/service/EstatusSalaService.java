package com.isita.ccapper.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.entity.EstatusSala;
import com.isita.ccapper.app.interfaces.IEstatusSalaService;
import com.isita.ccapper.app.repository.EstatusSalaRepository;

@Service
public class EstatusSalaService implements IEstatusSalaService {

	@Autowired
	private EstatusSalaRepository estatusSalaRepository;
	
	@Override
	public List<EstatusSala> findAll(EstatusSala estatusSala, Integer page, Integer sizes, String tipoConsulta) {
		return estatusSalaRepository.findAll(estatusSala, page, sizes, tipoConsulta);
	}

	@Override
	public EstatusSala save(EstatusSala estatusSala) {
		return estatusSalaRepository.save(estatusSala);
	}

	@Override
	public void actuliza(EstatusSala estatusSala) {
		estatusSalaRepository.actuliza(estatusSala);
	}

	@Override
	public void delete(EstatusSala estatusSala) {
		estatusSalaRepository.delete(estatusSala);
	}

}

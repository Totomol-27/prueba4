package com.isita.ccapper.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.entity.TipoSala;
import com.isita.ccapper.app.interfaces.ITipoSalaService;
import com.isita.ccapper.app.repository.TipoSalaRepository;

@Service
public class TipoSalaService implements ITipoSalaService {

	@Autowired
	private TipoSalaRepository tipoSalaRepository;
	
	@Override
	public List<TipoSala> findAll(TipoSala tipoSala, Integer page, Integer sizes, String tipoConsulta) {
		return tipoSalaRepository.findAll(tipoSala, page, sizes, tipoConsulta);
	}

	@Override
	public TipoSala save(TipoSala tipoSala) {
		return tipoSalaRepository.save(tipoSala);
	}

	@Override
	public void actuliza(TipoSala tipoSala) {
		tipoSalaRepository.actuliza(tipoSala);
	}

	@Override
	public void delete(TipoSala tipoSala) {
		tipoSalaRepository.delete(tipoSala);
	}

}

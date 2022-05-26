package com.isita.ccapper.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.entity.Sala;
import com.isita.ccapper.app.interfaces.ISalaService;
import com.isita.ccapper.app.repository.SalaRepository;

@Service
public class SalaService implements ISalaService {

	@Autowired
	private SalaRepository reposiroty;
	
	@Override
	public List<Sala> findAll(Sala sala, Integer page, Integer sizes, String tipoConsulta) {
		return reposiroty.findAll(sala, page, sizes, tipoConsulta);
	}

	@Override
	public Sala save(Sala sala) {
		return reposiroty.save(sala);
	}

	@Override
	public void actuliza(Sala sala) {
			reposiroty.actuliza(sala);
	}

	@Override
	public void delete(Sala sala) {
		reposiroty.delete(sala);
	}
}

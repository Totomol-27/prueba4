package com.isita.ccapper.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isita.ccapper.app.entity.Programacion;
import com.isita.ccapper.app.interfaces.IProgramacionService;
import com.isita.ccapper.app.repository.ProgramacionRepository;

@Service
public class ProgramacionService implements IProgramacionService{

	@Autowired
	private ProgramacionRepository programacionRepository;
	
	@Override
	public Programacion save(Programacion programacion) {
		return programacionRepository.save(programacion);
	}


	@Override
	public void update(Programacion programacion) {
		programacionRepository.modificarProgramacion(programacion);
	}

	@Override
	public List<Programacion> findAll(Programacion programacion, Integer page, Integer sizes, String tipoConsulta) {
		return  programacionRepository.findAll(programacion, page, sizes, tipoConsulta);
	}

	@Override
	public void delete(String idprogramacion, Integer eventoId, String tipoEliminacion) {
		programacionRepository.eliminarProgramacion(idprogramacion, eventoId, tipoEliminacion);	
	}

}

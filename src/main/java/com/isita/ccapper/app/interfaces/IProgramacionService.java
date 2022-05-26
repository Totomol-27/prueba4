package com.isita.ccapper.app.interfaces;

import java.util.List;

import com.isita.ccapper.app.entity.Programacion;

public interface IProgramacionService {
	public Programacion save(Programacion programacion);
	public List<Programacion> findAll(Programacion programacion,Integer page,Integer sizes, String tipoConsulta);
	public void delete(String idprogramacion, Integer eventoId, String tipoEliminacion);
	public void update(Programacion programacion);
}

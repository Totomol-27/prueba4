package com.isita.ccapper.app.interfaces;

import java.util.List;

import com.isita.ccapper.app.entity.Pantalla;

public interface IPantallaService {
    public List<Pantalla> findAll(Integer pagina, Integer tamanio, String tipoConsulta);
    public Pantalla findById(Long id, String tipo_consulta);
    public Pantalla save(Pantalla pantalla);
    public void delete(String pantallas);
    public void update(Pantalla pantalla, String tipoModificacion);	
    public List<Pantalla> findByName(String nombre, String tipoConsulta);
    public List<Pantalla> buscarPantallaClave(String clave, String tipoConsulta);
    public List<Pantalla> buscarPantallasPorIdPerfil(Integer id, String tipoConsulta);
}

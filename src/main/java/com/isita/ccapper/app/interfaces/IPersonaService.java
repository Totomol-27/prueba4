package com.isita.ccapper.app.interfaces;

import java.util.List;

import com.isita.ccapper.app.entity.Persona;

public interface IPersonaService {
    public List<Persona> findAll(Integer pagina, Integer tamanio, String tipoConsulta);
    public Persona findById(Long id, String tipo_consulta);
    public Persona save(Persona persona);
    public void delete(String personas);
    public void update(Persona persona, String tipoModificiacion);
    public Persona buscarSeguroSocial(Persona persona, String tipoConsulta);
    public List<Persona> findByName(String nombre, String tipoConsulta);
    public List<Persona> findByNameClienteId(Integer clienteId, String nombre, String tipoConsulta);
    public List<Persona> ListPerson(String tipoConsulta);
    
}

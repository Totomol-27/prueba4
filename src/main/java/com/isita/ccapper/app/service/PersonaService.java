package com.isita.ccapper.app.service;

import com.isita.ccapper.app.entity.Persona;
import com.isita.ccapper.app.interfaces.IPersonaService;
import com.isita.ccapper.app.repository.PersonaRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersonaService{

    @Autowired
    private PersonaRepository personaRepository;
    private List<Persona> persona = new ArrayList<>();
	@Override
	public List<Persona> findAll(Integer pagina, Integer tamanio, String tipoConsulta) {
		
		return personaRepository.findAll(pagina, tamanio, tipoConsulta);
	}
	@Override
	public Persona findById(Long id, String tipo_consulta) {
        
        Integer idPersona = (int)(long) id;
		return personaRepository.buscarPersonaById(idPersona, tipo_consulta);
	}
	@Override
	public Persona save(Persona persona) {
		
		return personaRepository.savePersona(persona);
	}
	@Override
	public void delete(String personas) {
		
		personaRepository.actualizarPersona(personas);
	}
	@Override
	public void update(Persona persona, String tipoModificiacion) {
		
		personaRepository.modificarPersona(persona, tipoModificiacion);
	}
	@Override
	public Persona buscarSeguroSocial(Persona persona, String tipoConsulta) {
		List<Persona> listaPersona = personaRepository.buscarPersonaByNumeroSeguridadSocial(persona.getNss(), tipoConsulta);
		Persona objPersona= new Persona();
		if(listaPersona.size()>0) {
			objPersona = listaPersona.get(0);
		}
		return objPersona;
	}
	@Override
	public List<Persona> findByName(String nombre, String tipoConsulta) {
		return personaRepository.findByName(nombre, tipoConsulta);
	}
	@Override
	public List<Persona> findByNameClienteId(Integer clienteId, String nombre, String tipoConsulta) {
		return personaRepository.findByNameClienteId(clienteId, nombre, tipoConsulta);
	}
	@Override
	public List<Persona> ListPerson(String tipoConsulta) {
		return personaRepository.listPerson(tipoConsulta);
	}
	
	
	public List<Persona> listEvento(Integer id, String tipoConsulta){
		return personaRepository.listEventos(id, tipoConsulta);
	}
  
	public List<Persona> buscarNss(String nss, String tipoConsulta){
		return personaRepository.buscarPersonaByNumeroSeguridadSocial(nss, tipoConsulta);
	}
}

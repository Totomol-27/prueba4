package com.isita.ccapper.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.isita.ccapper.app.entity.Evento;
import com.isita.ccapper.app.service.EventoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/buscar/{id}")
    public  List<Evento>  findById(@PathVariable Long id, @RequestParam String tipo_consulta){
        Evento evento = eventoService.findById(id, tipo_consulta);
        List<Evento> eventos = new ArrayList<>();
        eventos.add(evento);
        return eventos;
    }

  
    @GetMapping("/findAll")
    public List<Evento> findAll(@RequestParam Integer pagina, @RequestParam Integer tamanio, @RequestParam String tipoConsulta){
        return eventoService.findAll(pagina, tamanio, tipoConsulta);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Evento save(@RequestBody Evento evento){
        return eventoService.save(evento);
    }

    @DeleteMapping(value = "/{listaEvento}")
    public ResponseEntity<?> delete(@PathVariable String listaEvento){
        Map<String, Object> response = new HashMap<>();
        try{
            this.eventoService.delete(listaEvento);
        } catch(DataAccessException e){
            response.put("mensaje", "Error al eliminar el evento de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","Evento eliminado correctamente");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public void update(@RequestBody Evento evento, String tipoModificacion){
        this.eventoService.update(evento, tipoModificacion);
    }
    
    
    @PostMapping("/eventFilter")
    public List<Evento> findByName(@RequestBody Evento evento, @RequestParam String tipo_consulta){
    	return eventoService.findByName(evento, tipo_consulta);
    }
    
}

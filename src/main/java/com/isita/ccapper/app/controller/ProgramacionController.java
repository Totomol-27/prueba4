package com.isita.ccapper.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.isita.ccapper.app.entity.Programacion;
import com.isita.ccapper.app.entity.Sala;
import com.isita.ccapper.app.service.ProgramacionService;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/programacion")
public class ProgramacionController {

	@Autowired
	private ProgramacionService programacionService;
	
	@GetMapping("/consultaProgramacion")
	public List<Programacion> findAll(Programacion programacion, Integer page, 
			 Integer sizes,  String tipoConsulta) {
		return programacionService.findAll(programacion, page, sizes, tipoConsulta);
	}
	
	@PostMapping("/createProgramacion")
	@ResponseStatus(HttpStatus.CREATED)
	public Programacion save(@RequestBody Programacion programacion) {
		return programacionService.save(programacion);
	}
	
	@PutMapping("/updateProgramacion")
	public ResponseEntity<?> actuliza(@RequestBody Programacion programacion) {
		
		Map<String, Object> response = new HashMap<>(); 
		
		try {
			this.programacionService.update(programacion);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al actulizar el registro");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Actulizo correctamente");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{IdProgramacion}")
	public ResponseEntity<?> delete(@PathVariable String IdProgramacion, @RequestParam Integer eventoId, @RequestParam String tipoEliminacion){
		Map<String, Object> response = new HashMap<>();
		try {
			this.programacionService.delete(IdProgramacion,eventoId,tipoEliminacion);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el registro");
			response.put("error", e.getMessage().concat(" :").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Evento eliminado correctamente");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}

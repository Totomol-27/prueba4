package com.isita.ccapper.app.controller;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.isita.ccapper.app.entity.PerfilPantalla;
import com.isita.ccapper.app.service.PerfilPantallaService;

@RestController
@RequestMapping(value="perfilpantalla")
public class PerfilPantallaController {
	@Autowired
	private PerfilPantallaService perfilPantallaService;
	
	@GetMapping("/{id}")
	public PerfilPantalla findById(@PathVariable Long id, String tipoConsulta) {
		
		return perfilPantallaService.findById(id,tipoConsulta);
	}
	
	@GetMapping("perfil/{idPerfil}")
	public PerfilPantalla LoadPantallasDePerfil(@PathVariable Long idPerfil, @RequestParam String tipoConsulta) {
		
		return  perfilPantallaService.LoadPantallasDePerfil(idPerfil, tipoConsulta);
	}
	
	@PostMapping("/create")
	public PerfilPantalla save(@RequestBody PerfilPantalla perfilPantalla) {
		return perfilPantallaService.save(perfilPantalla);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody PerfilPantalla perfilPantalla) {
		this.perfilPantallaService.update(perfilPantalla);
	}
	
	@DeleteMapping("delete/{listaPantallas}/{perfilId}")
	public ResponseEntity<?> delete(@PathVariable String listaPantallas, @PathVariable Long perfilId){
		Map<String, Object> response = new HashMap<>();
 		try {
 			this.perfilPantallaService.delete(listaPantallas, perfilId);
 		}catch(DataAccessException e) {
 			response.put("mensaje", "Error al eliminar las pantallas en la base de datos ");
 			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
 			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
 		}
 		response.put("Mensaje","Pantallas eliminadas con éxito");
 		 return new  ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}

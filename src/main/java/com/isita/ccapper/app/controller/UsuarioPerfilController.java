package com.isita.ccapper.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isita.ccapper.app.entity.UsuarioPerfil;
import com.isita.ccapper.app.service.UsuarioPerfilService;

@RestController
@RequestMapping(value="/usuarioperfil")
public class UsuarioPerfilController {
	
	@Autowired
	private UsuarioPerfilService usuarioPerfilService;
	
	@GetMapping("/{id}")
	public UsuarioPerfil findById(@PathVariable Long id, String tipoConsulta) {
		return usuarioPerfilService.findById(id, tipoConsulta);
	}
	
	@GetMapping("/usuario/{idUsuario}")
	public UsuarioPerfil LoadPerfilesUsuario(@PathVariable Long idUsuario, @RequestParam String tipoConsulta) {
		return usuarioPerfilService.LoadPerfilesUsuario(idUsuario, tipoConsulta);
	}
	
	@PostMapping("/create")
	public UsuarioPerfil save(@RequestBody UsuarioPerfil usuarioPerfil) {
		return usuarioPerfilService.save(usuarioPerfil);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody UsuarioPerfil usuarioPerfil) {
	 this.usuarioPerfilService.update(usuarioPerfil);
	}
	
	@DeleteMapping("delete/{listaPerfiles}/{usuarioId}")
	public ResponseEntity<?> delete(@PathVariable String listaPerfiles, @PathVariable Long usuarioId){
		Map<String, Object> response = new HashMap<>();
 		try {
 			this.usuarioPerfilService.delete(listaPerfiles, usuarioId);
 		}catch(DataAccessException e) {
 			response.put("mensaje", "Error al eliminar las pantallas en la base de datos ");
 			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
 			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
 		}
 		response.put("Mensaje","Pantallas eliminadas con éxito");
 		 return new  ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}

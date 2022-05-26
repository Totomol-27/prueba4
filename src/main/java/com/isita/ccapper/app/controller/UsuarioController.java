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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.isita.ccapper.app.entity.PerfilPantalla;
import com.isita.ccapper.app.entity.Usuario;
import com.isita.ccapper.app.service.UsuarioService;
@CrossOrigin(origins = { "http://localhost:4200"})
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
 	@Autowired
	private UsuarioService usuarioService;
 		
 	@GetMapping("/username")
 	public Usuario getUsuario(@RequestParam String nombreUsuario,@RequestParam boolean token, @RequestParam String tipoConsulta){
 		Usuario usuario = new Usuario();
 		usuario.setUsuario(nombreUsuario);
 		return  this.usuarioService.findByUsername(usuario,tipoConsulta,token);
 	}
 	
 	@GetMapping("/buscar/{id}")
 	public Usuario findByid(@PathVariable Long id, @RequestParam String tipoConsulta) {
 		Usuario usuario = usuarioService.findById(id,tipoConsulta);
 		return usuario;
 	}
 	
 	@GetMapping("/nombreUsuario")
 	public List<Usuario> findUserName(@RequestParam String  nombre, @RequestParam String tipoConsulta) {
 		List<Usuario> listaUsuario = usuarioService.nombreUsuarrio(nombre, tipoConsulta);
 		return listaUsuario;
 	}
 	
 	
 	@GetMapping("/findAll")
 	public List<Usuario>findAll(@RequestParam Integer pagina,  @RequestParam Integer tamanio,  @RequestParam String tipoConsulta){
 		return usuarioService.findAll(pagina, tamanio, tipoConsulta);
 	}
	
 	@PostMapping("/create")
 	@ResponseStatus(HttpStatus.CREATED)
 	public Usuario save(@RequestBody Usuario usuario) {
 		return usuarioService.save(usuario);
 	}
 	
 	@DeleteMapping(value="/{listaUsuario}")
 	public ResponseEntity<?> delete(@PathVariable String listaUsuario) {
 		Map<String, Object> response = new HashMap<>();
 		try {
 			this.usuarioService.delete(listaUsuario);
 		}catch(DataAccessException e) {
 			response.put("mensaje", "Error al eliminar el cliente de la base de datos ");
 			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
 			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
 		}
 		response.put("Mensaje","El cliente eliminado con éxito");
 		 return new  ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
 	}
 	
 	@PutMapping("/update")
 	public void update(@RequestBody Usuario usuario) {
 		this.usuarioService.update(usuario,"");
 	}

}

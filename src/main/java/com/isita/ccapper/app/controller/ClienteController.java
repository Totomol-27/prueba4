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

import com.isita.ccapper.app.entity.Cliente;
import com.isita.ccapper.app.service.ClienteService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService empresaService;
	
	@GetMapping("/buscar/{id}")
	public Cliente findById(@PathVariable Long id, @RequestParam String tipoConsulta) {
		Cliente empresa = empresaService.findById(id, tipoConsulta);
		return empresa;
	}
	
	@GetMapping("/findAll")
	public List<Cliente> findAll(@RequestParam Integer pagina, @RequestParam Integer tamanio, @RequestParam String tipoConsulta){
		return empresaService.findAll(pagina, tamanio, tipoConsulta);
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente save(@RequestBody Cliente cliente) {
		return empresaService.save(cliente);
	}
	
	@DeleteMapping(value = "/{listaEmpresa}")
	public ResponseEntity<?> delete(@PathVariable String listaEmpresa){
		Map<String, Object> response = new HashMap<>();
		try {
			this.empresaService.delete(listaEmpresa);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar la empresa de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Cliente eliminado correctamente");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	@PutMapping("/update")
	public void update(@RequestBody Cliente cliente, String tipo_modificacion) {
		this.empresaService.update(cliente, tipo_modificacion);
	}
	
	 @GetMapping("/clienteNombre")
	 public List<Cliente> findEmpresaClave(@RequestParam String nombre, @RequestParam String tipo_consulta){
		 return empresaService.findByName(nombre, tipo_consulta);
	}
	 
	@GetMapping("/listClientes")
	public List<Cliente> lstClientes(@RequestParam String tipoConsulta){
		return empresaService.lstClientes(tipoConsulta);
	}

}

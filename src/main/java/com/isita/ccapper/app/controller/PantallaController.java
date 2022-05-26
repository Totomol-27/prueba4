package com.isita.ccapper.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.isita.ccapper.app.entity.Pantalla;
import com.isita.ccapper.app.service.PantallaService;

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

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/pantalla")
public class PantallaController {

    @Autowired
    private PantallaService pantallaService;


    @GetMapping("buscar/{id}")
    public Pantalla findByid(@PathVariable Long id, @RequestParam String tipo_consulta){
    	Pantalla pantalla = pantallaService.findById(id, tipo_consulta);
    	System.out.println("Pantalla controller "+pantalla.toString());
       return pantalla;

    }


    @GetMapping("/buscarClave")
    public List<Pantalla> buscarPantallaClave(@RequestParam String clave, @RequestParam String tipoConsulta){
       return pantallaService.buscarPantallaClave(clave, tipoConsulta);
    }
    
    @GetMapping("/findAll")
    public List<Pantalla> findAll(@RequestParam Integer pagina, @RequestParam Integer tamanio, @RequestParam String tipoConsulta){
    	System.out.println("PantallaController parametros ---------------------------->>>"+tipoConsulta);
        return pantallaService.findAll(pagina, tamanio, tipoConsulta);
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Pantalla save(@RequestBody Pantalla pantalla){
    	System.out.println("Datos recibidos de body---------------------------------------------->>"+pantalla);
        return pantallaService.save(pantalla);
    }

    @DeleteMapping(value="/{listaPantalla}")
    public ResponseEntity<?> delete(@PathVariable String listaPantalla){
    	System.out.println("valores --------->>>"+listaPantalla);
        Map<String, Object> response = new HashMap<>();
        try{
            this.pantallaService.delete(listaPantalla);
        } catch(DataAccessException e){
            response.put("mensaje","Error al eliminar la pantalla de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Mensaje","La pantalla se elimino con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    public void update(@RequestBody Pantalla pantalla, String tipoModificacion){
    	System.out.println("Valores del body---------------->>>><<"+ pantalla.getId()+"\n clave "+pantalla.getClave()+"\n nombre "+pantalla.getNombre()+"\n descripcion "+pantalla.getDescripcion()+"\n indice "+pantalla.getIndice()+ "\n pantalla padre"+pantalla.getPantallaPadre()+"\n aplicacion id"+pantalla.getAplicacionId()+"\n estatus"+pantalla.getEstatus()+" tipoModificacion ------->>>"+tipoModificacion);
        this.pantallaService.update(pantalla, "");
    }
    
    @GetMapping("/searchPantalla")
    public List<Pantalla> findPantallaByName(@RequestParam String nombre, @RequestParam String tipoConsulta){
    	return pantallaService.findByName(nombre, tipoConsulta);
    }
}

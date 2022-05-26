package com.isita.ccapper.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.isita.ccapper.app.interfaces.IArchivoService;
import com.isita.ccapper.app.model.RespuestaMensaje;


@RestController
@RequestMapping("/archivo")
public class ArchivoController {
	@Autowired
	  IArchivoService IArchivoService;
	
	@PostMapping("/subir")
	  public ResponseEntity<RespuestaMensaje> cargarArchivo(@RequestParam("file") MultipartFile file) {
	    String mensaje = "";
	    try {
	    	IArchivoService.save(file);
	    	mensaje = "Correo enviado";
	      return ResponseEntity.status(HttpStatus.OK).body(new RespuestaMensaje(mensaje));
	    } catch (Exception e) {
	    	mensaje = "No se fue posible enviar el correo";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new RespuestaMensaje(mensaje));
	    }
	  }

}

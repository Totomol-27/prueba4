package com.isita.ccapper.app.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.isita.ccapper.app.interfaces.IArchivoService;
import com.isita.ccapper.app.util.EmailService;

@Service
public class ArchivoService implements IArchivoService {
	@Autowired
	EmailService emailService;
	

	  @Override
	  public void save(MultipartFile file) {
	    try {
	      emailService.sendEmailTool("Alta de Usuario Capper", "Fue dado de alta el usuario en capper ", 
	    		  "eutimio.sanchez@isita.com.mx", true, file);
	    } catch (Exception e) {	    	
	    	e.getMessage();
	      throw new RuntimeException("No se pudo enviar el email con el archivo. Error: " + e.getMessage());
	    }
	  }

}

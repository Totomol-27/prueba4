package com.isita.ccapper.app.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class EmailService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender sender;

	public boolean sendEmailTool(String encabezadoCorreo,String contenidoMensage, String correoDestinario,
		boolean archivoAdjunto, MultipartFile file) throws MessagingException {
		boolean send = false;
		JavaMailSender javaMailSender = sender; 
		
		if(javaMailSender == null) {
			throw new MessagingException("Erro al intentar inicializar los parametros de configuración");
		}
			
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg,true);	
		try {
			helper.setTo(correoDestinario);
			helper.setText(contenidoMensage, true);
			helper.setSubject(encabezadoCorreo);
			 
			if(archivoAdjunto) {
				helper.addAttachment(file.getOriginalFilename(), new InputStreamSource() {
					@Override
					public InputStream getInputStream() throws IOException {
						return file.getInputStream();
					}
					
				});
			}
			
			javaMailSender.send(msg);
			LOGGER.info("<<Email enviado exitosamente!>>");
		} catch (MessagingException e) {
			LOGGER.error("Hubo un error al enviar el mail: {}", e);
		}
		return send;
	}	
}
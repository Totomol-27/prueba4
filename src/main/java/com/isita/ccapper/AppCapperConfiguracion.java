package com.isita.ccapper;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;

@Configuration
public class AppCapperConfiguracion {

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		try {
			Properties property = Utility.readPropertiesFile(Constantes.PROPIEDADES);
			
		    mailSender.setHost(property.getProperty("spring.mail.host"));
		    mailSender.setPort(Integer.parseInt(property.getProperty("spring.mail.port")));
		     
		    mailSender.setUsername(property.getProperty("spring.mail.username"));
		    mailSender.setPassword(property.getProperty("spring.mail.password"));
		     
		    Properties props = mailSender.getJavaMailProperties();
		    props.put("mail.transport.protocol", "smtp");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.debug", "false");
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	     
	    return mailSender;
	}
	
}

package com.isita.ccapper.app.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class InterceptorSecurity implements  WebMvcConfigurer{
	
	@Autowired
	private SecurityHttp securityHttp;
	
	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(securityHttp);
	}

}

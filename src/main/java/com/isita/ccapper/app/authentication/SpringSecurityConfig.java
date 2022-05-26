package com.isita.ccapper.app.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService usuarioService;
	
	@Autowired
	private AuthenticationSuccessErrorHandler eventPublisher;
	
	@Bean
	public BCryptPasswordEncoder  encrypPassword() {
		return  new BCryptPasswordEncoder();
	}
	
	
	
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
		authentication.userDetailsService(this.usuarioService).passwordEncoder(encrypPassword()).
		and().authenticationEventPublisher(eventPublisher);

	}
	
	@Bean("AuthenticationManager")
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		/*manejo de sesión deshabilitado*/
		http.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}	
}

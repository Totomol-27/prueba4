package com.isita.ccapper.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.isita.ccapper.app.authentication.MissingHeaderInfoException;
import com.isita.ccapper.app.entity.Usuario;
import com.isita.ccapper.app.util.Constantes;

@Service
public class ConfigurationService implements UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(ConfigurationService.class);
	
	@Autowired
	private  UsuarioService usuarioService;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws MissingHeaderInfoException {
		Usuario usuario = new Usuario();
		usuario.setUsuario(username);
		String tipo_consulta="L1";
		System.out.println("nombre del usuario-------------------------------------------------------------------> "+username);
		boolean tokenGenerado = false;
		
			Usuario user  = usuarioService.findByUsername(usuario,tipo_consulta,tokenGenerado);
			if(user.isActivo()==Constantes.ESTATUS_USUARIO_BLOQUEADO && user.getId()!=null) {
				throw new MissingHeaderInfoException("USER_ESTATUS_FALSE");
			}
			
			List<GrantedAuthority> authorities = user.getPerfil()
					.stream().map(perfil->new SimpleGrantedAuthority(perfil.getNombre()))
					.peek(authority ->logger.info("perfil-------------> "+authority.getAuthority()))
					.collect(Collectors.toList());
			return new User(user.getUsuario(), user.getContrasena(), user.isActivo(), true, true, true, authorities);	
	}

}

package com.isita.ccapper.app.authentication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.stereotype.Component;
import com.isita.ccapper.app.entity.Usuario;
import com.isita.ccapper.app.service.UsuarioService;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;

@Component
public class InfoAdicionalToken implements TokenEnhancer {
	@Autowired
	private UsuarioService usuarioService;
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Usuario objetoUsuario = new Usuario();
		
		objetoUsuario.setUsuario(authentication.getName());
		objetoUsuario = usuarioService.findByUsername(objetoUsuario, Constantes.TIPO_CONSULTA_L1, false);
		Map<String,Object>datosUsuario = new HashMap<>();
		datosUsuario.put("nombre",objetoUsuario.getUsuario()!=null?objetoUsuario.getUsuario():Constantes.VACIO);
		datosUsuario.put("fecha_generada", Utility.generarFecha());
		datosUsuario.put("id",objetoUsuario.getId()!=null?objetoUsuario.getId():Constantes.NUMERO_CERO);
		try {
			Properties property = Utility.readPropertiesFile(Constantes.PROPIEDADES);
			String segundos = property.getProperty(Constantes.TOKEN_EXPIRACION);
			datosUsuario.put("fecha_expiracion",Utility.fechaExpiracionToken(Integer.parseInt(segundos)));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(datosUsuario);
		return accessToken;
	}


}

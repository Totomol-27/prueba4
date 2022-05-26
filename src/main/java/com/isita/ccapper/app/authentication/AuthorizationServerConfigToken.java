package com.isita.ccapper.app.authentication;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import com.isita.ccapper.app.authentication.InfoAdicionalToken;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;

import org.springframework.security.authentication.AuthenticationManager;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigToken extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	private BCryptPasswordEncoder encryptPassword;
	
	@Autowired
	@Qualifier("AuthenticationManager")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private InfoAdicionalToken infoAdicionalToken;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll")
		.checkTokenAccess("isAuthenticated");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		Properties property = Utility.readPropertiesFile(Constantes.PROPIEDADES);
		clients.inMemory().withClient(property.getProperty(Constantes.APP_CLIENTE))
		.secret(encryptPassword.encode(property.getProperty(Constantes.KEY_CLIENTE_APP)))
		.scopes("read","write")
		.authorizedGrantTypes("password","refresh_token")
		.accessTokenValiditySeconds(Integer.parseInt(property.getProperty(Constantes.TOKEN_EXPIRACION)))
		.refreshTokenValiditySeconds(Integer.parseInt(property.getProperty(Constantes.TOKEN_EXPIRACION)));
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken,JwtDataAccessToken()));
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())
		.accessTokenConverter(JwtDataAccessToken())
		.tokenEnhancer(tokenEnhancerChain);
		
	}
	
	
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(JwtDataAccessToken());
	}
	
	@Bean
	public JwtAccessTokenConverter JwtDataAccessToken() {

		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		Properties property;
		try {
			property = Utility.readPropertiesFile(Constantes.PROPIEDADES);
			jwtAccessTokenConverter.setSigningKey(property.getProperty(Constantes.KEY_PRIVATE_TOKEN_RSA));
			jwtAccessTokenConverter.setVerifierKey(property.getProperty(Constantes.KEY_PUBLIC_TOKEN_RSA));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jwtAccessTokenConverter;
	}
	
}

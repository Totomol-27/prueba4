package com.isita.ccapper.app.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import com.isita.ccapper.app.entity.Usuario;
import com.isita.ccapper.app.service.UsuarioService;
import com.isita.ccapper.app.util.Constantes;
import com.isita.ccapper.app.util.Utility;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.text.DateFormat;
import java.text.ParseException;
import java.sql.Timestamp;
import java.util.*;
import org.slf4j.*;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {
	
	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);
	@Autowired
	private UsuarioService usuarioService;
	@Override
	public void publishAuthenticationSuccess(Authentication authentication) throws MissingHeaderInfoException {
			
	}
	
	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		log.error("error en el login "+exception.getMessage());
		
		Usuario usuario = new Usuario();
		usuario.setUsuario(authentication.getName());
		Usuario user = usuarioService.findByUsername(usuario, Constantes.TIPO_CONSULTA_L1, false);
		if(user.getId()!=null && user.getTipoBloqueo()==0) {
			if(user.getIntento()==null) {
				user.setIntento(Constantes.NUMERO_CERO);
			}
			
			System.out.println("fecha default "+user.getHoraPrimerIntentoFallido().toString());
			if(!user.getHoraPrimerIntentoFallido().toString().equals(Constantes.FECHA_DEFAULT)){
				
				this.RegistrarMasIntentos(user);
			}else {
				/*primer intento fallido*/
				System.out.println("--------> SE INSERTA EL PRIMER INTENTO");
				this.RegistrarPrimerIntento(user, Constantes.REGISTRAR_PRIMER_INTENTO);
				
			}
		}
		
		
	}

	
	
	private void RegistrarMasIntentos(Usuario user) {
		DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date fechaActual = new Date();
		try {
			DateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
			Date HoraPrimerIntento = fechaHora.parse(user.getHoraPrimerIntentoFallido().toString());
			
			if(formatoFecha.format(HoraPrimerIntento).equals(formatoFecha.format(fechaActual))) {	
				long diferencia=fechaActual.getTime()-HoraPrimerIntento.getTime();
				long minutos = TimeUnit.MILLISECONDS.toMinutes(diferencia);
				System.out.println("minuutos transcurridos "+minutos);
				if(minutos<=Constantes.RANGO_MINUTOS_DE_BLOQUEO){
					this.validarIntentos(user);
				}else {
					System.out.println("--------> LA FECHA ES IGUAL PERO LOS MINUTOS NO SE REGISTRA EL PRIMER INTENTO");

					this.RegistrarPrimerIntento(user, Constantes.REGISTRAR_PRIMER_INTENTO);
				}
			}else {
				System.out.println("--------> LA FECHA NO ES IGUAL SE REGISTRA EL PRIMER INTENTO");
			this.RegistrarPrimerIntento(user, Constantes.REGISTRAR_PRIMER_INTENTO);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void RegistrarPrimerIntento(Usuario user,String tipoModificacion) {
		Usuario usuario = new Usuario();
		if(user.getIntento()>=Constantes.NUMERO_CERO  && user.getIntento()<=Constantes.LIMITE_INTENTOS_PARA_LOGUEARSE) {
			usuario = user;
			usuario.setIntento(Constantes.NUMERO_UNO);
			Timestamp fechaActualSistema = Timestamp.valueOf(Utility.generarFecha());
			usuario.setHoraPrimerIntentoFallido(fechaActualSistema);
			this.ActualizarUsuario(usuario, tipoModificacion);
		}
		
	}
	
	
	private void validarIntentos(Usuario user) {
		if((user.getIntento()+1)>Constantes.LIMITE_INTENTOS_PARA_LOGUEARSE) {
			user.setIntento(user.getIntento()+Constantes.NUMERO_UNO);
			System.out.println("--------> LA FECHA ES IGUAL Y LOS MINUTOS TAMBIEN PERO YA ES MAS DE 3 INTENTOS" +user.getIntento());
			System.out.println("--------> SE BLOQUEARA USUARIO");

			Timestamp fechaActualSistema = Timestamp.valueOf(Utility.generarFecha());
			user.setHoraBloqueo(fechaActualSistema);
			user.setActivo(false);
			user.setTipoBloqueo(Constantes.BLOQUEO_POR_INTENTOS);
			this.ActualizarUsuario(user,Constantes.BLOQUEAR_USUARIO);
		}else {
			user.setIntento(user.getIntento()+Constantes.NUMERO_UNO);
			System.out.println("--------> LA FECHA ES IGUAL Y LOS MINUTOS TAMBIEN  SE REGISTRA EL INTENTO" +user.getIntento());
			this.ActualizarUsuario(user, Constantes.SOLO_REGISTRAR_INTENTO);
		}
		
	}
	
	
	private void ActualizarUsuario(Usuario usuario, String tipoModificacion) {
			this.usuarioService.update(usuario,tipoModificacion);
	}
	
	
	public Boolean DesbloquearUsuario(Usuario user) {
		DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date fechaActual = new Date();
		Date HoraBloqueo;
		try {
			HoraBloqueo = fechaHora.parse(user.getHoraBloqueo().toString());	
			long diferencia=fechaActual.getTime()-HoraBloqueo.getTime();
			long minutos = TimeUnit.MILLISECONDS.toMinutes(diferencia);
			System.out.println("minutos--------------> transcurridos"+minutos);
			if(minutos>Constantes.RANGO_MINUTOS_PARA_DESBLOQUEAR) {
				return true;
			}
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	

	
}

package com.isita.ccapper.app.authentication;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import com.isita.ccapper.app.entity.Permiso;
import com.isita.ccapper.app.entity.Servicio;
import com.isita.ccapper.app.service.ServicioService;
import com.isita.ccapper.app.util.Constantes;

@Configuration
public class SecurityHttp implements HandlerInterceptor {
	@Autowired
 	private ServicioService servicio;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpServletRequest solicitud = (HttpServletRequest) request;
		HttpServletResponse respuesta = (HttpServletResponse)response;
		String url = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		if(url.equals("/error")) {
			return false;
		}
		List<Servicio> servicioSolicitado  = this.consultarServicio(url, solicitud);
		if(servicioSolicitado.size()==Constantes.NUMERO_CERO) {
			respuesta.sendError(javax.servlet.http.HttpServletResponse.SC_FORBIDDEN,"Acceso Denegado");
			return false;
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	
	public List<Servicio> consultarServicio(String url, HttpServletRequest solicitud) {
		System.out.println("----->"+url);
		System.out.println("----->"+solicitud.getMethod());
		System.out.println("----->"+solicitud.getUserPrincipal().getName());

		
		Servicio servicio = new Servicio();
		servicio.setUri(url);
		Permiso permiso = new Permiso();
		permiso.setNombre(solicitud.getMethod());
		servicio.setPermiso(permiso);
		servicio.setUsuario(solicitud.getUserPrincipal().getName());
		return  this.servicio.buscarPermiso(servicio, Constantes.TIPO_CONSULTA_L5);
	}
	
	

}

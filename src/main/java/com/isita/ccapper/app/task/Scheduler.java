package com.isita.ccapper.app.task;

import org.springframework.stereotype.Component;

import com.isita.ccapper.app.repository.UsuarioRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

@Component
public class Scheduler {
	public static final int  TIEMPO_INICIO_DEPUES_DE_ARRANCAR_LA_APP = 5000;
	public static final int TIEMPO_EJECUTAR_LA_TAREA =60000;
	public static final int RANGO_MINUTOS_BLOQUEO = 3;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Scheduled(fixedDelay = TIEMPO_EJECUTAR_LA_TAREA, initialDelay = TIEMPO_INICIO_DEPUES_DE_ARRANCAR_LA_APP)
	   public void fixedDelaySch() {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	      Date now = new Date();
	      String strDate = sdf.format(now);
	      
	      System.out.println("Fixed Delay scheduler:: " + strDate);
	      usuarioRepository.procesoUsuario(RANGO_MINUTOS_BLOQUEO);
	   }
}

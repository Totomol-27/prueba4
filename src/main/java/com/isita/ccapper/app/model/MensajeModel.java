package com.isita.ccapper.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensajeModel {

	private String mensaje;
	private String codigo;
	public MensajeModel(String mensaje, String codigo) {
		super();
		this.mensaje = mensaje;
		this.codigo = codigo;
	}
	public MensajeModel() {
		super();
	}
	
}

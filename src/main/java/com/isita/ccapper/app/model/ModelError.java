package com.isita.ccapper.app.model;

public class ModelError {
	private String codigo_error;
	private String mensaje_error;
	
	
	
	public ModelError() {
	}



	public ModelError(String codigo_error, String mensaje_error) {
		super();
		this.codigo_error = codigo_error;
		this.mensaje_error = mensaje_error;
	}



	public String getCodigo_error() {
		return codigo_error;
	}



	public void setCodigo_error(String codigo_error) {
		this.codigo_error = codigo_error;
	}



	public String getMensaje_error() {
		return mensaje_error;
	}



	public void setMensaje_error(String mensaje_error) {
		this.mensaje_error = mensaje_error;
	}
	
	
}

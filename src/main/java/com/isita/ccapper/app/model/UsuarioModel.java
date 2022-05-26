package com.isita.ccapper.app.model;

public class UsuarioModel {
	
	private Long id;
	private String usuario;
	private String contrasena;
	private String aplicacion;
	private Integer tipo_usuario_id;
	private String correo;
	private Boolean activo;
	private String ip_origen;
	private Integer estatus;
	public UsuarioModel() {
		super();
	}

	public UsuarioModel(Long id, String usuario, String contrasena, String aplicacion, Integer tipo_usuario_id,
			String correo, Boolean activo, String ip_origen, Integer estatus) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.aplicacion = aplicacion;
		this.tipo_usuario_id = tipo_usuario_id;
		this.correo = correo;
		this.activo = activo;
		this.ip_origen = ip_origen;
		this.estatus = estatus;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public Integer getTipo_usuario_id() {
		return tipo_usuario_id;
	}

	public void setTipo_usuario_id(Integer tipo_usuario_id) {
		this.tipo_usuario_id = tipo_usuario_id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getIp_origen() {
		return ip_origen;
	}

	public void setIp_origen(String ip_origen) {
		this.ip_origen = ip_origen;
	}


	public Integer getEstatus() {
		return estatus;
	}


	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	
}

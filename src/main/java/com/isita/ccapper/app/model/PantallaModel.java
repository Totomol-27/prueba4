package com.isita.ccapper.app.model;

public class PantallaModel {

	private Long id;
	private String clave;
	private String nombre;
	private String descripcion;
	private Integer indice;
	private Integer pantalla_padre;
	private Integer aplicacion_id;
	private Integer estatus;
	
	
	public PantallaModel() {
		super();
	}


	public PantallaModel(Long id, String clave, String nombre, String descripcion, Integer indice,
			Integer pantalla_padre, Integer aplicacion_id, Integer estatus) {
		super();
		this.id = id;
		this.clave = clave;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.indice = indice;
		this.pantalla_padre = pantalla_padre;
		this.aplicacion_id = aplicacion_id;
		this.estatus = estatus;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Integer getIndice() {
		return indice;
	}


	public void setIndice(Integer indice) {
		this.indice = indice;
	}


	public Integer getPantalla_padre() {
		return pantalla_padre;
	}


	public void setPantalla_padre(Integer pantalla_padre) {
		this.pantalla_padre = pantalla_padre;
	}


	public Integer getAplicacion_id() {
		return aplicacion_id;
	}


	public void setAplicacion_id(Integer aplicacion_id) {
		this.aplicacion_id = aplicacion_id;
	}


	public Integer getEstatus() {
		return estatus;
	}


	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	
	
	
}

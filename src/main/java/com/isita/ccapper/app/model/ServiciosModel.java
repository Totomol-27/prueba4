package com.isita.ccapper.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiciosModel {
	
	private Long id;
	
	private String uri;
	
	private Integer aplicacionId;
	
	private Integer permisoId;
	
	private int estatus;

	public ServiciosModel(Long id, String uri, Integer aplicacionId, Integer permisoId, int estatus) {
		super();
		this.id = id;
		this.uri = uri;
		this.aplicacionId = aplicacionId;
		this.permisoId = permisoId;
		this.estatus = estatus;
	}

	public ServiciosModel() {
	}
	
	
	
}

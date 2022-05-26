package com.isita.ccapper.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tipo_usuario")
public class TipoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	private Long id;
	
	@Size(max=50)
	@NotNull
	private String clave;
	
	@Size(max=250)
	@NotNull
	private String descripcion;
	
	@Size(max=250)
	@NotNull
	private String nombre;
	
	@Size(max=1)
	@NotNull
	private int estatus;

	public TipoUsuario(@NotNull Long id, @Size(max = 50) @NotNull String clave,
			@Size(max = 250) @NotNull String descripcion, @Size(max = 250) @NotNull String nombre,
			@Size(max = 1) @NotNull int estatus) {
		super();
		this.id = id;
		this.clave = clave;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.estatus = estatus;
	}

	public TipoUsuario() {
		
	}
	
	
	
}

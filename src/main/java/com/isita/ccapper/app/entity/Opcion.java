package com.isita.ccapper.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="opcion")
public class Opcion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Integer indice;
	private String nombre;
	private String tipo;
	private Integer longitud;
	
	public Opcion() {

	}

	public Opcion(Long id, Integer indice, String nombre) {
		super();
		this.id = id;
		this.indice = indice;
		this.nombre = nombre;
	}

	
	
	
	
	
}

package com.isita.ccapper.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="tipo_opcion")
public class TipoOpcion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String descripcion;
	private Integer estatus;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="opcion_tipo",joinColumns=@JoinColumn(name="tipo_opcion_id"),
	inverseJoinColumns=@JoinColumn(name="opcion_id"),
	uniqueConstraints = {@UniqueConstraint(columnNames= {"tipo_opcion_id", "opcion_id"})})
	private List<Opcion> opcion;
	
	public TipoOpcion(Long id, String nombre, String descripcion, Integer estatus) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	public TipoOpcion() {
		this.opcion = new ArrayList<>();
	}
	

}

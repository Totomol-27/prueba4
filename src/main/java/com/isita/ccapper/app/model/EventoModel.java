package com.isita.ccapper.app.model;

import java.sql.Timestamp;

public class EventoModel {
    private Long id;
    private Long sala_id;
    private Long estatus_evento_id;
    private Long tipo_evento_id;
    private Timestamp fecha_inicio;
    private Timestamp fecha_fin;
    private String nombre;
    private String descripcion;
    private Integer total_participantes;
    private Integer estatus;
    
    
	public EventoModel() {
		super();
	}


	public EventoModel(Long id, Long sala_id, Long estatus_evento_id, Long tipo_evento_id, Timestamp fecha_inicio,
			Timestamp fecha_fin, String nombre, String descripcion, Integer total_participantes, Integer estatus) {
		super();
		this.id = id;
		this.sala_id = sala_id;
		this.estatus_evento_id = estatus_evento_id;
		this.tipo_evento_id = tipo_evento_id;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.total_participantes = total_participantes;
		this.estatus = estatus;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getSala_id() {
		return sala_id;
	}


	public void setSala_id(Long sala_id) {
		this.sala_id = sala_id;
	}


	public Long getEstatus_evento_id() {
		return estatus_evento_id;
	}


	public void setEstatus_evento_id(Long estatus_evento_id) {
		this.estatus_evento_id = estatus_evento_id;
	}


	public Long getTipo_evento_id() {
		return tipo_evento_id;
	}


	public void setTipo_evento_id(Long tipo_evento_id) {
		this.tipo_evento_id = tipo_evento_id;
	}


	public Timestamp getFecha_inicio() {
		return fecha_inicio;
	}


	public void setFecha_inicio(Timestamp fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}


	public Timestamp getFecha_fin() {
		return fecha_fin;
	}


	public void setFecha_fin(Timestamp fecha_fin) {
		this.fecha_fin = fecha_fin;
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


	public Integer getTotal_participantes() {
		return total_participantes;
	}


	public void setTotal_participantes(Integer total_participantes) {
		this.total_participantes = total_participantes;
	}


	public Integer getEstatus() {
		return estatus;
	}


	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
    
    
    
}

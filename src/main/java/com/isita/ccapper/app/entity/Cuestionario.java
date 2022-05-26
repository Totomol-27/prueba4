package com.isita.ccapper.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQuery;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="cuestionario")
@NamedNativeQuery(
		name = "cuestionario_consulta", 
		query = "{?= call cuestionario_con( ?,?,? )}",
		callable = true,
		resultSetMapping = "resultado_cuestionario"
		)
@SqlResultSetMapping(
		name = "resultado_cuestionario",
		entities = {
				@EntityResult(
						entityClass = Cuestionario.class,
						fields = {
								@FieldResult(
										name = "id", column = "id"
										),
								@FieldResult(
										name = "tipoCuestionarioId", column = "tipo_cuestionario_id"
										),
								@FieldResult(
										name = "nombre", column = "nombre"
										),
								@FieldResult(
										name = "descripcion", column = "descripcion"
										),
								@FieldResult(
										name = "posicion", column = "posicion"
										),
								@FieldResult(
										name = "estatus", column = "estatus"
										),
								@FieldResult(
										name = "iconNombre", column = "icon_nombre"
										)
												
								}
						)
		}
)
public class Cuestionario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name = "tipo_cuestionario_id")
	private Integer tipoCuestionarioId;
	private String nombre; 
	private String descripcion;
	private Integer posicion;
	private Integer estatus;
	
	@Column(name = "icon_nombre")
	private String iconNombre;

	public Cuestionario() {
		super();
	}

	public Cuestionario(Long id, Integer tipoCuestionarioId, String nombre, String descripcion, Integer posicion,
			Integer estatus, String iconNombre) {
		super();
		this.id = id;
		this.tipoCuestionarioId = tipoCuestionarioId;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.posicion = posicion;
		this.estatus = estatus;
		this.iconNombre = iconNombre;
	}
	
	
}

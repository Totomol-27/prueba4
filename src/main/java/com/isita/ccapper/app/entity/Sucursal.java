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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NamedNativeQuery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sucursal")

@NamedNativeQuery(
		name = "sucursal_consulta", 
		query = "{?= call sucursal_con( ?,?,?,?,? )}",
		callable = true,
		resultSetMapping = "resultado_sucursal"
		)
@SqlResultSetMapping(
		name = "resultado_sucursal",
		entities = {
				@EntityResult(
						entityClass = Sucursal.class,
						fields = {
								@FieldResult(
										name = "id", column = "id"
										),
								@FieldResult(
										name = "nombre", column = "nombre"
										),
								@FieldResult(
										name = "estadoId", column = "estado_id"
										),
								@FieldResult(
										name = "municipio", column = "municipio"
										),
								@FieldResult(
										name = "localidad", column = "localidad"
										),
								@FieldResult(
										name = "colonia", column = "colonia"
										),
								@FieldResult(
										name = "calle", column = "calle"
										),
								@FieldResult(
										name = "numeroInterior", column = "numero_interior"
										),
								@FieldResult(
										name = "numeroExterior", column = "numero_exterior"
										)
								}
						)
		}
)
public class Sucursal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	
	@Column(name="estado_id")
	private Integer estadoId;
	private String municipio;
	private String localidad;
	private String colonia;
	private String calle;
	
	@Column(name="numero_interior")
	private String numeroInterior;
	
	@Column(name="numero_exterior")
	private String numeroExterior;

	
	public Sucursal() {
	
	}


	public Sucursal(@NotNull Long id, String nombre, Integer estadoId, String municipio, String localidad,
			String colonia, String calle, String numeroInterior, String numeroExterior) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.estadoId = estadoId;
		this.municipio = municipio;
		this.localidad = localidad;
		this.colonia = colonia;
		this.calle = calle;
		this.numeroInterior = numeroInterior;
		this.numeroExterior = numeroExterior;
	}	
	
}

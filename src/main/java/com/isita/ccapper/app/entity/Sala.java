package com.isita.ccapper.app.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NamedNativeQuery;

import com.isita.ccapper.app.model.Menu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sala")
@NamedStoredProcedureQueries( value = {

		@NamedStoredProcedureQuery(name = "sala_alta", procedureName = "sala_alt",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="tipo_sala_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="estatus_sala_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="sucursal_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="nombre", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="clave", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="telefono", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="capacidad", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
		@NamedStoredProcedureQuery(name = "sala_modificar", procedureName = "sala_mod",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="tipo_sala_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="estatus_sala_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="sucursal_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="nombre", type = String.class),
//				@StoredProcedureParameter(mode = ParameterMode.IN, name="clave", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="telefono", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="capacidad", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
		@NamedStoredProcedureQuery(name = "sala_baja", procedureName = "sala_baj",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
	}
		
)
@NamedNativeQuery(
		name = "sala_consulta", 
		query = "{?= call sala_con( ?,?,?,?,?,? )}",
		callable = true,
		resultSetMapping = "resultado_sala"
		)
@SqlResultSetMapping(
		name = "resultado_sala",
		entities = {
				@EntityResult(
						entityClass = Sala.class,
						fields = {
								@FieldResult(
										name = "id", column = "id"
										),
								@FieldResult(
										name = "tipo_sala_id", column = "tipo_sala_id"
										),
								@FieldResult(
										name = "estatus_sala_id", column = "estatus_sala_id"
										),
								@FieldResult(
										name = "sucursalId", column = "sucursal_id"
										),
								@FieldResult(
										name = "nombre", column = "nombre"
										),
								@FieldResult(
										name = "clave", column = "clave"
										),
								@FieldResult(
										name = "telefono", column = "telefono"
										),
								@FieldResult(
										name = "capacidad", column = "capacidad"
										),
								@FieldResult(
										name = "estatus", column = "estatus"
										)
								}
						)
		}
)
public class Sala {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private Integer tipo_sala_id;
	private Integer estatus_sala_id;
	
	@Column(name = "sucursal_id")
	private Integer sucursalId;
	private String nombre;
	private String clave;
	private String telefono;
	private Integer capacidad;
	private Integer estatus;
	
	@Transient
	private Timestamp fechaInicio;
	@Transient
	private Timestamp fechaFin;
//	private List<Programacion> programacion;
	
	
	
	public Sala(Long id, Integer tipo_sala_id, Integer estatus_sala_id, Integer sucursalId, String nombre, String clave,
			String telefono, Integer capacidad, Integer estatus) {
		super();
		this.id = id;
		this.tipo_sala_id = tipo_sala_id;
		this.estatus_sala_id = estatus_sala_id;
		this.sucursalId = sucursalId;
		this.nombre = nombre;
		this.clave = clave;
		this.telefono = telefono;
		this.capacidad = capacidad;
		this.estatus = estatus;
	}

	public Sala() {
		
	}
	

	
	
}

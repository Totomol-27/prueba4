package com.isita.ccapper.app.entity;

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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NamedNativeQuery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tipo_evento")
@NamedStoredProcedureQueries( value = {

		@NamedStoredProcedureQuery(name = "tipo_evento_alta", procedureName = "tipo_evento_alt",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="nombre", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="descripcion", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
		@NamedStoredProcedureQuery(name = "tipo_evento_modificar", procedureName = "tipo_evento_mod",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="nombre", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="descripcion", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
		@NamedStoredProcedureQuery(name = "tipo_evento_baja", procedureName = "tipo_evento_baj",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
	}
		
)
@NamedNativeQuery(
		name = "tipo_evento_consulta", 
		query = "{?= call tipo_evento_con( ?,?,?,?,?)}",
		callable = true,
		resultSetMapping = "resultado_tipo_evento"
		)
@SqlResultSetMapping(
		name = "resultado_tipo_evento",
		entities = {
				@EntityResult(
						entityClass = TipoEvento.class,
						fields = {
								@FieldResult(
										name = "id", column = "id"
										),
								@FieldResult(
										name = "nombre", column = "nombre"
										),
								@FieldResult(
										name = "descripcion", column = "descripcion"
										),
								@FieldResult(
										name = "estatus", column = "estatus"
										)
								}
						)
		}
)
public class TipoEvento {
	
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(name="nombre")
	private String nombre;
	@NotNull
	@Column(name="descripcion")
	private String descripcion;
	@NotNull
	@Column(name="estatus")
	private Integer estatus;
	
	public TipoEvento(@NotNull Long id, @NotNull String nombre, @NotNull String descripcion, @NotNull Integer estatus) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}
	
	public TipoEvento() {
		
	}
	
}

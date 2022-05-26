package com.isita.ccapper.app.entity;

import java.io.Serializable;

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
import javax.validation.constraints.Size;

import org.hibernate.annotations.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="aplicacion")
@NamedStoredProcedureQueries(value= {
		@NamedStoredProcedureQuery(name = "alta_aplicacion", procedureName = "aplicacion_alt",
				parameters = {
						@StoredProcedureParameter(mode = ParameterMode.IN, name="clave", 				type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name="nombre",	 			type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name="descripcion", 			type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name="segundos_inactividad", type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name="estatus", 				type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name="segundos_session", 	type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.OUT, name="id", 					type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", 		type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", 		type = String.class)
			}),
		@NamedStoredProcedureQuery(name = "modificar_aplicacion", procedureName = "aplicacion_mod",
				parameters= {
						@StoredProcedureParameter(mode = ParameterMode.IN, name="id", 					type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name="clave", 				type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name="nombre", 				type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name="descripcion", 			type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name="segundos_inactividad", type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name="estatus", 				type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.IN, name="segundos_session", 	type = Integer.class),
						@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", 		type = String.class),
						@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", 		type = String.class)
		}),
		@NamedStoredProcedureQuery(name = "eliminar_aplicacion", procedureName = "usuario_act",
					parameters= {
							@StoredProcedureParameter(mode = ParameterMode.IN, name="id", 				type = Integer.class),
							@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", 	type = String.class),
							@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", 	type = String.class)
		})
	
})

@NamedNativeQuery(
		name = "aplicacion_consulta", 
		query = "{?= call aplicacion_con( ?,?,?,?,?,? )}",
		callable = true,
		resultSetMapping = "resultado_aplicacion"
		)
@SqlResultSetMapping(
		name = "resultado_aplicacion",
		entities = {
				@EntityResult(
						entityClass = Perfil.class,
						fields = {
								@FieldResult(
										name = "id", column = "id"
										),
								@FieldResult(
										name = "clave", column = "clave"
										),
								@FieldResult(
										name = "nombre", column = "nombre"
										),
								@FieldResult(
										name = "descripcion", column = "descripcion"
										),
								@FieldResult(
										name = "segundosInactividad", column = "segundos_inactividad"
										),
								@FieldResult(
										name = "segundosSession", column = "segundos_session"
										),
								@FieldResult(
										name = "estatus", column = "estatus"
										)
								}
						)
		}
)
public class Aplicacion implements Serializable {
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
	private String nombre;
	
	@Size(max=250)
	@NotNull
	private String descripcion;
	
	@NotNull
	@Column(name="segundos_inactividad")
	private Integer segundosInactividad;
	
	@NotNull
	@Column(name="segundos_session")
	private Integer segundosSession;
	
	@Size(max=1)
	@NotNull
	private Integer estatus;
	
	

	public Aplicacion(@NotNull Long id, @Size(max = 50) @NotNull String clave, @Size(max = 250) @NotNull String nombre,
			@Size(max = 250) @NotNull String descripcion, @NotNull Integer segundosInactividad,
			@NotNull Integer segundosSession, @Size(max = 1) @NotNull Integer estatus) {
		super();
		this.id = id;
		this.clave = clave;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.segundosInactividad = segundosInactividad;
		this.segundosSession = segundosSession;
		this.estatus = estatus;
	}

	public Aplicacion() {
		super();
	}
	
}

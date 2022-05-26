package com.isita.ccapper.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.NamedNativeQuery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="servicio")
@NamedStoredProcedureQueries( value = {
		@NamedStoredProcedureQuery(name = "servicio_consulta", procedureName = "servicio_con",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="uri", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="page", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="sizes", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="metodo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="usuario", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="pantalla_id", type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="tipo_consulta", type = String.class),

		}),
		@NamedStoredProcedureQuery(name = "servicio_alta", procedureName = "servicio_alt",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="uri", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="aplicacion_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="permiso_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
		@NamedStoredProcedureQuery(name = "servicio_modificar", procedureName = "servicio_mod",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="uri", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="aplicacion_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="permiso_id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="estatus", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
		@NamedStoredProcedureQuery(name = "servicio_baja", procedureName = "servicio_baj",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name="id", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name="estatus", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="ids", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_codigo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name="error_mensaje", type = String.class)
		}),
	}
		
)
@NamedNativeQuery(//se importo de hibernate solo para pruebas deberia ser javax.persistence
		name = "consulta_servicios",
		query ="{?=call servicio_con(?,?,?,?,?)}",
		callable = true,
		resultSetMapping ="servicio"
		)
@SqlResultSetMapping(name = "servicio",
	entities = {
		@EntityResult(
			entityClass = Servicio.class,
			fields = {
				@FieldResult( 
				  name = "id", column = "id"
				 ),
				@FieldResult( 
				  name = "uri", column = "uri"
				),
				@FieldResult( 
				  name = "aplicacionId", column = "aplicacion_id"
				  ),
				@FieldResult( 
				  name = "permisoId", column = "permiso_id"
						  ),
				@FieldResult( 
				  name = "estatus", column = "estatus"
				 ),		
			}
		)		
	}
)
public class Servicio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(max=250)
	@NotNull
	private String uri;
	
	@NotNull
	@OneToOne
	@JoinColumn(name="aplicacion_id")
	private Aplicacion aplicacion;
	
	@OneToOne
	@JoinColumn(name="permiso_id")
	private Permiso permiso;
	
	@NotNull
	private int estatus;
	
	@Transient
	private String usuario;
	
	@Transient
	private Pantalla pantalla;

	public Servicio() {		
	}

	public Servicio(@NotNull Long id, @Size(max = 250) @NotNull String uri, @NotNull Aplicacion aplicacion,
			Permiso permiso, @NotNull int estatus) {
		super();
		this.id = id;
		this.uri = uri;
		this.aplicacion = aplicacion;
		this.permiso = permiso;
		this.estatus = estatus;
	}

	

}
